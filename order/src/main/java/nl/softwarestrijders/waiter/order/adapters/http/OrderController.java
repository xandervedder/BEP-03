package nl.softwarestrijders.waiter.order.adapters.http;

import nl.softwarestrijders.waiter.order.adapters.http.dto.AddProductToOrderDto;
import nl.softwarestrijders.waiter.order.adapters.http.dto.CreateOrderDto;
import nl.softwarestrijders.waiter.order.core.application.CommandHandler;
import nl.softwarestrijders.waiter.order.core.application.QueryHandler;
import nl.softwarestrijders.waiter.order.core.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final QueryHandler queryHandler;
    private final CommandHandler commandHandler;

    public OrderController(QueryHandler queryHandler, CommandHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public Order createOrderById(@RequestBody CreateOrderDto dto) {
        return this.commandHandler.handleCreateOrder(dto.customerId());
    }

    @PostMapping("/receipt")
    public Order addProductToOrder(@RequestBody AddProductToOrderDto dto) {
        return this.commandHandler.handleAddProductToOrder(dto.orderId(), dto.productId(), dto.amount());
    }

    @DeleteMapping("/receipt")
    public void removeProductFromOrder(@RequestBody AddProductToOrderDto dto) {
        this.commandHandler.handleRemoveProductFromOrder(dto.orderId(), dto.productId(), dto.amount());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        this.commandHandler.handleDeleteOrder(id);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable UUID id) {
        return this.queryHandler.getOrderById(id);
    }
}
