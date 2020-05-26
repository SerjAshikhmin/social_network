package ex3;

import org.w3c.dom.ls.LSOutput;

public class GlassesAssemblyLine implements IAssemblyLine {
    @Override
    public IProduct assembleProduct(IProduct iProduct) {
        FrameLineStep frameLineStep = new FrameLineStep();
        LensesLineStep lensesLineStep = new LensesLineStep();
        TemplesLineStep templesLineStep = new TemplesLineStep();

        System.out.println("Starting production of parts...");
        Frame frame = (Frame) frameLineStep.buildProductPart();
        Lenses lenses = (Lenses) lensesLineStep.buildProductPart();
        Temples temples = (Temples) templesLineStep.buildProductPart();

        iProduct.installFirstPart(frame);
        iProduct.installSecondPart(lenses);
        iProduct.installThirdPart(temples);

        System.out.println("Glasses produced successfully!");
        return iProduct;
    }
}
