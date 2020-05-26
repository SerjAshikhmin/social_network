package ex3;

public class FrameLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Building frame");
        return new Frame();
    }
}
