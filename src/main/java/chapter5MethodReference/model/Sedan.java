package chapter5MethodReference.model;

public class Sedan extends Car {

    public Sedan(String name, String brand) {
        super(name, brand);
    }

    public void drive() {
        System.out.println("Driving a sedan " + name + " from " + brand);
    }

    @Override
    public String toString() {
        return "Sedan{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
