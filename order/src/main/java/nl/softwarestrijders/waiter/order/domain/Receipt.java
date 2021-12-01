package nl.softwarestrijders.waiter.order.domain;

import nl.softwarestrijders.waiter.order.domain.id.ProductId;
import nl.softwarestrijders.waiter.order.domain.id.ReceiptItemId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Receipt {

    private List<ReceiptItem> items;

    public Receipt() {
        this.items = new ArrayList<>();
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    //TODO: If ReceiptItem with that productId is already in the list we just need to add the amount.
    //TODO: Generate new id for each receipt item
    public void addItem(ProductId product, int amount) {
        if(this.getItemByProductId(product) != null)
            this.getItemByProductId(product).addAmount(amount);

        this.items.add(new ReceiptItem(new ReceiptItemId(1), product, amount));
    }

    public void removeItem(ProductId productId, int amount) {
        var item = this.getItemByProductId(productId);

        // If the amount in the list is more than the amount we need to remove, we remove the whole entry.
        if(amount >= item.getAmount()) {
            this.items.remove(item);
            return;
        }

        item.removeAmount(amount);
    }

    public ReceiptItem getItemByProductId(ProductId id) {
        var item = this.items.stream().filter(i -> i.getProductId().equals(id)).findFirst();
        return item.orElse(null);
    }
}
