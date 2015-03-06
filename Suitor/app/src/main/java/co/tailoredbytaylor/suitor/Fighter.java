package co.tailoredbytaylor.suitor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by snitch on 26/02/15.
 */
public class Fighter {
    private Bitmap bitmap;
    private Velocity velocity;
    private float x;
    private float y;
    private boolean touched;

    public Fighter(Bitmap bitmap, int x, int y) {
        this.velocity = new Velocity();
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitmap() { return bitmap; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public boolean isTouched() { return touched; }
    public void setTouched(boolean touched) { this.touched = touched; }

    public Velocity getVelocity() { return velocity; }
    //public void setVelocity() {  }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2), y - (bitmap.getHeight()/2), null);
    }

    public void handleActionDown(float eventX, float eventY) {
        if(eventX >= (x - bitmap.getWidth()/2) && (eventX <= (x+bitmap.getWidth()/2))) {
            if(eventY >= (y - bitmap.getHeight()/2) && (y <= (y+bitmap.getHeight() / 2))) {
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }
    }

    public void update() {
        if(!touched) {
            x += (velocity.getX() * velocity.getXDirection());
            y += (velocity.getY() * velocity.getYDirection());
        }
    }
}
