package nl.softwarestrijders.waiter.delivery.infrastructure.driver.web;

import nl.softwarestrijders.waiter.delivery.core.application.DeliveryCommandHandler;
import nl.softwarestrijders.waiter.delivery.core.application.DeliveryQueryHandler;
import nl.softwarestrijders.waiter.delivery.core.domain.Delivery;
import nl.softwarestrijders.waiter.delivery.infrastructure.driver.web.dto.DeliveryDto;
import nl.softwarestrijders.waiter.delivery.infrastructure.driver.web.dto.DeliveryStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryCommandHandler commandHandler;
    private final DeliveryQueryHandler queryHandler;

    public DeliveryController(DeliveryCommandHandler commandHandler, DeliveryQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping
    public List<DeliveryDto> listAll() {
        return this.toDto(this.queryHandler.handleGetAllDeliveries());
    }

    @GetMapping("/{id}")
    public DeliveryDto findDeliveryById(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handleGetDeliveryById(id));
    }

    @GetMapping("/status/{id}")
    public DeliveryStatusDto findDeliveryStatus(@PathVariable UUID id) {
        return new DeliveryStatusDto(this.queryHandler.handleGetDeliveryStatus(id));
    }

    @GetMapping("/order/{id}")
    public DeliveryDto findDeliveryByOrder(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handleGetDeliveryByOrder(id));
    }

    private List<DeliveryDto> toDto(List<Delivery> deliveries) {
        return deliveries.stream().map(this::toDto).toList();
    }

    private DeliveryDto toDto(Delivery delivery) {
        return new DeliveryDto(delivery.getId(), delivery.getAddress(), delivery.getStatus(), delivery.getOrderId());
    }
}
