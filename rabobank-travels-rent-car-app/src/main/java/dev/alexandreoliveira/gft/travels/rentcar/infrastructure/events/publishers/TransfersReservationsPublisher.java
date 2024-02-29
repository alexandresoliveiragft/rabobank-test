package dev.alexandreoliveira.gft.travels.rentcar.infrastructure.events.publishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexandreoliveira.gft.travels.rentcar.configurations.KafkaConfiguration;
import dev.alexandreoliveira.gft.travels.rentcar.core.models.TransferModel;
import dev.alexandreoliveira.gft.travels.rentcar.core.usecases.transfers.lock.TransfersLockReservationPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("transfersReservationsPublisher")
public class TransfersReservationsPublisher implements TransfersLockReservationPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransfersReservationsPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notify(TransferModel transferModel) {
        try {
            Map<String, Object> reservedTransfersData = HashMap.newHashMap(3);
            reservedTransfersData.put("reservationId", transferModel.getExternalId());
            reservedTransfersData.put("transferId", transferModel.getId());

            Map<String, Object> data = HashMap.newHashMap(1);
            data.put("reservedTransfers", List.of(reservedTransfersData));

            kafkaTemplate.send(
                    KafkaConfiguration.KAFKA_TOPIC_TRAVEL_TRANSFERS_LOCK_LISTEN,
                    new ObjectMapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
