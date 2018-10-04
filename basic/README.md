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
```for (T element : array) {}```



## Array -> List

```Arrays.asList(array)```



# Socket

### Socket Server
1. new ServerSocket(int port)，创建Server并指定监听端口
2. accept() 接受Socket
3. 从Socket类获取输入流
4. 读取输入流
5. 指定编码
6. 存入StringBuilder
7. 从Socket类获取输出流
8. 写出信息
9. 关闭Server

### Socket Client
1. new Socket(String host, int port) 连接到Socket Server
2. 从Socket获取输出流
3. 往输出流写入信息
4. shutdownOutput() 告诉服务器发送完数据
5. 从Socket获取输入流
6. 读取输入流
7. 指定编码
8. 存入StringBuilder
9. 关闭Server



# 反射

- 具体实例 -> 包名类名

```java
myInstance.getClass().getName()
```

- 包名类名 -> Class实例

```java
Class.forName("com.example.MyInstance");
```

- Class实例 -> 创建具体实例

```java
(MyInstance) classInstance.newInstance();
```

- Class实例 -> 获取构造函数

```java
classInstance.getConstructors();
```

- 构造函数 -> 创建具体实例

```java
(MyInstance) constructors[0].newInstance();
```

- Class实例 -> 获取实现的接口

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


```java
myInstance.getClass().getClassLoader().getClass().getName()
```

- 动态代理结构

1. 定义实体（要实现接口）
2. 自定义InvocationHandler，需要实现接口InvocationHandler
  1. 重写方法invoke
  2. 获取代理对象，Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)



# 创建对象的过程
1. 执行全部静态代码块
2. 如果继承父类，执行父类的代码块
3. 如果继承父类，执行父类默认无参构造方法
4. 执行代码块
5. 执行默认无参构造方法

### 注意
- 即使同一个class创建多个实例，他们的静态成员变量是共享的



# BigDecimal
### 比较大小
```java
bigDecimal.compareTo(bigDecimal);
```
- 1 >
- 0 =
- -1 <

### 运算

```java
bigDecimal.add(bigDecimal);//加
bigDecimal.subtract(bigDecimal);//减
bigDecimal.multiply(bigDecimal);//乘
bigDecimal.multiply(bigDecimal, new MathContext(4, RoundingMode.HALF_DOWN));//保留位数
bigDecimal.divide(bigDecimal,10,RoundingMode.HALF_UP);//除，必须保留位数
bigDecimal.remainder(bigDecimal);//余数
bigDecimal.pow(n);//n次方
bigDecimal.max(bigDecimal);//最大值
bigDecimal.min(bigDecimal);//最小值
bigDecimal.movePointLeft(n);//小数点左移n位
bigDecimal.movePointRight(n);//小数点右移n位
```



# BigInteger

```java
//long/int -> BigInteger
BigInteger.valueOf(n);


//string -> BigInteger
new BigInteger(string);

//10进制 -> 2进制
new BigInteger(binaryString , 2);

```



# Boxing/Unboxing

#### Boxing, int -> Integer
```java
int i = 1;
Integer rs = Integer.valueOf(i);
```

#### Unboxing, Integer -> int
```java
Integer i = new Integer(1);
int rs = i.intValue();
```



# File

```new File("D:\\a.txt")``` 绝对路径创建实例
```isDirectory()``` 是否是文件夹
```mkdir()``` 创建文件夹
```createNewFile()``` 创建文件
```isFile()``` 是否是文夹
```mkdirs()``` 创建层级文件夹
```renameTo(File)``` 重命名
```delete()``` 删除文件
```getName()``` 获取文件名或文件夹名
```getAbsolutePath()``` 获取绝对路径
```list()``` 获取绝对路径
```listFiles()``` 获取抽象路径数组



# IO

```new FileInputStream(File);``` 实例化文件输入流
```fileInputStream.read();fileInputStream.read(new byte[20]);``` 读取输入流
```new FileOutputStream(File);``` 实例化文件输出流
```fileOutputStream.write(byte[]);``` 写出输出流
```fileOutputStream.write(byte[], 0, len);```
```fileOutputStream.flush();```



# Java 8 - Time

