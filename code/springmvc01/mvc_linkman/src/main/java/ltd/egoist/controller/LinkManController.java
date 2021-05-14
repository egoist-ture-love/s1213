package ltd.egoist.controller;

import ltd.egoist.pojo.LinkMan;
import ltd.egoist.result.Result;
import ltd.egoist.service.LinkManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname LinkmanController
 * @Description TODO
 * @Date 2021/5/3 18:50
 * @Created by Lenovo
 */
@RestController
@RequestMapping("/linkman")
public class LinkManController {
    @Autowired
    private LinkManService linkManService;
    @RequestMapping("/findAll")
    public Result findAll(){
    
        return new Result(true,"查询成功",linkManService.findAll());
    }
}
