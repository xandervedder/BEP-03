package nl.softwarestrijders.waiter.delivery.core.port.storage;

import nl.softwarestrijders.waiter.delivery.infrastructure.driven.storage.CustomerDeliveryAddress;

import java.util.UUID;

public interface CustomerRepository {
    CustomerDeliveryAddress getCustomerDeliveryAddress(UUID orderId);
}
