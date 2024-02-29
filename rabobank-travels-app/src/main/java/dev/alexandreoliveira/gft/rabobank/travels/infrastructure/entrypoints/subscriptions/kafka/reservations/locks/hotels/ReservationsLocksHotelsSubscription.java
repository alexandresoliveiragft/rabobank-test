package dev.alexandreoliveira.gft.rabobank.travels.infrastructure.entrypoints.subscriptions.kafka.reservations.locks.hotels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.rabobank.travels.configurations.pubsub.KafkaConfiguration;
import dev.alexandreoliveira.gft.rabobank.travels.infrastructure.services.ReservationsService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "travels-hotels-returned", topics = {KafkaConfiguration.KAFKA_TOPIC_TRAVEL_HOTELS_LOCK_LISTEN}, groupId = "0")
public class ReservationsLocksHotelsSubscription {

    private final ReservationsService service;
    private final ObjectMapper objectMapper;

    public ReservationsLocksHotelsSubscription(ReservationsService service) {
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaHandler
    public void listen(@Payload String message) {
        try {
            ReservationsLocksHotelsSubscriptionMessage reservationsLocksHotelsSubscriptionMessage = objectMapper.readValue(message, ReservationsLocksHotelsSubscriptionMessage.class);
            service.updateHotelsLockReservation(reservationsLocksHotelsSubscriptionMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
