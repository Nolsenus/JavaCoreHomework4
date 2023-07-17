import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Shop {

    final ArrayList<Item> stock;
    final ArrayList<Buyer> customers;

    public Shop(ArrayList<Item> stock, ArrayList<Buyer> customers) {
        this.stock = stock;
        this.customers = customers;
    }

    public Order deal(String buyerName, List<String> itemNames, List<Integer> amounts) throws NoBuyerFoundException, NoItemFoundException, IllegalAmountException, IllegalListsException, TooMuchSaleException {
        if (itemNames == null) {
            throw new IllegalListsException("Items list is null.");
        }
        if (amounts == null) {
            throw new IllegalListsException("Amounts list is null.");
        }
        if (itemNames.size() != amounts.size()) {
            throw new IllegalListsException(String.format("Items and amounts lists must be the same size, but are items: %d, amounts: %d instead.", itemNames.size(), amounts.size()));
        }
        Buyer tempBuyer = null;
        for (Buyer customer : customers) {
            if (buyerName.equals(customer.getName())) {
                tempBuyer = customer;
                break;
            }
        }
        if (tempBuyer == null) {
            throw new NoBuyerFoundException("No such Buyer found");
        }
        Item tempItem;
        List<Item> items = new ArrayList<>();
        List<Integer> amountItems = new ArrayList<>();
        Map<Item.Category, Item.SaleAmount> sales = Item.getSales();
        int totalSale = 0;
        for (int i = 0; i < amounts.size(); i++) {
            tempItem = null;

            if (amounts.get(i) <= 0) {
                throw new IllegalAmountException("Amount of " + itemNames.get(i) + " is: " + amounts.get(i) + "\nExpected > 0");
            }
            for (Item item : stock) {
                if (itemNames.get(i).equals(item.getName())) {
                    tempItem = item;
                    break;
                }
            }
            if (tempItem == null) {
                throw new NoItemFoundException("Shop doesn't have " + itemNames.get(i));
            }

            if (sales.containsKey(tempItem.getCategory())) {
                totalSale += sales.get(tempItem.getCategory()).getValue();
                if (totalSale > 50) {
                    throw new TooMuchSaleException(String.format("Total sale amount reached %d%% when adding %s.(Max allowed total sale is 50%%)", totalSale, tempItem.getName()));
                }
            }

            items.add(tempItem);
            amountItems.add(amounts.get(i));
        }
        return new Order(tempBuyer, items, amountItems);
    }

}
