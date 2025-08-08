package Lib;

import java.util.ArrayList;


public class ShoppingCart {
    
    
    private final ArrayList<CartItem> ShoppingCart = new ArrayList<>();
    private final PricingService pricingService;
    private final ProductCatalog productCatalog;

    public void checkRep() {
        if (ShoppingCart == null) {
            throw new RuntimeException("RI violated:ShoppingCart cannot be null ");
        }
        for (CartItem item : ShoppingCart) {
            if (item.getProduct() == null) {
                throw new RuntimeException("RI violated:CartItem product cannot be null ");
            }
            if (item.getQuantity() < 0) {
                throw new RuntimeException("RI violated:CartItem quantity cannot be negative ");
            }
        }
    }

    //RI: ArrayList ภายในต้องไม่เป็น null และห้ามมี Product ซ้ำกันใน CartItem ที่แตกต่างกัน
    public void addItem(String sku, int quantity) {

        Product product = productCatalog.findByID(sku);
        if (product == null || quantity <= 0) {
            return;
        }
        
        for (CartItem Item : ShoppingCart) {
            if (Item.getProduct().getProductID().equals(sku)) {
                Item.increasesQuantity(quantity);
                return;
            }
        }
        ShoppingCart.add(new CartItem(product, quantity));
    }

    public void removeItem(String sku) {
        Product product = productCatalog.findByID(sku);
        if (product == null) {
            return;
        }

        ShoppingCart.removeIf(cartItem -> cartItem.getProduct().equals(product));
        checkRep();
    }

    public void clearCart() {
        ShoppingCart.clear();
        checkRep();
    }

    public int getItemCount() {
        return ShoppingCart.size();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : ShoppingCart) {
            total += pricingService.calculateItemPrice(item);
        }
        return total;
    }

    public ShoppingCart(PricingService pricingService, ProductCatalog productCatalog) {
        this.pricingService = pricingService;
        this.productCatalog = productCatalog;
        checkRep();
    }

    

}
