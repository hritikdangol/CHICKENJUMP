class Path {
    private int pathNum;
    private double multiplier;
    private boolean fire;

    Path(int pathNum, double multiplier, boolean fire) {
        this.pathNum = pathNum;
        this.multiplier = multiplier;
        this.fire = fire;
    }

    public int getPathNum() {
        return pathNum;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public boolean hasFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
}