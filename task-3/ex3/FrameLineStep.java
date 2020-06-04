package ex3;

public class FrameLineStep implements ILineStep {
    public Frame buildProductPart() {
        System.out.println("Building frame");
        return new Frame();
    }
}
