public class Constraint {
    private double inferiorLimit;
    private double superiorLimit;

    public Constraint(double inferiorLimit, double superiorLimit) {
        this.inferiorLimit = inferiorLimit;
        this.superiorLimit = superiorLimit;
    }

    public double getInferiorLimit() {
        return inferiorLimit;
    }

    public double getSuperiorLimit() {
        return superiorLimit;
    }
}
