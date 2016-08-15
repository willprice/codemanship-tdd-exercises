public class OrderValueDiscounter implements Discounter {
    private int threshold;

    public OrderValueDiscounter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public float discount(float total) {
        if (total > threshold) {
            return 0.05f;
        }
        return 0;
    }
}