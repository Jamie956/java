package com.jamie;

import com.alibaba.fastjson.JSONArray;
import com.jamie.concurrency.SyncObject;
import com.jamie.entity.*;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicMain {
    /**
     * 获取注解的值
     */
    @Test
    public void annotationTest() {
        MyAnnotation anno = TestAnno.class.getAnnotation(MyAnnotation.class);
        String a = anno.value();
    }

    /**
     * 执行顺序
     * 1父类静态代码块
     * 2子类静态代码块
     * 3父类代码块
     * 5父类构造函数
     * 4子类代码块
     * 6子类构造函数
     */
    @Test
    public void testOrder() {
        new InitOrderB();
    }

    /**
     * JDK代理
     */
    @Test
    public void jdkproxytest() {
        DynamicProxy dp = new DynamicProxy(new HelloImpl());
        IHello helloProxy = dp.getProxy();

        helloProxy.greeting();
    }

    /**
     * 深克隆，引用类型也会被克隆
     */
    @Test
    public void deepClone() {
        try {
            User user = new User(new Address("stress1"));
            User clone = (User) deepClone(user);

            System.out.println(user == clone);
            System.out.println(user.getAddress() == clone.getAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象 -> 字节输出流 -> 对象
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deepClone(Object object) throws IOException, ClassNotFoundException {
        //字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return ois.readObject();
    }

    /**
     * 反射获取成员变量、修改成员变量
     */
    @Test
    public void reflectFields() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Field nameField = person.getClass().getDeclaredField("name");
        nameField.setAccessible(true);

        String a = (String) nameField.get(person);
        nameField.set(person, "jim");
        String b = (String) nameField.get(person);
    }

    /**
     * 浅克隆，不克隆引用类型
     * 对象实现Cloneable，重写clone 方法，调用父类方法
     */
    @Test
    public void shallowClone() {
        try {
            Student student = new Student(new Address("stress"));
            Student clone = (Student) student.clone();

            System.out.println(student == clone);
            System.out.println(student.getAddress() == clone.getAddress());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void objectIO() {
        try {
            //字节输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //构建对象输出流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(new Person("tom"));

            //字节输出流
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            //读取对象输出流
            ObjectInputStream ois = new ObjectInputStream(bais);
            Person person = (Person) ois.readObject();

            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void charO() {
        Writer out = null;
        try {
            out = new FileWriter("a");
            out.write("hi");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void charIO2() {
        Reader in = null;
        Writer out = null;
        try {
            in = new FileReader("a");
            out = new FileWriter("b");
            char[] cbuf = new char[10];
            int len = -1;

            while ((len = in.read(cbuf)) != -1) {
                out.write(cbuf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //file output
    @Test
    public void fileOut() {
        OutputStream out = null;
        try {
            out = new FileOutputStream("a");
            out.write(new byte[]{65, 66, 67});
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //file input and output
    @Test
    public void fileIO() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream("a");
            out = new FileOutputStream("b");
            /**
             * way1, read byte by byte
             */
            int b = 0;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            /**
             * way2, read by bytes[]
             */
//            byte[] b = new byte[20];
//            int len = -1;
//            while ((len = in.read(b)) != -1) {
//                out.write(b, 0, len);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void notBlockingServer() {
        try {
            ServerSocket ss = new ServerSocket(8081);

            while (true) {
                Socket socket = ss.accept();
                OutputStream out = socket.getOutputStream();
                out.write("hi".getBytes());

                out.close();
                socket.close();
            }

//            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notBlockingClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);
            InputStream in = socket.getInputStream();
            Scanner scan = new Scanner(in);

            while (scan.hasNext()) {
                System.out.println(scan.next());
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程执行同一实例的带同一把锁的方法，顺序执行
     */
    @Test
    public void synctest1() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.syncMethod()).start();
        new Thread(() -> obj.syncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行不同实例的带不同锁的方法，不按顺序执行
     */
    @Test
    public void synctest2() throws InterruptedException {
        SyncObject x = new SyncObject();
        SyncObject y = new SyncObject();

        new Thread(() -> x.syncMethod()).start();
        new Thread(() -> y.syncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行同一个实例的带锁方法和不带锁方法，不按顺序执行
     */
    @Test
    public void synctest3() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.syncMethod()).start();
        new Thread(() -> obj.notSyncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行同一实例的带同一把锁的方法，顺序执行
     */
    @Test
    public void synctest4() throws InterruptedException {
        SyncObject obj = new SyncObject();
        new Thread(() -> obj.syncMethod()).start();
        new Thread(() -> obj.syncMethod2()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行带锁的静态方法，顺序执行
     * 类锁/全局锁
     */
    @Test
    public void synctest5() throws InterruptedException {
        new Thread(() -> SyncObject.staticSyncMethod()).start();
        new Thread(() -> SyncObject.staticSyncMethod2()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * 多线程执行实例的带锁方法和静态带锁方法，不按顺序执行
     */
    @Test
    public void synctest6() throws InterruptedException {
        SyncObject x = new SyncObject();
        new Thread(() -> x.syncMethod2()).start();
        new Thread(() -> SyncObject.staticSyncMethod()).start();

        //当前线程等待线程t1和t2执行完成
        Thread.sleep(3000);
    }

    /**
     * TimeUnit的 sleep方法
     */
    @Test
    public void timeunitTes() throws InterruptedException {
        System.out.println("sleep begin");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sleep end");
    }

    /**
     * timedWait 带锁方法，获取锁等待
     */
    public synchronized void work() {
        System.out.println("Begin Work");
        try {
            TimeUnit.SECONDS.timedWait(this, 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work End");
    }

    /**
     * atom
     */
    @Test
    public void atom() throws InterruptedException {
        AtomicInteger sharedValue = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    sharedValue.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(sharedValue.get());
    }

    /**
     * future task
     */
    @Test
    public void futureTaskTest() {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
            new Thread(futureTask).start();
            int a = futureTask.get();
            System.out.println();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void futureTaskTestPool() {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> 1);
            ExecutorService pool = Executors.newSingleThreadExecutor();
            pool.submit(futureTask);
            int a = futureTask.get();
            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * double 排序
     */
    @Test
    public void listdoubleorder() {
        String jsonString = "[{\"name\":\"a\",\"value\":0.01}, {\"name\":\"b\",\"value\":0.06}, {\"name\":\"c\",\"value\":0.3}]";

        //1
        JSONArray json = JSONObject.parseArray(jsonString);
        json.sort((o1, o2) -> {
            double value1 = ((JSONObject)o1).getDoubleValue("value") * 10000;
            double value2 = ((JSONObject)o2).getDoubleValue("value") * 10000;
            return (int) (value2 - value1);
        });

        //2
        json.sort((a, b) -> (int)(((JSONObject)a).getDoubleValue("value") - ((JSONObject)b).getDoubleValue("value")));

        //3
//        json.sort(Comparator.comparingDouble(e -> e.getDoubleValue("value")));

    }

    //html 转义
    @Test
    public void htmlConvert(){
        String str = "&nbsp;-&lt;-&gt;-&amp;-&quot;-&apos;";

        String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(str);
        System.out.println(unescapeHtml4);
        String escapeHtml4 = StringEscapeUtils.escapeHtml4(unescapeHtml4);
        System.out.println(escapeHtml4);

        String unescapeHtml3 = StringEscapeUtils.unescapeHtml3(str);
        System.out.println(unescapeHtml3);
        String escapeHtml3 = StringEscapeUtils.escapeHtml3(unescapeHtml3);
        System.out.println(escapeHtml3);
    }


    @Test
    public void isDateTest() {
        boolean a = isDate("2021-03-01");
        boolean b = isDate("6363-34-60");
        boolean c = isDate("0000-00-00");
    }

    private static boolean isDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}



