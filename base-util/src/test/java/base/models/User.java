package base.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by base on 2016/3/28.
 */
@XmlRootElement
public class User implements Serializable {

    private String name;
    private String test;
    private char sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", test='" + test + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
