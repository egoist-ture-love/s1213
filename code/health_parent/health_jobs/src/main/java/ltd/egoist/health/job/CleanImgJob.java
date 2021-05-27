package ltd.egoist.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import ltd.egoist.health.service.SetMealService;
import ltd.egoist.utils.QiNiuUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname CleanImgJob
 * @Description TODO
 * @Date 2021/5/25 10:56
 * @Created by Lenovo
 */
@Component
public class CleanImgJob {
    @Reference
    private SetMealService setMealService;

    public void cleanImg() {
        //1.查询七牛所有图片
        List<String> list = QiNiuUtils.listFile();
        //2.查询数据库所有图片
        List<String> imgsList = setMealService.findImgs();
        //3.使用七牛图片减去数据库图片---要删除的图片
        list.removeAll(imgsList);
        //4.删除七牛上面的图片
        String[] strings = list.toArray(new String[]{});
        QiNiuUtils.removeFiles(strings);
    }
}
