package co.tailoredbytaylor.suitor;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by snitch on 22/02/15.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private MainLoop loop;
    private Fighter fighter;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        fighter = new Fighter(BitmapFactory.decodeResource(getResources(), R.drawable.man_one), 75, 75);
        loop = new MainLoop(getHolder(), this);
        setFocusable(true);//Makes MainGamePanel focusable. Will receive events
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        loop.setRunning(true);
        loop.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
        boolean retry = true;
        while(retry) {
            try {
                loop.join();
                retry = false;
            }
            catch(InterruptedException ie) {

            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            fighter.handleActionDown((float)event.getX(), (float)event.getY());
            if(event.getY() > getHeight() - 50) {
                loop.setRunning(false);
                ((Activity)getContext()).finish();
            }else {
                Log.d(TAG, "Coords: x="+event.getX()+",y="+event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if(fighter.isTouched()) {
                fighter.setX((float)event.getX());
                fighter.setY((float)event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_UP) {
            if(fighter.isTouched()) {
                fighter.setTouched(false);
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        fighter.draw(canvas);
    }

    public void update() {
        if(fighter.getVelocity().getXDirection() == Velocity.DIRECTION_RIGHT
                && fighter.getX() + fighter.getBitmap().getWidth()/2 >= getWidth()) {
            fighter.getVelocity().toggleXDirection();
        }
        if(fighter.getVelocity().getXDirection() == Velocity.DIRECTION_LEFT
                && fighter.getX() - fighter.getBitmap().getWidth()/2 <= 0) {
            fighter.getVelocity().toggleXDirection();
        }
        if(fighter.getVelocity().getYDirection() == Velocity.DIRECTION_DOWN
                && fighter.getY() + fighter.getBitmap().getHeight()/2 >= getHeight()) {
            fighter.getVelocity().toggleYDirection();
        }
        if(fighter.getVelocity().getYDirection() == Velocity.DIRECTION_UP
                && fighter.getY() - fighter.getBitmap().getHeight()/2 <= 0) {
            fighter.getVelocity().toggleYDirection();
        }

        fighter.update();
    }
}
