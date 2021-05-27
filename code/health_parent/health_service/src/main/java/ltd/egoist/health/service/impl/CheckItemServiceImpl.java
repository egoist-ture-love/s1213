package ltd.egoist.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ltd.egoist.entity.HealthException;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.dao.CheckItemDao;
import ltd.egoist.health.pojo.CheckItem;
import ltd.egoist.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Classname CheckItemServiceImpl
 * @Description TODO
 * @Date 2021/5/19 20:31
 * @Created by Lenovo
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findByPage(QueryPageBean queryPageBean) {
        //使用pageHelper分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //如果有条件查询进行拼接
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        //之后你的查询语句就会被分页
        Page<CheckItem> page = checkItemDao.findByPage(queryPageBean.getQueryString());
        return new PageResult<CheckItem>(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(int id) throws HealthException {
        int count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            throw new HealthException("检察组已使用该元素");
        }else if(count == 0){
            checkItemDao.deleteById(id);
        }
    }

    @Override
    public CheckItem findById(int id) {
       return checkItemDao.findById(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

}
