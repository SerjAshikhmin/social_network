package ex3;

public class Solution {
    public static void main(String[] args) {
        GlassesAssemblyLine glassesAssemblyLine = new GlassesAssemblyLine(new FrameLineStep(), new LensesLineStep(), new TemplesLineStep());
        glassesAssemblyLine.assembleProduct(new Glasses());
    }
}
