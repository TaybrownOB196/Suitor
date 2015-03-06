package co.tailoredbytaylor.suitor;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by snitch on 16/02/15.
 */
public class GLSurfaceViewHelper extends GLSurfaceView {

    private final GLRendererHelper mRenderer;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch(e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if( y > getHeight() / 2)
                    dx = dx * -1;
                if(x < getWidth() / 2)
                    dy = dy * -1;

                mRenderer.setAngle(mRenderer.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    public GLSurfaceViewHelper(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        mRenderer = new GLRendererHelper();

        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
