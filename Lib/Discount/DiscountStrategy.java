package Lib.Discount;
import Lib.*;

/**
 * Interface สำหรับกลยุทธ์การคำนวณสราคาสินค้า
 */
public interface DiscountStrategy {
    /**
     * คำนวณส่วนลดสำหรับรายการสินค้า 1 รายการ
     * @param item รายการสินค้าในตะกร้า
     * @return ราคาสุทธิหลังหักส่วนลด
     */
    double calculatePrice(CartItem item);

}
