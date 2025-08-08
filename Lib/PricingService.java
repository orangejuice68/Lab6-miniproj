package Lib;

import java.util.ArrayList;

import Lib.Discount.DefaultPricingStrategy;
import Lib.Discount.DiscountStrategy;

public class PricingService {
    private record StrategyRule(String sku, DiscountStrategy strategy) {}

    private final ArrayList<StrategyRule> strategies = new ArrayList<>();

    private final DiscountStrategy defaultStrategy = new DefaultPricingStrategy();

    /**
     * ลงทะเบียนกลยุทธ์การคำนวณราคาสำหรับ SKU ที่กำหนด
     * หากมีโปรโมชันที่ตรงกับ SKU นี้อยู่แล้ว จะถูกแทนที่ด้วยอันใหม่
     * @param sku รหัสสินค้าที่ต้องการผูกกับโปรโมชัน
     * @param strategy กลยุทธ์ส่วนลดที่จะใช้
     */
    public void addStrategy(String sku, DiscountStrategy strategy) {
        StrategyRule ruleToRemove = null;
        for (StrategyRule rule : strategies) {
            if (rule.sku.equals(sku)) {
                ruleToRemove = rule;
                break;
            }
        }
        if (ruleToRemove != null) {
            strategies.remove(ruleToRemove);   
        }
        strategies.add(new StrategyRule(sku, strategy));
    }

    /**
     * คำนวณราคาสุทธิของสินค้า 1 รายการโดยใช้กลยุทธ์ที่เหมาะสม
     * @param item รายการสินค้าที่ต้องการคำนวณราคา
     * @return ราคาสุทธิหลังหักส่วนลด
     */
    public double calculateItemPrice(CartItem item) {
        String sku = item.getProduct().getProductID();
        for (StrategyRule rule : strategies) {
            if (rule.sku().equals(sku)) {
                return rule.strategy().calculatePrice(item);
            }
        }
        return defaultStrategy.calculatePrice(item);
    }
}
