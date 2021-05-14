package ltd.egoist.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Classname Items
 * @Description TODO
 * @Date 2021/5/12 10:27
 * @Created by Lenovo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    private Integer id;
    private String name;
    private Float price;
    private String pic;
    private Date createtime;
    private String detail;

}
