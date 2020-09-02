package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hi {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(3);
        list.add(2);
        list.add(7);

        int i = list.stream().max(Comparator.comparing(a -> a)).orElse(null);

        Map<Integer,String> userMap1 = userList.stream().collect(Collectors.toMap(User::getId,User::getName));





        List<Student> students = new ArrayList<>();

        students.add(new Student(1,"张三",90));
        students.add(new Student(2,"李四",60));
        students.add(new Student(3,"王五",30));
        students.add(new Student(4,"赵六",85));

        int studentId = 3;
        Student student = students.stream().filter(o -> o.getId() == studentId).findAny().orElse(null);

    }
}
