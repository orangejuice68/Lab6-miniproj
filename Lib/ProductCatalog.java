package Lib;

import java.util.ArrayList;

/**
 * คลาสสำหรับจัดการแคตตาล็อกสินค้าทั้งหมด (Repository)
 */
public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();

    // RI: products list must not be null, contains no null elements, and no duplicate products.
    // Af: AF(products) = A catalog of all avaliable products.

    private void checkRep() {
        if (products == null) {
            throw new RuntimeException("RI violated: Product list cannot be null");
        }
        // Checf for duplicate products
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    throw new RuntimeException("RI violated: catalog contains duplicate products: " + products.get(i).getProductID());
                }
            }
        }
        
    }

    public ProductCatalog() {
        checkRep();
    }

    /**
     * เพิ่มสินค้าใหม่ลงในแคตตาล็อก
     * @param product สินค้าที่ต้องการเพิ่ม
     */
    public void addProduct(Product product) {
        if (product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }

    /**
     * ค้นหาสินค้าตามรหัสสินค้า
     * @param ProductID รหัสสินค้าที่ต้องการค้นหา
     * @return อ็อบเจ็กต์ Product ถ้าพบ หรือ null ถ้าไม่พบ
     */
    public Product findByID(String ProductID) {
        for (Product p : products) {
            if (p.getProductID().equals(ProductID)) {
                return p;
            }
        }
        return null;
    }
}
