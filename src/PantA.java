public class PantA extends Container {
    private int type;
    private double value;

    @Override
    public int getType() {
        return type;
    }
    @Override
    public double getValue() {
        return value;
    }

    public PantA(int type, double value) {
        this.type = type;
        this.value = value;
    }



}
