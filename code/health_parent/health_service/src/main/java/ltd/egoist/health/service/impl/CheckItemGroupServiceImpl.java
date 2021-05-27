package ltd.egoist.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.xml.internal.ws.handler.HandlerException;
import ltd.egoist.entity.HealthException;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.dao.CheckGroupDao;
import ltd.egoist.health.pojo.CheckGroup;
import ltd.egoist.health.pojo.CheckItem;
import ltd.egoist.health.service.CheckItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Classname CheckItemGroupImpl
 * @Description TODO
 * @Date 2021/5/22 17:54
 * @Created by Lenovo
 */
@Service(interfaceClass = CheckItemGroupService.class)
public class CheckItemGroupServiceImpl implements CheckItemGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    @Transactional
    public void add(CheckGroup checkItemGroup, Integer[] list) {
        //添加检查组到检查组表
     checkGroupDao.add(checkItemGroup);
     //获取自增长的id将 遍历list与id一起添加
        Integer checkGroupId = checkItemGroup.getId();
        if(null != list) {
            for (Integer checkitemId : list) {
                // 添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId, checkitemId);
            }
        }

    }

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<CheckGroup> page=checkGroupDao.findPage(queryPageBean.getQueryString());
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public CheckItem findById(int id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void update(CheckGroup checkItemGroup, Integer[] list) {
        //更新检查组信息
        checkGroupDao.update(checkItemGroup);
        //根据id删除关系列表
        checkGroupDao.deleteById(checkItemGroup.getId());
        //添加新关系
        if(null != list) {
            for (Integer checkitemId : list) {
                // 添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkItemGroup.getId(), checkitemId);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) throws HealthException {
        //1.查看是否与顶单项目有关联
        int count = checkGroupDao.findSetmealCountByCheckGroupId(id);
        if(count > 0){
            throw new HealthException("又被使用的订单不可删除");
        }
            //1.1有关联抛出异常
        //没关联继续删除
            //先删除关联表单的
        checkGroupDao.deleteById(id);
        //再删除检查组的
        checkGroupDao.deleteGroup(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
