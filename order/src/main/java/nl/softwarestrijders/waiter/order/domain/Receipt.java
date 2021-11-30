package nl.softwarestrijders.waiter.order.domain;

import java.util.Collections;
import java.util.List;

public class Receipt {

    private List<ReceiptItem> items;

    public Receipt() {
    }

    public List<ReceiptItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    //TODO: If ReceiptItem with that productId is already in the list we just need to add the amount.
    public void addItem(ProductId product, int amount) {
        this.items.add(new ReceiptItem(new ReceiptItemId(1), product, amount));
    }

    public void removeItem(ProductId productId, int amount) {
        var item = this.getItemByProductId(productId);

        // If the amount in the list is more than the amount we need to remove, we remove the whole entry.
        if(item.getAmount() > amount)
            this.items.remove(item);

        item.removeAmount(amount);
    }

    private ReceiptItem getItemByProductId(ProductId id) {
        var item = this.items.stream().filter(i -> i.getProductId() == id).findFirst();
        return item.orElse(null);
    }
}
