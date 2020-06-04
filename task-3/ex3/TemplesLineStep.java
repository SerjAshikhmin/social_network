package ex3;

public class TemplesLineStep implements ILineStep {
    public Temples buildProductPart() {
        System.out.println("Building temples");
        return new Temples();
    }
}
