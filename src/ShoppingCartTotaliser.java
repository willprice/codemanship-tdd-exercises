import java.util.List;

public class ShoppingCartTotaliser {
    private final List<Discounter> discounters;

    public ShoppingCartTotaliser(List<Discounter> discounters) {
        this.discounters = discounters;
    }


    public float calculateTotal(ShoppingCart shoppingCart) {
        float total = shoppingCart.getItems().stream()
                .map((item) -> item.getTotal())
                .reduce(0f, Float::sum);
        return total - (total * calculateDiscount(total));
    }

    private float calculateDiscount(float total) {
        float discount = 0;
        for (Discounter discounter : discounters) {
            discount += discounter.discount(total);
        }
        return discount;
    }
}
