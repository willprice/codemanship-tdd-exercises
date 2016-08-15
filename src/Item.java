public class Item {
    private final float unitPrice;
    private final int quantity;

    public Item(float unitPrice, int quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public float getTotal() {
        return unitPrice * quantity;
    }
}
