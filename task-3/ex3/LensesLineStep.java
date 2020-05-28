package ex3;

public class LensesLineStep implements ILineStep {
    public Lenses buildProductPart() {
        System.out.println("Building lenses");
        return new Lenses();
    }
}
