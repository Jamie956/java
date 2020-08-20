package threadLocal;

public class UserContext {
    private static final ThreadLocal<User> context = new ThreadLocal<>();


    public static void set(User user) {
        context.set(user);
    }

    public static User get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }
}