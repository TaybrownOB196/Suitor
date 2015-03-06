package co.tailoredbytaylor.suitor;

/**
 * Created by snitch on 01/03/15.
 */
public class Velocity {

    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_UP = -1;
    public static final int DIRECTION_DOWN = 1;

    private float x = 1;
    private float y = 1;

    private int xDirection = DIRECTION_RIGHT;
    private int yDirection = DIRECTION_DOWN;

    public Velocity() {this.x=12.5f; this.y=0.5f;}

    public Velocity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public float getXDirection() { return xDirection; }
    public void setXDirection(int xDirection) { this.xDirection = xDirection; }

    public float getYDirection() { return xDirection; }
    public void setYDirection(int xDirection) { this.xDirection = xDirection; }

    public void toggleXDirection() { xDirection = xDirection * -1; }
    public void toggleYDirection() { yDirection = yDirection * -1; }
}
