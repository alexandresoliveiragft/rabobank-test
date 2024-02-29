package dev.alexandreoliveira.gft.travels.rentcar.infrastructure.entrypoints.subscriptions.rooms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.travels.rentcar.configurations.KafkaConfiguration;
import dev.alexandreoliveira.gft.travels.rentcar.infrastructure.entrypoints.subscriptions.rooms.lock.TransfersLockSubscriptionMessage;
import dev.alexandreoliveira.gft.travels.rentcar.infrastructure.services.TransfersService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TransfersSubscription {

    private final TransfersService transfersService;
    private final ObjectMapper objectMapper;

    public TransfersSubscription(TransfersService transfersService) {
        this.transfersService = transfersService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(id = "travels-transfers-lock", topics = {KafkaConfiguration.KAFKA_TOPIC_LOCK_TRANSFERS}, groupId = "0")
    public void listenLockMessage(@Payload String message) {
        try {
            TransfersLockSubscriptionMessage transfersLockSubscriptionMessage = objectMapper.readValue(message, TransfersLockSubscriptionMessage.class);
            transfersService.lock(transfersLockSubscriptionMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
