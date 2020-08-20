package threadLocal;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class User {
    private Integer userId;
    private String name;
//    private LocalDate birthday;
}