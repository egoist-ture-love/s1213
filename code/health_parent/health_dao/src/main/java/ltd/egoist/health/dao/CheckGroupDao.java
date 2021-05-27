package ltd.egoist.health.dao;


import com.github.pagehelper.Page;
import ltd.egoist.health.pojo.CheckGroup;
import ltd.egoist.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
public interface CheckGroupDao {

    /**
     * 添加检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组与检查项的关系
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findPage(String queryString);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    CheckItem findById(int id);

    void update(CheckGroup checkItemGroup);

    void deleteById(Integer id);

    int findSetmealCountByCheckGroupId(int id);

    void deleteGroup(int id);

    List<CheckGroup> findAll();

}
