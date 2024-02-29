package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.events.kafka.reservations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.rabobank.travels.configurations.pubsub.KafkaConfiguration;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.FlightModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.HotelModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.models.TransferModel;
import dev.alexandreoliveira.gft.rabobank.travels.core.usecases.reservations.create.ReservationsCreatePublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ReservationsLocksPublishersImpl implements ReservationsCreatePublisher {

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

    public ReservationsLocksPublishersImpl(KafkaTemplate<String, String> template) {
        this.template = template;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void lockHotels(UUID reservationId, HotelModel hotel) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("reservationId", reservationId);
            data.put("hotelId", hotel.getExternalId());
            data.put("roomId", hotel.getRoom());
            template.send(KafkaConfiguration.KAFKA_TOPIC_LOCK_HOTEL, objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lockTransfers(UUID reservationId, List<? extends TransferModel> transfers) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("reservationId", reservationId);
            data.put("transfers", transfers.stream().map(TransferModel::getExternalId).toList());
            template.send(KafkaConfiguration.KAFKA_TOPIC_LOCK_TRANSFERS, objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lockFlights(UUID reservationId, List<? extends FlightModel> flights) {
        try {
            List<Map<String, Object>> seatsData = new ArrayList<>();

            flights.forEach(f -> {
                List<Map<String, Object>> seats = f.getSeats().stream().map(s -> {
                    Map<String, Object> seatData = HashMap.newHashMap(3);
                    seatData.put("reservationId", reservationId);
                    seatData.put("flightId", f.getExternalId());
                    seatData.put("seatId", s.getExternalId());
                    return seatData;
                }).toList();
                seatsData.addAll(seats);
            });

            Map<String, Object> data = HashMap.newHashMap(1);
            data.put("seats", seatsData);

            template.send(KafkaConfiguration.KAFKA_TOPIC_LOCK_FLIGHTS, objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
