package ltd.egoist.dao;

import ltd.egoist.pojo.Items;

import java.util.List;

/**
 * @Classname ItemsDao
 * @Description TODO
 * @Date 2021/5/12 10:46
 * @Created by Lenovo
 */
public interface ItemsDao {
    Items findById(int id);

    List<Items> findAll();
}
