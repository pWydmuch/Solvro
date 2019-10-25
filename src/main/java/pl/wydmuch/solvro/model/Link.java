package pl.wydmuch.solvro.model;

public class Link {

    Stop source;

    Stop target;

    int distance;

    public Stop getSource() {
        return source;
    }

    public void setSource(Stop source) {
        this.source = source;
    }

    public Stop getTarget() {
        return target;
    }

    public void setTarget(Stop target) {
        this.target = target;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
