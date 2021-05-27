package ltd.egoist.health.service;

import ltd.egoist.entity.HealthException;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.pojo.CheckGroup;
import ltd.egoist.health.pojo.CheckItem;

import java.util.List;

/**
 * @Classname CheckItemGroupService
 * @Description TODO
 * @Date 2021/5/22 17:53
 * @Created by Lenovo
 */
public interface CheckItemGroupService {
    void add(CheckGroup checkItemGroup, Integer[] list);

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckItem findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void update(CheckGroup checkItemGroup, Integer[] list);

    void deleteById(int id) throws HealthException;

    List<CheckGroup> findAll();

}
