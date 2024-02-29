package dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.events.publishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.configurations.KafkaConfiguration;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.models.RoomModel;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.core.usecases.rooms.lock.RoomsLockReservationPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("roomsLockReservationPublisher")
public class HotelsRoomsReservationsPublisher implements RoomsLockReservationPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public HotelsRoomsReservationsPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notifyForReservationLockRooms(RoomModel model) {
        try {
            Map<String, Object> data = HashMap.newHashMap(3);
            data.put("reservationId", model.getExternalId());
            data.put("hotelId", model.getHotel().getId());
            data.put("roomId", model.getId());

            kafkaTemplate.send(
                    KafkaConfiguration.KAFKA_TOPIC_TRAVEL_HOTELS_LOCK_LISTEN,
                    new ObjectMapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
