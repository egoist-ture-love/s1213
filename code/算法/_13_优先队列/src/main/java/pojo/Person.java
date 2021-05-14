package pojo;

/**
 * @Classname pojo.Person
 * @Description TODO
 * @Date 2021/5/11 20:06
 * @Created by Lenovo
 */
public class Person implements Comparable{
    private String name;
    private Integer boneBreak;

    public Person(String name, Integer boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public String toString() {
        return "pojo.Person{" +
                "name='" + name + '\'' +
                ", boneBreak=" + boneBreak +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.boneBreak - ((Person)o).boneBreak;
    }
}
