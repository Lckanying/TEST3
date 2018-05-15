package net.ossrs.yasea.demo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayerView;
import net.ossrs.yasea.demo.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;


public class IjkAcitivity extends Activity
{

    private PlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ///设置全屏操作
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ijk);
        ButterKnife.bind(this);


    }
    @OnClick({R.id.btn_h, R.id.btn_v, R.id.btn_live, R.id.btn_origin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_h:
                /**半屏播放器*/
                startActivity(HPlayerActivity.class);
                break;
            case R.id.btn_v:
                /**竖屏播放器*/
                startActivity(PlayerActivity.class);
                break;
            case R.id.btn_live:
                /**竖屏直播播放器*/
                startActivity(PlayerLiveActivity.class);
                break;
            case R.id.btn_origin:
                /**ijkplayer原生的播放器*/
                startActivity(OriginPlayerActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(IjkAcitivity.this, cls);
        startActivity(intent);
    }
}
