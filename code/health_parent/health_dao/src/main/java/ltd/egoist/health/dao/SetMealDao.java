package ltd.egoist.health.dao;

import com.github.pagehelper.Page;
import ltd.egoist.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname SetMealDao
 * @Description TODO
 * @Date 2021/5/23 21:38
 * @Created by Lenovo
 */
public interface SetMealDao {
    void add(Setmeal setmeal);

    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    Page<Setmeal> findPage(String queryString);

    Setmeal findById(int id);

    List<Integer> findCheckgroupIdsBySetmealId(int id);

    void update(Setmeal setmeal);

    void deleteSetmealCheckGroup(Integer id);

    int findOrderCountBySetmealId(int id);

    void deleteById(int id);

    List<String> findImgs();

    List<Setmeal> findAll();

    Setmeal findDetailById(int id);
}
