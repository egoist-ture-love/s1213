package ltd.egoist.dao;

import ltd.egoist.pojo.LinkMan;

import java.util.List;

/**
 * @Classname LinkManDao
 * @Description TODO
 * @Date 2021/5/3 18:48
 * @Created by Lenovo
 */
public interface LinkManDao {
    List<LinkMan> findAll();
}
