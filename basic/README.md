# Array
## 定义
- 定义一个指定长度的数组

  ```java
  int[] a = new int[3];
  ```
- 定义一个包含元素的数组

  ```java
  int[] b = { 1, 2, 3 };
  int[] c = new int[] { 1, 2, 3 };
  ```
- 定义一个指定长度的二维数组

  ```java
  int[][] f = new int[5][4];
  ```
- 定义一个包含元素的二维数组 

  ```java
  int[][] d = { { 1, 2 }, { 3, 4, 5 } };
  ```

## 遍历
```java
for (T element : array) {}
```

## 数组转集合

```java
Arrays.asList(array)
```

# 单向Socket
## Socket Server
1. new ServerSocket(int port)，实例化ServerSocket类，在指定监听端口
2. accept() 接受Socket
3. 从Socket类获取输入流
4. 读取输入流
5. 指定编码
6. 存入StringBuilder
7. 关闭Server

## Socket Client
1. new Socket(String host, int port) 连接到Socket Server
2. 从Socket获取输出流
3. 往输出流写入信息
4. 关闭Socket

# 双向Socket
## Socket
1. new ServerSocket(int port)，实例化ServerSocket类，在指定监听端口
2. accept() 接受Socket
3. 从Socket类获取输入流
4. 读取输入流
5. 指定编码
6. 存入StringBuilder
7. 从Socket类获取输出流
8. 写出信息
9. 关闭Server

## Socket Client
1. new Socket(String host, int port) 连接到Socket Server
2. 从Socket获取输出流
3. 往输出流写入信息
4. shutdownOutput() 告诉服务器发送完数据
5. 从Socket获取输入流
6. 读取输入流
7. 指定编码
8. 存入StringBuilder
9. 关闭Server


## 注意
- 如果关闭了输出流，那么相应的Socket也将关闭，和直接关闭Socket一个性质
- 服务端优化，建立线程池处理
	- 线程复用，创建线程耗时，回收线程慢
	- 防止短时间内高并发，指定线程池大小，超过数量将等待，方式短时间创建大量线程导致资源耗尽，服务挂掉



# 反射
- 具体实例 -> 包名类名

```java
myInstance.getClass().getName()
```

- 包名类名 -> Class实例

```java
Class.forName("com.example.MyInstance");
```

- Class实例 -> 具体实例

```java
(MyInstance) classInstance.newInstance();
```

- Class实例 -> 构造函数

```java
classInstance.getConstructors();
```

- 构造函数 -> 具体实例

```java
(MyInstance) constructors[0].newInstance();
```

- Class实例 -> 实现的接口

```java
classInstance.getInterfaces();
```

- Class实例 -> 继承的父类

```java
classInstance.getSuperclass();
```

- Class实例 -> 成员变量


```java
classInstance.getDeclaredFields();
classInstance.getDeclaredFields("name");
```

- Class实例 -> 父类或接口的变量

```java
classInstance.getFields();
```

- fields[i] -> 权限修饰符

```java
Modifier.toString(fields[i].getModifiers());

```

- fields[i] -> 类型

```java
fields[i].getType().getName()
```

- Class实例 -> Method实例

```java
classInstance.getMethod("hello");
classInstance.getMethod("hello", String.class);

```

- Method实例 -> Method调用

```java
method.invoke(classInstance.newInstance());
method.invoke(classInstance.newInstance(), "tom");
```

- 操作属性

```java
field.setAccessible(true);
field.set(myInstance,"cat");

```

- 获取类加载器类型

1. Bootstrap ClassLoader 此加载器采用c++编写
2. Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
3. AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器

```java
myInstance.getClass().getClassLoader().getClass().getName()
```

- 动态代理结构

1. 定义实体（实现接口）
2. 自定义InvocationHandler，需要实现接口InvocationHandler
3. 重写方法invoke
4. 获取代理对象，Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)



# 创建对象的过程
1. 执行全部静态代码块
2. 如果继承父类，执行父类的代码块
3. 如果继承父类，执行父类默认无参构造方法
4. 执行代码块
5. 执行默认无参构造方法

## 注意
- 即使同一个class创建多个实例，他们的静态成员变量是共享的





















































