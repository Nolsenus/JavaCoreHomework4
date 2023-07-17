import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Item {

    private String name;
    private int price;
    private Category category;

    private static final Map<Category, SaleAmount> sales = new HashMap<>();

    public Item(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Map<Category, SaleAmount> getSales() {
        return sales;
    }

    public static void addRandomSale(Category category, Random random) {
        SaleAmount[] values = SaleAmount.values();
        sales.put(category, values[random.nextInt(values.length)]);
    }

    public static void addSale(Category category, SaleAmount sa) {
        sales.put(category, sa);
    }

    enum Category {
        FRUIT, HYGIENE_PRODUCTS, DAIRY
    }

    enum SaleAmount {
        ZERO_PERCENT(0),
        FIVE_PERCENT(5),
        TEN_PERCENT(10),
        FIFTEEN_PERCENT(15),
        TWENTY_PERCENT(20);
        private final int value;

        SaleAmount(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
