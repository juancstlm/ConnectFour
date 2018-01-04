package connect4.model;

public class Tuple<X, Y> {
    private X x;
    private Y y;

    public Tuple(X x, Y y){
        this.x = x;
        this.y = y;
    }


    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public int hashCode() { return x.hashCode() ^ y.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tuple)) return false;
        Tuple pairo = (Tuple) o;
        return this.x.equals(pairo.getX()) &&
                this.y.equals(pairo.getY());
    }
}
