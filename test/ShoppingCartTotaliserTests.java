import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ShoppingCartTotaliserTests {
    private final ShoppingCartTotaliser totaliser = new ShoppingCartTotaliser(
            Arrays.asList(new OrderValueDiscounter(100), new OrderValueDiscounter(200))
    );

    @Test
    @Parameters(method="shoppingCartTotals")
    public void totalOfCartIsThePriceOfTheItemInTheCart(float expectedTotal, List<Item> itemsInCart) {
        ShoppingCart cart = addItemsToCart(itemsInCart);

        assertEquals(expectedTotal, totaliser.calculateTotal(cart), 0);
    }

    public Object[] shoppingCartTotals() {
        return new Object[]{
                new Object[]{0, Arrays.asList(new Item(0, 1))},
                new Object[]{10, Arrays.asList(new Item(10, 1))},
                new Object[]{20, Arrays.asList(new Item(10, 2))},
                new Object[]{20, Arrays.asList(new Item(10, 1), new Item(10, 1))}
        };
    }

    @Test
    public void cartWithTotalGrossValueOver100Has5PercentDiscount() {
        ShoppingCart cart = addItemsToCart(Arrays.asList(new Item(110, 1)));

        assertEquals(104.5, totaliser.calculateTotal(cart), 0);
    }

    @Test
    public void cartWithTotalGrossValueOver200Has10PercentDiscount() {
        ShoppingCart cart = addItemsToCart(Arrays.asList(new Item(210, 1)));

        assertEquals(189, totaliser.calculateTotal(cart), 0);
    }


    private ShoppingCart addItemsToCart(List<Item> itemsInCart) {
        ShoppingCart cart = new ShoppingCart();

        for (Item item : itemsInCart) {
            cart.addItem(item);
        }
        return cart;
    }
}
