package ltd.egoist.controller;

import ltd.egoist.pojo.Items;
import ltd.egoist.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Classname Controller
 * @Description TODO
 * @Date 2021/5/12 16:53
 * @Created by Lenovo
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @GetMapping("/findAll")
    public String list(Model model){
        //集合查询
        System.out.println("11111111111111111111111111");
        List<Items> items = itemsService.findAll();
        System.out.println(items);
        //存入回显
        model.addAttribute("items",items);
        return "items";
    }
}
