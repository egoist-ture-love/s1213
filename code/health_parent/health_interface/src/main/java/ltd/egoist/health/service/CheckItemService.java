package ltd.egoist.health.service;

import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.pojo.CheckItem;

import java.util.List;

/**
 * @Classname CheckItemService
 * @Description TODO
 * @Date 2021/5/19 20:31
 * @Created by Lenovo
 */
public interface CheckItemService {
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    PageResult<CheckItem> findByPage(QueryPageBean queryPageBean);

    void delete(int id);

    CheckItem findById(int id);

    void update(CheckItem checkItem);
}
