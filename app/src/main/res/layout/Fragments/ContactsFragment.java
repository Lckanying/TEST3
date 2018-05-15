package com.example.acer.mymusic.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.acer.mymusic.R;
import com.yuyh.library.BubblePopupWindow;

import java.util.List;

/**
 * Created by kang on 2018/3/19.
 */

public class ContactsFragment extends Fragment
{

    private BubblePopupWindow leftTopWindow;
    private BubblePopupWindow rightTopWindow;
    private BubblePopupWindow leftBottomWindow;
    private BubblePopupWindow rightBottomWindow;
    private BubblePopupWindow centerWindow;

    private LinearLayout popll;

    private List<Fragment> list;

     public   ContactsFragment()
     {

     }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view=inflater.inflate(R.layout.fragment_contacts,container,false);
        leftTopWindow = new BubblePopupWindow(getContext());
        rightTopWindow = new BubblePopupWindow(getContext());
        leftBottomWindow = new BubblePopupWindow(getContext());
        rightBottomWindow = new BubblePopupWindow(getContext());
        centerWindow = new BubblePopupWindow(getContext());
        popll=view.findViewById(R.id.popll);

        popll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View  View_top = inflater.inflate(R.layout.layout_popup_viewtwo , null);
                View bubbleView = inflater.inflate(R.layout.layout_popup_viewone, null);
                View  View_buttom = inflater.inflate(R.layout.layout_popup_viewthr, null);
                leftTopWindow.setBubbleView(bubbleView);
                rightTopWindow.setBubbleView(View_top);
                rightBottomWindow.setBubbleView(View_buttom);

                leftTopWindow.showAsDropDown(v,350,-410);
                rightTopWindow.show(v,Gravity.TOP);
                rightBottomWindow.show(v,Gravity.BOTTOM);

                //整体展示popupwindow后 加上这个监听就可以了
                //遇到的坑就是如果界面中存在ViewPager轮播，轮播后popupwindow会回到原来的位置，
                // 暂时解决方法：在MotionEvent.ACTION_MOVE:中停止轮播，当PopupWindow隐藏后再重新开始轮播
                //********************注意是popupview设置监听************************
                //********************其实我尝试了下用popupwindow中的某个控件也是可以的************************
                bubbleView.setOnTouchListener(new  View.OnTouchListener() {
                    int orgX, orgY;
                    int offsetX,offsetY;
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                orgX = (int) event.getX();
                                orgY = (int) event.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                offsetX = (int) event.getRawX() - orgX;
                                offsetY = (int) event.getRawY() - orgY;
                                leftTopWindow.update(offsetX, offsetY, -1, -1, true);
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                        }
                        return true;
                    }
                });




            }
        });
        return view;
    }
}
