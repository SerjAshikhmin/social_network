package ex3;

public class Glasses implements IProduct {
    private Frame frame;
    private Lenses lenses;
    private Temples temples;

    @Override
    public void installFirstPart(IProductPart iProductPart) {
        this.frame = (Frame) iProductPart;
        System.out.println("Frame installed");
    }

    @Override
    public void installSecondPart(IProductPart iProductPart) {
        this.lenses = (Lenses) iProductPart;
        System.out.println("Lenses installed");
    }

    @Override
    public void installThirdPart(IProductPart iProductPart) {
        this.temples = (Temples) iProductPart;
        System.out.println("Temples installed");
    }
}
