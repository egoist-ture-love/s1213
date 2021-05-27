package ltd.egoist.health.dao;

import com.github.pagehelper.Page;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.pojo.CheckItem;

import java.util.List;

/**
 * @Classname CheckItemDao
 * @Description TODO
 * @Date 2021/5/19 20:35
 * @Created by Lenovo
 */
public interface CheckItemDao {
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    Page<CheckItem> findByPage(String queryString);

    int findCountByCheckItemId(int id);

    void deleteById(int id);

    CheckItem findById(int id);

    void update(CheckItem checkItem);
}
