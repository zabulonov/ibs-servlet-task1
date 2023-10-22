package Calculator.Logic;

public class Calculation {
    double a;
    double b;
    char operation;

    public Calculation (double a, double b, char operation)
    {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    public double Calc() throws Exception {
        switch (operation){
            case('+'):
                return Plus();
            case('-'):
                return Minus();
            case('*'):
                return Multiply();
            case('/'):
                return Divide();
            case('%'):
                return RemainderOfDivision();
            default:
                throw new Exception("Invalid operation!");
        }

    }

    public double Plus()
    {
        return a + b;
    }
    public double Minus()
    {
        return a - b;
    }
    public double Multiply()
    {
        return a * b;
    }
    public double Divide() throws Exception {
        if (b == 0)
            throw new Exception("You can't divide by zero!");
        return a / b;
    }
    public double RemainderOfDivision()
    {
        return a % b;
    }
}
