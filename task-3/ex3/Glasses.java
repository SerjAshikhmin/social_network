package ex3;

public class Glasses implements IProduct {
    @Override
    public void installFirstPart(IProductPart iProductPart) {
        System.out.println("Frame installed");
    }

    @Override
    public void installSecondPart(IProductPart iProductPart) {
        System.out.println("Lenses installed");
    }

    @Override
    public void installThirdPart(IProductPart iProductPart) {
        System.out.println("Temples installed");
    }
}
