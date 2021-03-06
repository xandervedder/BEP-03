package nl.softwarestrijders.waiter.order.adapters.http.rest;

import nl.softwarestrijders.waiter.order.adapters.http.rest.dto.ModifyProductOnOrderDto;
import nl.softwarestrijders.waiter.order.adapters.http.rest.dto.CreateOrderDto;
import nl.softwarestrijders.waiter.order.core.application.CommandHandler;
import nl.softwarestrijders.waiter.order.core.application.QueryHandler;
import nl.softwarestrijders.waiter.order.core.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/{id}/product/")
    public Order addProductToOrder(
            @PathVariable UUID id,
            @RequestBody ModifyProductOnOrderDto dto) {
        return this.commandHandler.handleAddProductToOrder(id, dto.productId(), dto.amount());
    }

    @DeleteMapping("/{id}/product")
    public void removeProductFromOrder(
            @PathVariable UUID id,
            @RequestBody ModifyProductOnOrderDto dto) {
        this.commandHandler.handleRemoveProductFromOrder(id, dto.productId(), dto.amount());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        this.commandHandler.handleDeleteOrder(id);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable UUID id) {
        return this.queryHandler.getOrderById(id);
    }

    @GetMapping
    public List<Order> findAllOrders(
            @RequestParam(value = "customer", required = false) UUID customer,
            @RequestParam(value = "product", required = false) UUID product) {

        if (customer != null) {
            return this.queryHandler.getAllOrdersByCustomerId(customer);
        }

        if (product != null) {
            return this.queryHandler.getAllOrdersByProductId(product);
        }

        return this.queryHandler.getAllOrders();
    }
}
