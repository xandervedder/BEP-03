package nl.softwarestrijders.waiter.order.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Receipt {

    private List<ReceiptItem> items;

    public Receipt() {
        this.items = new ArrayList<>();
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public void addItem(UUID product, int amount, double price) {
        if (this.getItemByProductId(product) != null) {
            this.addToExistingItem(product, amount);
            return;
        }

        this.items.add(new ReceiptItem(product, amount, price));
    }

    public void addToExistingItem(UUID product, int amount) {
        this.getItemByProductId(product).addAmount(amount);
    }

    public void removeItem(UUID productId, int amount) {
        var item = this.getItemByProductId(productId);

        // If the amount in the list is more than the amount we need to remove, we remove the whole entry.
        if (amount >= item.getAmount()) {
            this.items.remove(item);
            return;
        }

        item.removeAmount(amount);
    }

    public ReceiptItem getItemByProductId(UUID id) {
        var item = this.items.stream().filter(i -> i.getProductId().equals(id)).findFirst();
        return item.orElse(null);
    }
}
