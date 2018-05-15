package net.ossrs.yasea.demo.Activity.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class BarrageView extends LinearLayout
{


    private ArrayList<BarrageLine> mBarrages = new ArrayList<BarrageLine>();
    private Random mRandom;

    public BarrageView(Context context) {
        this(context, null);
    }

    public BarrageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 总共三行弹幕
     */
    private void init() {
        mRandom = new Random();
        setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 3; i++) {
            BarrageLine bl = new BarrageLine(getContext());
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            bl.setLayoutParams(param);
            this.addView(bl);
            mBarrages.add(bl);
        }
    }

    /**
     * 随机添加弹幕到某一行
     * @param view
     */
    public void addBarrage(View view) {

        mBarrages.get(mRandom.nextInt(3)).addBarrage(view);
    }

    /**
     * 指定添加弹幕到某一行
     * @param view
     * @param line
     */
    public void addBarrage(View view,int line){
        mBarrages.get(line).addBarrage(view);
    }



}
