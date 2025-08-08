package Lib;

/**
 * ADT ที่เปลี่ยนแปลงได้ (Mutable) สำหรับเก็บข้อมูลสินค้า รายการในตะกร้าสินค้า
 */
public class CartItem {
    private final Product product;
    private int quantity;

    // Rep Invariant (RI):
    // - Product must not be null.
    // - Quantity must be non-negative.
    //
    // Abstraction Function (AF):
    // - AF(product, quantity) = An item in a shopping cart for the specified product with a given quantity.
    // with the specified quantity.

    /**
     * ตรวจสอบว่า Rep Invariant ถูกต้องหรือไม่
     */
    private void checkRep() {
        if (product == null) {
            throw new RuntimeException("RI violated: Product cannot be null");
        }
        if (quantity < 0) {
            throw new RuntimeException("RI violated: Quantity cannot be negative");
        }
    }

    /**
     * สร้างรายการสินค้าในตะกร้า
     * @param product อ็อบเจ็กต์ Product
     * @param quantity จำนวนสินค้าที่ต้องการในตะกร้า ต้องไม่ติดลบ
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        checkRep();
    }

    /**
     * @return อ็อบเจ็กต์ Product
     */
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * เพิ่มจำนวนสินค้าในรายการนี้
     * @param amount จำนวนที่ต้องการเพิ่ม ต้องไม่ติดลบ
     */
    public void increasesQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
        checkRep(); // ตรวจสอบหลังการเปลี่ยนแปลงสถานะ
    }
    
}
