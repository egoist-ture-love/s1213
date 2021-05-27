package ltd.egoist.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ltd.egoist.constant.MessageConstant;
import ltd.egoist.entity.PageResult;
import ltd.egoist.entity.QueryPageBean;
import ltd.egoist.entity.Result;
import ltd.egoist.health.pojo.Setmeal;
import ltd.egoist.health.service.SetMealService;
import ltd.egoist.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Classname SetmealController
 * @Description TODO
 * @Date 2021/5/23 20:25
 * @Created by Lenovo
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    private SetMealService setMealService;

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        //获取文件名称截取到后缀名
        String originalFilename = imgFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成唯一文件名,拼接后缀名
        String filename = UUID.randomUUID() + extension;
        //调用七牛云进行上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            Map<String, String> map = new HashMap<>();
            map.put("imgName", filename);
            map.put("domain", QiNiuUtils.DOMAIN);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回数据给页面
        return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        setMealService.add(setmeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Setmeal> page = setMealService.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, page);
    }

    @GetMapping("/findById")
    public Result findById(int id) {
        Setmeal setmeal = setMealService.findById(id);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("setmeal",setmeal);
        map.put("domain",QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,map);
    }

    @GetMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(int id) {
        List<Integer> list = setMealService.findCheckgroupIdsBySetmealId(id);
        System.out.println(list);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    }
    @PostMapping("/update")
    public Result update(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        setMealService.update(setmeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        setMealService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
}
