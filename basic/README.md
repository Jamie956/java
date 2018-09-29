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
4. 关闭Socket output
5. 从Socket获取输入流
6. 读取输入流
7. 指定编码
8. 存入StringBuilder
9. 关闭Server









