package nl.softwarestrijders.waiter.delivery.core.application;

import nl.softwarestrijders.waiter.delivery.core.application.exception.NotFoundException;
import nl.softwarestrijders.waiter.delivery.core.domain.Delivery;
import nl.softwarestrijders.waiter.delivery.core.domain.DeliveryAddress;
import nl.softwarestrijders.waiter.delivery.core.domain.Status;
import nl.softwarestrijders.waiter.delivery.core.port.messaging.DeliveryEventPublisher;
import nl.softwarestrijders.waiter.delivery.core.port.storage.CustomerRepository;
import nl.softwarestrijders.waiter.delivery.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliveryCommandHandler {
    private final DeliveryRepository repository;
    private final DeliveryEventPublisher eventPublisher;
    private final CustomerRepository customerGateway;

    public DeliveryCommandHandler(
            DeliveryRepository repository,
            DeliveryEventPublisher eventPublisher,
            CustomerRepository customerGateway
    ) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.customerGateway = customerGateway;
    }

    public void handleStatusChange(UUID id, Status status) {
        var delivery = this.getDeliveryById(id);
        delivery.changeStatus(status);
        this.repository.save(delivery);
        this.publishEventsFor(delivery);
    }

    public void handleDeliveryAddressChange(UUID id, DeliveryAddress address) {
        var delivery = this.getDeliveryById(id);
        delivery.changeAddress(address);
        this.repository.save(delivery);
        this.publishEventsFor(delivery);
    }

    public void handleDeleteDelivery(UUID order) {
        var delivery = this.repository.findByOrderId(order).orElseThrow(() -> new NotFoundException(order));
        this.repository.delete(delivery);
    }

    public void handleRegisterDelivery(UUID orderId, UUID customerId) {
        var customerDeliveryAddress = this.customerGateway.getCustomerDeliveryAddress(customerId);

        var address = new DeliveryAddress(
                customerDeliveryAddress.street(),
                customerDeliveryAddress.houseNumber(),
                customerDeliveryAddress.addition(),
                customerDeliveryAddress.postalCode(),
                customerDeliveryAddress.city());

        var delivery = new Delivery(address, Status.REGISTERED, orderId);
        this.repository.save(delivery);
        this.publishEventsFor(delivery);
    }

    private Delivery getDeliveryById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    private void publishEventsFor(Delivery delivery) {
        var events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
    }
}