```LocalDate.now();``` 获取当天格式化日期
```LocalDate.of(2018, 05, 20);``` 获取指定格式化日期
```Clock.systemUTC();```
```Clock.systemDefaultZone();``` 获取系统时区
```MonthDay.of(5, 20);```
```LocalTime.now();``` 获取当前时间
```YearMonth.now();``` 获取当月天数
```Instant.now();``` 获取时间戳
```LocalDate.parse(text, formatter)``` 解析日期
```LocalDate.format(formatter)``` 格式化日期



# NumberFormatTest
```java
//格式化数字
NumberFormat.getInstance(new Locale("en", "IN")).format(10000000.99);
//货币格式化
NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(10340.999);
//百分比格式化
NumberFormat.getPercentInstance(new Locale("en", "IN")).format(10340.999);
```



# Regexp



| 字符 | 描述 |
| :--- | ------------------------------------------------- |
| $    | 匹配输入字符串的结尾位置                          |
| ()   | 标记一个子表达式的开始和结束位置                  |
| {}   | 标记限定符表达式的开始                            |
| \w   | 匹配包括下划线的任何单词字符,等价于“[A-Za-z0-9_]” |
| \d   | 匹配一个数字字符。等价于[0-9] |
| \s   | 匹配任何空白字符，包括空格、制表符、换页符等等。等价于[ \f\n\r\t\v] |
| \D   | 匹配一个非数字字符。等价于[^0-9] |
| \W | 匹配任何非单词字符。等价于[A-Za-z0-9] |
| [] | 字符范围，匹配指定范围内的任意字符 |
| [xyz] | 字符集合。匹配所包含的任意一个字符 |
| [^xyz] | 负值字符集合。匹配未包含的任意字符。 |
| [a-z] | 字符范围。匹配指定范围内的任意字符 |
| . | 匹配除“\n”之外的任何单个字符 |
| * | 匹配前面的子表达式零次或多次 |
| + | 匹配前面的子表达式一次或多次 |
| ? | 贪婪模式则尽可能多的匹配所搜索的字符串 |
| {n} | n是一个非负整数。匹配确定的n次 |
| {n,} | 至少匹配n次 |
| {n,m} | m和n均为非负整数，其中n<=m。最少匹配n次且最多匹配m次 |



## 例子

|              |                                    |
| ------------ | ---------------------------------- |
| /\d/         | 匹配数字，无限定符                 |
| /\d+/        | 匹配数字，一次或多次               |
| /\d+/g       | 匹配数字，全局                     |
| /[abc]       | 匹配包含a,b,c                      |
| /[a-z]/g     | 匹配包含a-z，全局                  |
| /[^a-z]/g    | 不匹配a-z的字符                    |
| /b/          | 匹配特定一个字符                   |
| /[^a-z0-9]/g | 不匹配a-z和0-9的字符               |
| /a\|2\|o/g   | 匹配多个指定字符                   |
| /<.*>/       | 匹配从 < 到 > 之间的所有内容       |
| /<.*?>/      | 匹配从 < 到第一个 > 之间的所有内容 |
| /<\w+?>/     | 匹配开始的 < >                     |



# String

```string.toUpperCase();``` 大写
```string.toLowerCase();``` 大写
```str1.contains(str2)``` 是否包含
```str.indexOf(key)``` 根据key获取索引
```str.split(";");``` String -> Array
```String.join(" + ", array);``` Array -> String
```string1.concat(string2)```


# StringBuilder

```stringBuilder.append(string)```



# 生产者/消费者

### 生产者
```java
//继承Thread，重写run()
run(){
    while(true){
        synchronized (queue) {
            //超过限制
            while(maxSize){
                //释放锁
                wait()
            }
            //生产
            notifyAll()
        }
    }
}
```

### 消费者
```java
//继承Thread，重写run()
run(){
	while(true){
		synchronized (queue) {
			//消费队列为空
			while(empty){
				//释放锁
				wait()
			}
			//消费
			notifyAll()
		}
	}	
}
```



# 线程池

```java
ThreadPoolExecutor.ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)

threadPoolExecutor.execute(thread);
threadPoolExecutor.shutdown();

```


































