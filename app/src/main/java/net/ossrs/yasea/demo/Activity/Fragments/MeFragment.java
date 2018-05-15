package net.ossrs.yasea.demo.Activity.Fragments;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import net.ossrs.yasea.demo.Activity.MainActivity;
import net.ossrs.yasea.demo.Activity.PersonActivity;
import net.ossrs.yasea.demo.R;




/**
 * Created by kang on 2018/3/19.
 */

public class MeFragment extends Fragment
{

    private Button main_btn,main_btn_zb;
     public    MeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View  view=inflater.inflate(R.layout.fragment_me,container,false);

        main_btn=view.findViewById(R.id.main_btn);
        main_btn_zb=view.findViewById(R.id.main_btn_zb);
        main_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(getContext(), PersonActivity.class);
               startActivity(intent);
            }
        });

        main_btn_zb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        ///动态获取拍照权限

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },2);
        }
        return view ;
    }
}
