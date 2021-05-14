package ltd.egoist.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Classname User
 * @Description TODO
 * @Date 2021/5/2 19:59
 * @Created by Lenovo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer age;
    private String name;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private List<String> hobbies;
}
