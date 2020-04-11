package generics;

public class App {

	public static void main(String[] args) {
		//定义泛型，类里的方法参数类型也就确定了
	    Point<String> p = new Point<>() ;
	    p.setX("balabala");
	    System.out.println(p.x);
	}
}

class Point<T> { 
    public T x ;  
    public void setX(T x) {
        this.x = x;
    }
}