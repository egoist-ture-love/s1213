package ltd.egoist.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import ltd.egoist.entity.HealthException;
import ltd.egoist.health.dao.OrderSettingDao;
import ltd.egoist.health.pojo.OrderSetting;
import ltd.egoist.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname OrderSettingServiceImpl
 * @Description TODO
 * @Date 2021/5/25 19:12
 * @Created by Lenovo
 */
@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Override
    @Transactional
    public void add(List<OrderSetting> orderSettingList) throws HealthException{
        //遍历List集合
        for (OrderSetting orderSetting : orderSettingList) {
            //通过日期查询预约信息
           OrderSetting os = orderSettingDao.findByOrderDate(orderSetting.getOrderDate());
            System.out.println(os);
           if(os!=null){
               //当前时间不为空
                    //比对大小
               if(os.getReservations() > orderSetting.getNumber()){
                   throw new HealthException(orderSetting.getOrderDate()+"中已预约数量不能大于可预约数量");
               }else {
                   //更新大小
                   orderSettingDao.updateNumber(orderSetting);
               }
           }else {
               //当前时间为空
               //进行添加
               orderSettingDao.add(orderSetting);
           }
        }
    }

    @Override
    public List<Map<String, Integer>> getOrderSettingByMonth(String month) {
        // 拼接开始日期
        String startDate = month + "-01";
        // 结束日期
        String endDate = month + "-31";
        // 调用dao查询
        Map<String,String> map = new HashMap<String,String>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        return orderSettingDao.getOrderSettingByMonth(map);
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) throws HealthException{
        //通过日期查询预约信息
        OrderSetting os = orderSettingDao.findByOrderDate(orderSetting.getOrderDate());
        System.out.println(os);
        if(os!=null){
            //当前时间不为空
            //比对大小
            if(os.getReservations() > orderSetting.getNumber()){
                throw new HealthException(orderSetting.getOrderDate()+"中已预约数量不能大于可预约数量");
            }else {
                //更新大小
                orderSettingDao.updateNumber(orderSetting);
            }
        }else {
            //当前时间为空
            //进行添加
            orderSettingDao.add(orderSetting);
        }
    }
}
