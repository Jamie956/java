package threadLocal;

public class UserService {
    public void addUser() {
        System.out.println(Thread.currentThread().getName() + "  添加用户信息:  " + UserContext.get());
    }
}