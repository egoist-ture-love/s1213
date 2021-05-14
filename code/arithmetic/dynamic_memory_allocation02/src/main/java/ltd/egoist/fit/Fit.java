package ltd.egoist.fit;

import java.util.LinkedList;
import java.util.Map;

/**
 * @Classname Fit
 * @Description TODO
 * @Date 2021/5/9 10:34
 * @Created by Lenovo
 */
public abstract class Fit {

    public void storageAllocationByList(int size, LinkedList<Integer> linkedList){};

    public void storageAllocationByMap(int size, Map<Object, Integer> map){};

    /**
     * 空间不足报错
     */
    public void error() {
        throw new IllegalArgumentException("暂时没有足够空间");
    }
}
