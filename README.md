hi java core

[基础知识、JDK](/src/main/java/basic)


[设计模式](/src/main/java/pattern)

```
strategy 策略模式
解决多if对应不同算法处理的情况
1.定义一个策略接口，有统一处理的方法名、入参
2.每种策略对应每一个类，实现策略接口，重写方法实现策略处理方法
3.定义一个context类，负责执行策略，实例化context时传入具体的策略，定义一个execute方法执行策略
```
