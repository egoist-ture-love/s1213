package ltd.egoist.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * @Classname Compare
 * @Description 比较器类进行比较
 * @Date 2021/5/9 15:52
 * @Created by Lenovo
 */
public class Compare implements Comparator<Map.Entry<Object, Integer>>{
    /**
     * 重写比较器方法
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Map.Entry<Object, Integer> o1, Map.Entry<Object, Integer> o2) {
        return o1.getValue() - o2.getValue();
    }
}
