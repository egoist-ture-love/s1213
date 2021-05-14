package ltd.egoist.controller;

import ltd.egoist.utils.UploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Classname FileController
 * @Description TODO
 * @Date 2021/5/4 10:35
 * @Created by Lenovo
 */

/**
 * 配置文件上传需要做的事情:获取客户端传入的文件描述信息
 *                      创建file文件夹
 *                      使用输出流将客户端上传的文件输出到file文件夹中
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @RequestMapping("/pic")
    public String uploadPic(String pdesc, MultipartFile upload, HttpServletRequest request){
        System.out.println("文件描述信息是"+pdesc);
        //创建file文件夹
            //1.获取文件的目录地址
        String files = request.getSession().getServletContext().getRealPath("files/"+ UploadUtils.getDir());
        System.out.println(files);
        File file = new File(files);
        //这个file文件夹不存在
        if(!file.exists()){
            //直接创建这个文件夹
            file.mkdirs();
        }
        String filename =upload.getOriginalFilename();
        String uuidName = UploadUtils.getUUIDName(filename);
        System.out.println(filename);
        try {
           upload.transferTo(new File(file,uuidName));//将文件上传到文件夹中
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
