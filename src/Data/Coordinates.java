package Data;

public class Coordinates {
    private Integer x;
    private Integer y;

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return '(' + x + ", " + y + ')';
    }
}
