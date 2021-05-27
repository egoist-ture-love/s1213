package ltd.egoist.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ltd.egoist.constant.MessageConstant;
import ltd.egoist.entity.Result;
import ltd.egoist.health.pojo.Setmeal;
import ltd.egoist.health.service.SetMealService;
import ltd.egoist.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname SetmealMobileController
 * @Description TODO
 * @Date 2021/5/27 7:18
 * @Created by Lenovo
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {
    @Reference
    private SetMealService setmealService;

    /**
     * 查询所有
     */
    @GetMapping("/getSetmeal")
    public Result getSetmeal() {
        // 查询所有的套餐
        List<Setmeal> list = setmealService.findAll();
        // 套餐里有图片有全路径吗? 拼接全路径
        list.forEach(s -> {
            s.setImg(QiNiuUtils.DOMAIN + s.getImg());
        });
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
    }

    @GetMapping("/findById")
    public Result findById(int id) {
        Setmeal setmeal = setmealService.findDetailById(id);
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
