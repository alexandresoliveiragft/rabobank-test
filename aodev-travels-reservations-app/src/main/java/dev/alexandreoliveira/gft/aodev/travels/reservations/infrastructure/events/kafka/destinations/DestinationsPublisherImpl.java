package dev.alexandreoliveira.gft.aodev.travels.infrastructure.events.kafka.destinations;

import dev.alexandreoliveira.gft.aodev.travels.configurations.pubsub.KafkaConfiguration;
import dev.alexandreoliveira.gft.aodev.travels.core.models.DestinationModel;
import dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.commands.DestinationsCreateCommand;
import dev.alexandreoliveira.gft.aodev.travels.core.usecases.destinations.commands.DestinationsUpdateCommand;
import dev.alexandreoliveira.gft.aodev.travels.infrastructure.events.dto.destinations.CreateDestinationEvent;
import dev.alexandreoliveira.gft.aodev.travels.infrastructure.events.dto.destinations.UpdateDestinationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class DestinationsPublisherImpl implements DestinationsCreateCommand, DestinationsUpdateCommand {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public DestinationsPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishCreate(DestinationModel model) {
        var createDestination = new CreateDestinationEvent(model.getCity(), model.getState());
        kafkaTemplate.send(KafkaConfiguration.KAFKA_TOPIC_TRAVEL_CQRS_DESTINATION_EVENTS, createDestination);
    }

    @Override
    public void publishUpdate(DestinationModel model) {
        var updateDestination = new UpdateDestinationEvent(model.getId(), model.getCity(), model.getState());
        kafkaTemplate.send(KafkaConfiguration.KAFKA_TOPIC_TRAVEL_CQRS_DESTINATION_EVENTS, updateDestination);
    }
}
