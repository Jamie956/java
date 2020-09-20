package basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = -2687432631518129972L;

    private String name;

    public Person() {
        System.out.println(1);
        this.name = "tom";
    }

}
