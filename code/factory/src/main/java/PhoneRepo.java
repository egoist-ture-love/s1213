/**
 * @Classname PhoneRepo
 * @Description TODO
 * @Date 2021/5/7 9:10
 * @Created by Lenovo
 */
public class PhoneRepo {
    public Phone getPhone(){
        return new HuaweiPhone();
    }
}
