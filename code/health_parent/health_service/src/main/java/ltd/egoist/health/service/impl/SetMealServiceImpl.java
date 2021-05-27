package ltd.egoist.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ltd.egoist.entity.HealthException;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.health.dao.SetMealDao;
import ltd.egoist.health.pojo.Setmeal;
import ltd.egoist.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Classname SetMealImplService
 * @Description TODO
 * @Date 2021/5/23 21:30
 * @Created by Lenovo
 */
@Service(interfaceClass = SetMealService.class)

public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //1.向setmeal表中添加此元素
        setMealDao.add(setmeal);
        //2.获取id
        Integer setmealId = setmeal.getId();
        //3.级联添加setmeal的id和checkeId
        if (checkgroupIds != null) {
            for (Integer checkgroupId : checkgroupIds) {
                setMealDao.addSetmealCheckGroup(setmealId, checkgroupId);
            }
        }

    }

    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Setmeal> page = setMealDao.findPage(queryPageBean.getQueryString());

        return new PageResult<Setmeal>(page.getTotal(), page.getResult());
    }

    @Override
    public Setmeal findById(int id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(int id) {
        return setMealDao.findCheckgroupIdsBySetmealId(id);
    }

    @Override
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        //1.update检查项目信息
        setMealDao.update(setmeal);
        //2.获取id删除检擦组信息
        Integer id = setmeal.getId();
        setMealDao.deleteSetmealCheckGroup(id);
        //3.添加新关系
        if (null != checkgroupIds) {
            for (Integer checkgroupId : checkgroupIds) {
                setMealDao.addSetmealCheckGroup(id, checkgroupId);
            }
        }
    }

    @Override
    public void deleteById(int id) throws HealthException {
        //1、判断订单是否被使用
        int count = setMealDao.findOrderCountBySetmealId(id);
        //2.使用了报错
        if (count > 0) {
            throw new HealthException("已经被订单使用了,无法删除");
        }
        //3.没使用
        //先删除套餐与检查组关系
        setMealDao.deleteSetmealCheckGroup(id);
        //在删除套餐数据
        setMealDao.deleteById(id);
    }

    @Override
    public List<String> findImgs() {
        return setMealDao.findImgs();
    }

    @Override
    public List<Setmeal> findAll() {
        return setMealDao.findAll();
    }

    @Override
    public Setmeal findDetailById(int id) {
        return setMealDao.findDetailById(id);

    }
}
