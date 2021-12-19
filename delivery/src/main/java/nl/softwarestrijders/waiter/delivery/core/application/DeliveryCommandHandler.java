package nl.softwarestrijders.waiter.delivery.core.application;

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

    // Need to discuss with the boys how to handle fetches appropriately.
    public void handleRegisterDelivery(UUID orderId) {
        var customerDeliveryAddress = this.customerGateway.getCustomerDeliveryAddress(orderId);

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
        return this.repository.findById(id).orElseThrow();
    }

    private void publishEventsFor(Delivery delivery) {
        var events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
    }
}
