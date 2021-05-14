package ltd.egoist.test01;

import ltd.egoist.InternalStorage;
import org.junit.Test;

/**
 * @Classname Test01
 * @Description TODO
 * @Date 2021/5/10 18:48
 * @Created by Lenovo
 */
public class Test01 {
    @Test
    public void method(){
        InternalStorage internalStorage = new InternalStorage();
        internalStorage.doFirstFit(15);
        internalStorage.doFirstFit(15);
        internalStorage.doFirstFit(15);
        internalStorage.printLinkList();
        internalStorage.recycleSpace(0);
        internalStorage.printLinkList();
        internalStorage.recycleSpace(2);
        internalStorage.printLinkList();


    }
}
