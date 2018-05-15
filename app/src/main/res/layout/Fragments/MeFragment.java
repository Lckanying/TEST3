package com.example.acer.mymusic.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.acer.mymusic.Adapter.TimeLineAdapter;
import com.example.acer.mymusic.R;
import com.example.acer.mymusic.Utils.DividerItemDecoration;
import com.example.acer.mymusic.View.MyRecyclerView;
import com.example.acer.mymusic.View.MyScrollView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kang on 2018/3/19.
 */

public class MeFragment extends Fragment
{


    MyScrollView scrollView;

    LinearLayout tv_head;

    SwipeRefreshLayout layout;

    MyRecyclerView recyclerView;
    private boolean hasMeasured;


    View view;
    private RecyclerView Rv;
    private ArrayList<HashMap<String,Object>> listItem;
    private TimeLineAdapter timeLineAdapter;

     public    MeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        // 初始化显示的数据
        initData();

        // 绑定数据到RecyclerView
        initView();

        initView_two();

        return view ;
    }


    ////把需要的数据放在RecyclView
    private void initView()
    {
        recyclerView=view.findViewById(R.id.my_recycler_view);
        layout=view.findViewById(R.id.layout);
        scrollView=view.findViewById(R.id.scrollView);
        tv_head=view.findViewById(R.id.tv_head);

        //使用线性布局
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        //用自定义分割线类设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));


    }

    private void initView_two() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        timeLineAdapter=new TimeLineAdapter(listItem,getContext());
        recyclerView.setAdapter(timeLineAdapter);
        ViewTreeObserver vto = tv_head.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    int height = tv_head.getMeasuredHeight();
                    scrollView.setHeadHeight(height);
                    hasMeasured = true;
                }
                return true;
            }
        });
        recyclerView.setCallBack(new MyRecyclerView.CallBack() {
            @Override
            public void top() {
                scrollView.setIntercept(true);
            }
        });
        layout.setEnabled(false);
        //设置刷新时动画的颜色，可以设置4个
        layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //正在刷新
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        //刷新完成
                        layout.setRefreshing(false);
                    }
                }, 500);
            }
        });
    }



    // 初始化显示的数据
    public void initData(){
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        HashMap<String, Object> map6 = new HashMap<String, Object>();

        map1.put("ItemTitle", "2015.09-2019.07 贵州大学 信息安全（本科）");
        map1.put("ItemText", " 主修课程：网络安全编程技术，应用密码学，计算机通信与网络，" +
                "操作系统原理，信息安全概论，微机原理与接口技术，" +
                "面向对象程序设计，初等数论，数据结构与算法分析，C程序设计，网络安全攻防，" +
                "计算机病毒，信息安全工程等");
        listItem.add(map1);

        map2.put("ItemTitle", "2015.09-2019.07 通达智慧科技有限公司 志图博纳有限公司");
        map2.put("ItemText", "担任安卓工程师，.net前端工程师，培训助教" +
                "开发项目（非个人开发）：贵阳国际马拉松app，马场镇扶贫网站建设，贵安新区智慧园区app" +
                "设计海报宣传单以及运营微信公众号");
        listItem.add(map2);

        map3.put("ItemTitle", "我说不上特别的阳光，但是也并不特别沉默的人，" +
                "对某些事情追求完美，追求简单快乐的生活。喜欢和人聊天和打交道，喜欢创新，" +
                "有组织能力，曾创立贵州大学Shadow计算机协会，是现在这个协会的第一任会长。");
        map3.put("ItemText", "爱好的运动是篮球");
        listItem.add(map3);

        map4.put("ItemTitle", "中级软件设计师");
        map4.put("ItemText", "");
        listItem.add(map4);


    }





}
