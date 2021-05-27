package ltd.egoist.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import ltd.egoist.entity.HealthException;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.pojo.Setmeal;

import java.util.List;

/**
 * @Classname SetMealService
 * @Description TODO
 * @Date 2021/5/23 21:30
 * @Created by Lenovo
 */
public interface SetMealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    Setmeal findById(int id);

    List<Integer> findCheckgroupIdsBySetmealId(int id);

    void update(Setmeal setmeal, Integer[] checkgroupIds);

    void deleteById(int id) throws HealthException;

    List<String> findImgs();

    List<Setmeal> findAll();

    Setmeal findDetailById(int id);
}
