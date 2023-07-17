import java.util.*;

public class Main {
    public static void main(String[] args) {


        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Shampoo", Item.Category.HYGIENE_PRODUCTS, 150));
        items.add(new Item("Toothpaste", Item.Category.HYGIENE_PRODUCTS, 100));
        items.add(new Item("Body Wash", Item.Category.HYGIENE_PRODUCTS, 100));
        items.add(new Item("Banana", Item.Category.FRUIT, 75));

        ArrayList<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("Ilia", 1000));
        buyers.add(new Buyer("Sabina", 5000));

        Shop shop = new Shop(items, buyers);
        Random random = new Random();
        Item.addRandomSale(Item.Category.HYGIENE_PRODUCTS, random);
        try {
            Order order = shop.deal("Ilia", Arrays.asList("Shampoo", "Toothpaste", "Body Wash"), Arrays.asList(2, 2, 2));
            System.out.println(order.getTotalPrice());
        } catch (IllegalListsException e) {
            e.printStackTrace();
        } catch (NoBuyerFoundException e) {
            e.printStackTrace();
        } catch (NoItemFoundException e) {
            e.printStackTrace();
        } catch (IllegalAmountException e) {
            e.printStackTrace();
        } catch (TooMuchSaleException e) {
            e.printStackTrace();
        }

        System.out.println("-------------");
        System.out.println("Total deals: " + Order.getCounter());
    }


}