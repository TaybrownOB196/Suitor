package co.tailoredbytaylor.suitor;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by snitch on 22/02/15.
 */
public class SwordsmanAnimated {
    private static final String TAG = SwordsmanAnimated.class.getSimpleName();
    private Bitmap bitmap;
    private Rect sourceRect;
    private int frameNr;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;

    private int spriteWidth;
    private int spriteHeight;

    private int x;
    private int y;
}
