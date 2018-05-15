package net.ossrs.yasea.demo.Activity.Uitls;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.RadioButton;

public class RadioButtonUtil
{
    private  RadioButton[] rb;

    private Drawable[] drawables;


    /**
     * 图片要缩小的比例molecule:Denominator
     * @param rbid
     * @param context
     * @param molecule
     * @param Denominator
     */
    public RadioButtonUtil(int[] rbid, Context context, int molecule, int Denominator)
    {
        //定义RadioButton数组用来装RadioButton，改变drawableTop大小
        rb = new RadioButton[rbid.length];

        //将RadioButton装进数组中
        for (int i=0;i<rbid.length;i++)
        {
            int rb_id=rbid[i];

//          View view=LayoutInflater.from(context).inflate(viewId,null);
            try {
                rb[i] = (RadioButton)getActivity(context).findViewById(rb_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //for循环对每一个RadioButton图片进行缩放
        for (int i = 0; i < rb.length; i++)
        {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            drawables = rb[i].getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * molecule / Denominator,
                    drawables[1].getMinimumHeight() * molecule / Denominator);
            //定义一个Rect边界

            drawables[1].setBounds(r);
            //给每一个RadioButton设置图片大小
            rb[i].setCompoundDrawables(null, drawables[1],null, null);
        }
    }
    private Activity getActivity(Context context) throws Exception {

        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
           context = ((ContextWrapper) context).getBaseContext();
       }

        if (context instanceof Activity)
        {
            return (Activity) context;
        }
            throw new  Exception("Unable to get Activity.");

    }
}
