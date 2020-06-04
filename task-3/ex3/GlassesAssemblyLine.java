package ex3;

public class GlassesAssemblyLine implements IAssemblyLine {
    private FrameLineStep frameLineStep;
    private LensesLineStep lensesLineStep;
    private TemplesLineStep templesLineStep;

    public GlassesAssemblyLine(FrameLineStep frameLineStep, LensesLineStep lensesLineStep, TemplesLineStep templesLineStep) {
        this.frameLineStep = frameLineStep;
        this.lensesLineStep = lensesLineStep;
        this.templesLineStep = templesLineStep;
    }

    @Override
    public IProduct assembleProduct(IProduct iProduct) {
        System.out.println("Starting production of parts...");
        Frame frame = frameLineStep.buildProductPart();
        Lenses lenses = lensesLineStep.buildProductPart();
        Temples temples = templesLineStep.buildProductPart();

        iProduct.installFirstPart(frame);
        iProduct.installSecondPart(lenses);
        iProduct.installThirdPart(temples);

        System.out.println("Glasses produced successfully!");
        return iProduct;
    }
}
