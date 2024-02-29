package dev.alexandreoliveira.gft.rabobank.travels.flights.infrastructure.events.publishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.rabobank.travels.flights.configurations.KafkaConfiguration;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.models.SeatModel;
import dev.alexandreoliveira.gft.rabobank.travels.flights.core.usecases.seats.reservations.SeatsReservationsPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component("seatsReservationsPublisher")
public class FlightsSeatsReservationsPublisher implements SeatsReservationsPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public FlightsSeatsReservationsPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishLockSeatEvents(UUID registrationId, List<? extends SeatModel> seats) {
        try {
            List<Map<String, Object>> reservations = seats.stream()
                    .map(seatModel -> {
                        Map<String, Object> reserve = HashMap.newHashMap(3);
                        reserve.put("reservationId", seatModel.getExternalId());
                        reserve.put("flightId", seatModel.getFlight().getId());
                        reserve.put("seatId", seatModel.getId());
                        return reserve;
                    }).toList();

            Map<String, Object> data = HashMap.newHashMap(1);
            data.put("reservations", reservations);

            kafkaTemplate.send(
                    KafkaConfiguration.KAFKA_TOPIC_TRAVEL_FLIGHTS_LOCK_LISTEN,
                    new ObjectMapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
