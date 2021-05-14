package ltd.egoist.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 包名:com.itheima.pojo
 *
 * @author Leevi
 * 日期2020-08-13  16:06
 */
@Data
public class LinkMan implements Serializable {
    private Integer id;
    private Integer age;
    private String name;
    private String sex;
    private String qq;
    private String address;
    private String email;
}
