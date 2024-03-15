package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.transfers;

import dev.alexandreoliveira.gft.rabobank.travels.configurations.pubsub.KafkaConfiguration;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.services.ReservationsService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "travels-transfers-returned", topics = {KafkaConfiguration.KAFKA_TOPIC_TRAVEL_TRANSFERS_LOCK_LISTEN}, groupId = "0")
public class ReservationsLocksTransfersSubscription {

    private final ReservationsService service;

    public ReservationsLocksTransfersSubscription(ReservationsService service) {
        this.service = service;
    }

    @KafkaHandler
    public void listen(@Payload ReservationsLocksTransfersSubscriptionMessage message) {
        service.updateTransfersLockReservation(message);
    }
}
