package net.ossrs.yasea.demo.Activity.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by kang on 2018/4/6.
 */

public class GrideViewScroll extends GridView
{


    public GrideViewScroll(Context context) {
        super(context);
    }

    public GrideViewScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GrideViewScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    ///测量高度 MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
    /// MeasureSpec.AT_MOST)就是生成一个符合MeasureSpec的一个32位的包含测量模式和测量高度的int值
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
         int expend= MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2,MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expend);
    }
}
