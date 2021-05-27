package ltd.egoist.health.dao;

import ltd.egoist.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname OrderSettingDao
 * @Description TODO
 * @Date 2021/5/25 19:13
 * @Created by Lenovo
 */
public interface OrderSettingDao {
    OrderSetting findByOrderDate(Date orderDate);

    void updateNumber(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<Map<String, Integer>> getOrderSettingByMonth(Map<String, String> map);
}
