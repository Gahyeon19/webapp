package main;

public class Main {
    public static void main(String[] args) {
        // has a 관계  Computer => Mouse => Button
        Button button = new Button(2);
        Mouse mouse = new Mouse(button);  // 생성자 주입
        Computer computer = new Computer();
        computer.setMouse(mouse);   // Setter 주입

        System.out.println(computer);   //Computer with mouse [Mouse with button [Button with number [2]]]
    }
}

class Button {
    private int b;
    Button(int b) {
        this.b = b;
    }
    Button() {}

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Button with number [" + b + "]";
    }
}

class Mouse {
    private Button button;
    Mouse(Button button) {
        this.button = button;
    }
    Mouse(){}

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Mouse with button [" + button + "]";
    }
}

class Computer {
    private Mouse mouse;
    Computer(Mouse mouse) {
        this.mouse = mouse;
    }
    Computer(){}

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    @Override
    public String toString() {
        return "Computer with mouse [" + mouse + "]";
    }
}