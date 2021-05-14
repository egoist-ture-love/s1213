package ltd.egoist.service.impl;

import ltd.egoist.dao.LinkManDao;
import ltd.egoist.pojo.LinkMan;
import ltd.egoist.service.LinkManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname LinkmanServiceImpl
 * @Description TODO
 * @Date 2021/5/3 18:49
 * @Created by Lenovo
 */
@Service
public class LinkManServiceImpl implements LinkManService {
    @Autowired
    private LinkManDao linkManDao;
    @Override
    public List<LinkMan> findAll() {
        return linkManDao.findAll();
    }
}
