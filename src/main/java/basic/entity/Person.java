package basic.entity;

import lombok.Data;

@Data
public class Person {

    private String name;

    public Person() {
        System.out.println(1);
        this.name = "tom";
    }

}
