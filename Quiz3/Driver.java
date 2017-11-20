public class Driver {
    public static void main(String[] args) {
        Child c = new Child(1, 2, 3, 4);
        Mother m = new Mother(1, 2);

        System.out.println("Getting A with child: " + c.getA());
        System.out.println("Getting B with child: " + c.getB());

        System.out.println("Using child add method: " + c.add(1, 2, 3));
        System.out.println("Using mother add method: " + m.add(1, 2, 3));


        System.out.println("Using child to call all 4 setters.");
        c.setA(3);
        c.setB(4);
        c.setC(5);
        c.setD(6);
    }
}

class Mother {
    int valueA;
    int valueB;

    public Mother() {
        valueA = 0;
        valueB = 0;
    }

    public Mother(int A, int B) {
        valueA = A;
        valueB = B;
    }

    int getA() {
        return valueA;
    }

    int getB() {
        return valueB;
    }

    void setA(int A) {
        valueA = A;
    }

    void setB(int B) {
        valueB = B;
    }

    int add(int A, int B, int C) {
        return A * B + C;
    }

}


class Child extends Mother {
    int valueC;
    int valueD;

    public Child(int C, int D) {
        super();
        valueC = C;
        valueD = D;
    }

    public Child(int A, int B, int C, int D) {
        super(A, B);
        valueC = C;
        valueD = D;
    }

    int getC() {
        return valueC;
    }

    int getD() {
        return valueD;
    }

    void setC(int C) {
        valueC = C;
    }

    void setD(int D) {
        valueD = D;
    }

    int add(int A, int B, int C) {
        return (int) A / B - C;
    }
}
