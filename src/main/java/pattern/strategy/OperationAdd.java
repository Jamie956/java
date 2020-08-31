package pattern.strategy;

public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public int doDoubleOperation(int num1, int num2) {
        return (num1 + num2) * 2;
    }

}