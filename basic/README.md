```
StringBuilder 用于任何字符串处理，修改
StringBuffer 与StringBuilder几乎一样，但是是线程安全
Formatter 处理字符串格式，效率没有StringBuilder高
StringJoiner 按照格式用分隔符拼接字符串
```

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

