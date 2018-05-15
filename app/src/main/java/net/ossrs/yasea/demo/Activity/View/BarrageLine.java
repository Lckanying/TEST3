package net.ossrs.yasea.demo.Activity.View;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.concurrent.ConcurrentLinkedQueue;


public class BarrageLine extends FrameLayout
{
    private Handler mHandler;
    private ConcurrentLinkedQueue<View> mQueue = new ConcurrentLinkedQueue<>();
    private int mWidth;
    private int PADDING = 20;
    private int HEIGHT = 100;

// /**
// * 统一线程池
// */
// public static Executor mExecutor = Executors.newCachedThreadPool();

    public BarrageLine(Context context) {
        this(context,null);
    }

    public BarrageLine(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BarrageLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        ///LOOPER用来抽取队列里面的东西
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        flutter();
    }

    /**
     * 设置一行的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mWidth,HEIGHT);
    }

    /**
     * 网队列里添加弹幕view
     * @param view
     */
    public void addBarrage(View view){
        mQueue.offer(view);
    }

    private class AddBarrageTask implements Runnable{
        View view;
        public AddBarrageTask(View view){
            this.view = view;
        }

        @Override
        public void run() {
            mQueue.offer(view);
        }
    }

    /**
     * 开始执行动画
     */
    private void flutter(){
        mHandler.post(mFlutterTask);
    }

    private Runnable mFlutterTask = new Runnable() {
        @Override
        public void run() {
            addBarrageView();
            moveView();
            mHandler.postDelayed(this,5);
        }
    };

    /**
     * 判断每一行是否要添加view
     */
    private void addBarrageView() {
        if (getChildCount() == 0){
            addNextView();
            return;
        }
        View lastChild = this.getChildAt(getChildCount()-1);
        int lastChildRight = (int) (lastChild.getTranslationX()+(int)lastChild.getTag());
        if (lastChildRight+PADDING>=mWidth)
            return;
        addNextView();
    }

    /**
     * 给每一行添加view
     */
    private void addNextView(){
        if (mQueue.isEmpty())
            return;
        View view = mQueue.poll();
        view.measure(0,0);
        view.setTag(view.getMeasuredWidth());
        addView(view);
        view.setTranslationX(mWidth);
    }

    /**
     * 通过handler.post执行，形成动画
     */
    private void moveView() {
        if (this.getChildCount()==0)
            return;
        for (int i=0;i<getChildCount();i++){
            View view = this.getChildAt(i);
            view.setTranslationX(view.getTranslationX()-3);
            if (view.getTranslationX()+(int)view.getTag()<=0)
                removeBarrageView(view);
        }
    }

    /**
     * 当view移出弹幕行，删除
     * @param view
     */
    private void removeBarrageView(View view){
        view.setVisibility(GONE);
        this.removeView(view);
        view = null;
    }

    /**
     * 停止发消息，取消动画
     */
    private void stop(){
        if (mHandler!=null)
            mHandler.removeCallbacks(mFlutterTask);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();

    }
}
