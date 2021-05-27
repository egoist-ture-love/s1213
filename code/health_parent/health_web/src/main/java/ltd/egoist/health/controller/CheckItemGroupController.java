package ltd.egoist.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import ltd.egoist.constant.MessageConstant;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.entity.Result;
import ltd.egoist.health.pojo.CheckGroup;
import ltd.egoist.health.pojo.CheckItem;
import ltd.egoist.health.service.CheckItemGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname CheckItemGroup
 * @Description TODO
 * @Date 2021/5/22 17:52
 * @Created by Lenovo
 */
@RestController
@RequestMapping("/checkItemGroup")
public class CheckItemGroupController {
    @Reference
    private CheckItemGroupService checkItemGroupService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkItemGroup, Integer[] list) {

        checkItemGroupService.add(checkItemGroup,list);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    @PostMapping("/findPage")
    public Result findPage(@RequestBody  QueryPageBean queryPageBean){
        PageResult<CheckGroup> page =checkItemGroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,page);
    }

    @GetMapping("/findById")
    public Result findById(int id){
        CheckItem checkItem = checkItemGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItem);
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        List<Integer> integerList = checkItemGroupService.findCheckItemIdsByCheckGroupId(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,integerList);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkItemGroup, Integer[] list){
        checkItemGroupService.update(checkItemGroup,list);
        Result result = new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        return result;
    }
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        checkItemGroupService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroups = checkItemGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroups);
    }

}
