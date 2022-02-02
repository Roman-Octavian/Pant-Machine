public class PantC extends Container {
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

    public PantC(int type, double value) {
        this.type = type;
        this.value = value;
    }
}
