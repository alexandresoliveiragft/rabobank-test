package dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.entrypoints.subscriptions.rooms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.configurations.KafkaConfiguration;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.entrypoints.subscriptions.rooms.lock.RoomsLockSubscriptionMessage;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.entrypoints.subscriptions.rooms.unlock.RoomsUnlockSubscriptionMessage;
import dev.alexandreoliveira.gft.rabobank.travels.hotel.infrastructure.services.RoomsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RoomsSubscription {

    private final RoomsService roomsService;
    private final ObjectMapper objectMapper;

    public RoomsSubscription(RoomsService roomsService) {
        this.roomsService = roomsService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(id = "travels-hotels-lock", topics = {KafkaConfiguration.KAFKA_TOPIC_LOCK_HOTELS}, groupId = "0")
    public void listenLockMessage(@Payload String message) {
        try {
            RoomsLockSubscriptionMessage roomsLockSubscriptionMessage = objectMapper.readValue(message, RoomsLockSubscriptionMessage.class);
            roomsService.lockRoom(roomsLockSubscriptionMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void listenUnlockMessage(RoomsUnlockSubscriptionMessage message) {
        roomsService.unlockRoom(message);
    }
}
