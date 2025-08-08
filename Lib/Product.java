package Lib;

public class Product {
    private final String ProductID;
    private final String ProductName;
    private final double price;

    // Rep Invariant (RI):
    // - ProductID and ProductName must not be null or empty.
    // - Price must be non-negative.
    //
    // Abstraction Function (AF):
    // - AF(ProductID, ProductName, price) = A product with a unique identifier, a name, and a price.

    /**
     * ตรวจสอบว่า Rep Invariant ถูกต้องหรือไม่
     */
    private void checkRep() {
        if (ProductID == null || ProductID.isBlank()) {
            throw new RuntimeException("RI violated: Product ID cannot be null or empty");
        }
        if (ProductName == null || ProductName.isBlank()) {
            throw new RuntimeException("RI violated: Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new RuntimeException("RI violated: Price cannot be negative");
        }
    }

    /**
     * สร้างออบเจ็กต์ Product
     * @param ProductID รหัสสินค้า ห้ามเป็น null หรือว่าง
     * @param ProductName ชื่อสินค้า ห้ามเป็น null หรือว่าง
     * @param price ราคาสินค้า ต้องไม่ติดลบ
     */
    public Product(String ProductID, String ProductName, double price) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.price = price;
        checkRep(); // ตรวจสอบ Rep Invariant หลังจากสร้างออบเจ็กต์
    }

    public String getProductID() {
        return ProductID;
    }
    public String getProductName() {
        return ProductName;
    }
    public double getPrice() {
        return price;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return ProductID.equals(product.ProductID);
    }
}
