package Test01;

import ltd.egoist.Memory;
import org.junit.Test;

/**
 * @Classname Test01
 * @Description TODO
 * @Date 2021/5/10 18:56
 * @Created by Lenovo
 */
public class Test01 {
    @Test
    public void method(){
        Memory memory = new Memory();
        memory.fristFit(15);
        memory.fristFit(15);
        memory.fristFit(15);
        memory.showZones();
        memory.collection(2);
        memory.showZones();
    }
}
