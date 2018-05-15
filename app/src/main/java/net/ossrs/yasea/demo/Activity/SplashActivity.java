package net.ossrs.yasea.demo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import net.ossrs.yasea.demo.R;

public class SplashActivity  extends Activity
{
    ///缺省判断
    private  boolean FlaseORTure=false;

    private TextView main_txt;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        //// handle的延时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                init();
            }
        }, 3000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Log.i("////////////////", "onCreate: -----------------");
        init();
        return true;
    }

    private void init()
    {
        if (!FlaseORTure)
        {
            FlaseORTure=true;
            finish();
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
