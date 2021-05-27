package ltd.egoist.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ltd.egoist.constant.MessageConstant;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.entity.Result;
import ltd.egoist.health.pojo.CheckItem;
import ltd.egoist.health.service.CheckItemService;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname CheckItemController
 * @Description TODO
 * @Date 2021/5/19 20:26
 * @Created by Lenovo
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<CheckItem> list = checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @PostMapping("/findByPage")
    public Result findByPage(@RequestBody QueryPageBean queryPageBean) {
        System.out.println("到达了");
        PageResult<CheckItem> pageResult = checkItemService.findByPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        checkItemService.delete(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    @GetMapping("findById")
    public Result findById(int id) {
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }
    @PostMapping("update")
    public Result update(@RequestBody CheckItem checkItem){
        checkItemService.update(checkItem);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

}
