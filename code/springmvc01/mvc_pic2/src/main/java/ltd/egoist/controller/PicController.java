package ltd.egoist.controller;

import ltd.egoist.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Classname PicController
 * @Description TODO
 * @Date 2021/5/4 16:14
 * @Created by Lenovo
 */
@ResponseBody
@Controller
@RequestMapping("/file")
public class PicController {
    @RequestMapping("/pic")
    public String upload(String desc, MultipartFile picture, HttpServletRequest request){
        System.out.println(desc);
        //2.创建文件夹
            //2.1文件夹存放目录
        String realPath = request.getSession().getServletContext().getRealPath("pic/" + UploadUtils.getDir());
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //1.接受图片
        String originalFilename = picture.getOriginalFilename();
        String uuidName = UploadUtils.getUUIDName(originalFilename);
        try {

            //3.将图片放入文件夹中
            picture.transferTo(new File(file,uuidName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
