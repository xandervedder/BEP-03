package nl.softwarestrijders.waiter.order.core.domain;

public class Price {

    private double subTotal;
    private double total;
    private double vat = 0.21f;

    public Price(double subTotal, double total, double vat) {
        this.subTotal = subTotal;
        this.total = total;
        this.vat = vat;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTotal() {
        return total;
    }

    public double getVat() {
        return vat;
    }

    public void calculatePrice(Receipt receipt) {
        this.subTotal = receipt.getItems().stream().map(i -> i.getPrice() * i.getAmount()).reduce(0d, Double::sum);
        this.total = this.subTotal + (this.subTotal * this.vat);
    }
}
