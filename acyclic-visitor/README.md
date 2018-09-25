# 结构
1. 定义Modem抽象类，定义抽象方法，用来调用ModemVisitor的方法
2. 定义具体Modem类，继承1.，重写方法
3. 定义ModemVisitor接口
4. 定义ModemVisitor具体接口，继承3.接口，定义方法，接收具体的Modem
5. 定义ConfigureVisitor类，可实现4.多个接口

# 角色
- Modem，接收ModemVisitor和调用它的方法
- ModemVisitor，拥有接收modem的方法