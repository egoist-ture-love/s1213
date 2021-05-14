package ltd.egoist.service;

import ltd.egoist.pojo.Items;

import java.util.List;

/**
 * @Classname ItemsService
 * @Description TODO
 * @Date 2021/5/12 16:09
 * @Created by Lenovo
 */
public interface ItemsService {
    Items findById(int id);

    List<Items> findAll();

}
