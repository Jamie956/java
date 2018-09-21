# Factory Pattern

- 解耦 ：把对象的创建和使用的过程分开
- 降低代码重复
- 降低维护成本 ：由于创建过程都由工厂统一管理，所以发生业务逻辑变化，不需要找到所有需要创建对象的地方去逐个修正，只需要在工厂里修改即可，降低维护成本



## Simple Factory 

(Static Factory Method Pattern)

### 结构

1. 定义一个接口和抽象方法
2. 定义一个实体类，实现接口和它的方法
3. 定义一个工厂类，一个方法，参数接收实体类型，返回创建好的实体



## Factory Method 

(Polymorphic Factory or Virtual Constructor)

概念：每个对象都有一个与之对应的工厂



### 结构

1. 定义工厂接口，获取实体的抽象方法
2. 定义实体工厂类，实现1.接口和方法，返回值是创建对应实体的实例
3. 定义实体类的接口
4. 定义实体类，实现2.接口



## Abstract Factory 

(Kit or Toolkit)