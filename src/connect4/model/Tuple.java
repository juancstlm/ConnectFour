package connect4.model;

public class Tuple {
    private final Integer x;
    private final Integer y;

    public Tuple(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }


    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public int hashCode() { return x.hashCode() ^ y.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tuple)) return false;
        Tuple t = (Tuple) o;
        return this.x.equals(t.getX()) &&
                this.y.equals(t.getY());
    }
}
