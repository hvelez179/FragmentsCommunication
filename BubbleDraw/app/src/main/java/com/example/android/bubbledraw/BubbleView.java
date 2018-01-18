import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;

public class BubbleView extends ImageView {

    private ArrayList<Bubble> bubbleList:
    private final int DELAY = 16;
    private Paint myPaint = new Paint();
    private Handler h;

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bubbleList = new ArrayList<Bubble>();
        myPaint.setColor(Color.WHITE);
        h = new Handler();
    }

    
}
