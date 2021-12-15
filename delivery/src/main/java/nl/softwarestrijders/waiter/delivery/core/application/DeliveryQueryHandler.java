package nl.softwarestrijders.waiter.delivery.core.application;

import nl.softwarestrijders.waiter.delivery.core.domain.Delivery;
import nl.softwarestrijders.waiter.delivery.core.domain.Status;
import nl.softwarestrijders.waiter.delivery.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryQueryHandler {
    private final DeliveryRepository repository;

    public DeliveryQueryHandler(DeliveryRepository repository) {
        this.repository = repository;
    }

    public Delivery handleGetDeliveryById(UUID id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Status handleGetDeliveryStatus(UUID id) {
        return this.repository.findById(id).orElseThrow().getStatus();
    }

    public List<Delivery> handleGetAllDeliveries() {
        return this.repository.findAll();
    }

    public Delivery handleGetDeliveryByOrder(UUID orderId) {
        return this.repository.findDeliveryByOrderId(orderId).orElseThrow();
    }
}
