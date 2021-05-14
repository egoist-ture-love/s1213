package ltd.egoist.service.impl;

import ltd.egoist.dao.ItemsDao;
import ltd.egoist.pojo.Items;
import ltd.egoist.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname ItemsServiceImpl
 * @Description TODO
 * @Date 2021/5/12 16:10
 * @Created by Lenovo
 */
@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsDao itemsDao;
    @Override
    public Items findById(int id) {
        return itemsDao.findById(id);
    }

    @Override
    public List<Items> findAll() {

        return itemsDao.findAll();
    }
}
