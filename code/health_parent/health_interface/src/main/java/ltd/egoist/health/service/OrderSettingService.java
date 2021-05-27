package ltd.egoist.health.service;

import ltd.egoist.entity.HealthException;
import ltd.egoist.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Classname OrderSettingService
 * @Description TODO
 * @Date 2021/5/25 19:12
 * @Created by Lenovo
 */
public interface OrderSettingService {
    void add(List<OrderSetting> orderSettingList) throws HealthException;

    List<Map<String, Integer>> getOrderSettingByMonth(String month);

    void editNumberByDate(OrderSetting orderSetting)throws HealthException;
}
