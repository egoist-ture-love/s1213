package ltd.egoist.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ltd.egoist.constant.MessageConstant;
import ltd.egoist.entity.Result;
import ltd.egoist.health.pojo.OrderSetting;
import ltd.egoist.health.service.OrderSettingService;
import ltd.egoist.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname OrderSettingController
 * @Description TODO
 * @Date 2021/5/25 19:11
 * @Created by Lenovo
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;
    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        //解析excel内容
        try {
            List<String[]> stringsList = POIUtils.readExcel(excelFile);
            //转化位OrderSetting对象内容
            List<OrderSetting> orderSettingList = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(POIUtils.DATE_FORMAT);
            Date orderDate = null;
            OrderSetting os = null;
            for (String[] strings : stringsList) {
                orderDate = simpleDateFormat.parse(strings[0]);
                Integer value = Integer.valueOf(strings[1]);
                os = new OrderSetting(orderDate,value);
                orderSettingList.add(os);
            }
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }
    /**
     * 通过月份查询预约设置信息
     */
    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month){
        // 调用服务端查询
        List<Map<String,Integer>> data = orderSettingService.getOrderSettingByMonth(month);
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,data);
    }
    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        orderSettingService.editNumberByDate(orderSetting);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }
}
