import java.util.List;
import java.util.Map;

/*
Задача: Эмуляция интернет-магазина
написать классы покупатель, товар и заказ;
создать массив покупателей, массив товаров и массив заказов;
создать статический метод “совершить покупку” со строковыми параметрами, соответствующими полям объекта заказа. Метод должен вернуть объект заказа
Если в метод передан несуществующий покупатель, товар или отрицательное количество, метод должен выбросить соответствующее исключение;
Вызвать метод совершения покупки несколько раз таким образом, чтобы заполнить массив покупок возвращаемыми значениями. Обработать исключения.
Вывести в консоль итоговое количество совершённых покупок после выполнения приложения.

 */
public class Order {
    private Buyer buyer;
    private List<Item> items;
    private List<Integer> amountItems;
    private int totalPrice;

    private static int counter;

    public Order(Buyer buyer, List<Item> items, List<Integer> amountItems) throws IllegalListsException {
        if (items == null) {
            throw new IllegalListsException("items list must not be null");
        }
        if (amountItems == null) {
            throw new IllegalListsException("amountItems list must not be null");
        }
        if (amountItems.size() != items.size()) {
            throw new IllegalListsException("amountItems and items list must be of the same size");
        }
        this.buyer = buyer;
        this.items = items;
        this.amountItems = amountItems;
        counter++;
        int totalPrice = 0;
        double addedPrice;
        for (int i = 0; i < items.size(); i++) {
            addedPrice = items.get(i).getPrice();
            Item.Category category = items.get(i).getCategory();
            Map<Item.Category, Item.SaleAmount> sales = Item.getSales();
            if (sales.containsKey(category)) {
                addedPrice *= (100 - sales.get(category).getValue()) / 100d;
            }
            addedPrice *= amountItems.get(i);
            totalPrice += addedPrice;
        }
        this.totalPrice = totalPrice;
    }

    public static int getCounter() {
        return counter;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Integer> getAmountItems() {
        return amountItems;
    }

    public void setAmountItems(List<Integer> amountItems) {
        this.amountItems = amountItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "buyer=" + buyer +
                ", item=" + items +
                ", amountItems=" + amountItems +
                '}';
    }
}
