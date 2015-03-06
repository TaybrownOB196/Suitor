package co.tailoredbytaylor.suitor;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by snitch on 23/02/15.
 */
public class MainLoop extends Thread {

    private static final String TAG = MainLoop.class.getSimpleName();
    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;
    private boolean running;

    public MainLoop(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        long tickCount = 0l;
        Log.d(TAG, "Starting game loop");
        while(running) {
            canvas = null;
            tickCount++;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.onDraw(canvas);
                }
            } finally {
                if(canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        Log.d(TAG, "Game loop executed "+tickCount+" times");
    }
}
