package com.example.acer.mymusic.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.acer.mymusic.Adapter.LuageAdapter;
import com.example.acer.mymusic.Models.LuageModel;
import com.example.acer.mymusic.R;
import com.example.acer.mymusic.Utils.Fast;
import com.example.acer.mymusic.Utils.GlideImageLoader;
import com.example.acer.mymusic.Utils.info_array_Util;
import com.example.acer.mymusic.Utils.info_data_util;
import com.example.acer.mymusic.Utils.info_util;
import com.example.acer.mymusic.View.GrideViewScroll;
import com.example.acer.mymusic.View.MyListView;
import com.show.api.ShowApiRequest;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kang on 2018/3/19.
 */

///其余三个和这个是一样的

public class WeChatFragment extends Fragment
{
    ///装在图片id的集合
    List<Integer> imageid;
    List<String> titleid;

    ////下拉收


    ///九宫格
    GrideViewScroll gridView;
    SimpleAdapter simpleAdapter;
    List<Map<String,Object>> mapList;

    ///测试api
    TextView textView;

    ///笑话集合
    List<LuageModel> keywords = new ArrayList<>();;

    //以下代码为纯java实现，并未依赖第三方框架,具体传入参数请参看接口描述详情页.
    protected Handler mHandler =  new Handler();

    ///list笑话List
    MyListView myListView;

    ////装在这json数据的三个model
    private info_util infoUtil=new info_util();
    private info_data_util infoDataUtil=new info_data_util();
    private List <info_array_Util> infoArrayUtil=new ArrayList<>();



    ///吧图片和下标题封装在数组里面
    private   int[] picId= {
                    R.mipmap.lubo2,R.mipmap.lubo2,R.mipmap.lubo2,
                    R.mipmap.lubo2
                            };
    private   String[] undtitle=
            {
                    "标题1","标题2","标题3","标题4"

            };

   public  WeChatFragment()
   {
       ///预留构造函数
   }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_we_chat,container,false);
        Banner banner=view.findViewById(R.id.banner);
        //设置Banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());
        myListView=view.findViewById(R.id.wechat_content);

        ///加载图片集合以及标题集合
        imageid=new ArrayList<>();
        imageid.add(R.mipmap.lubo1);
        imageid.add(R.mipmap.lubo2);
        imageid.add(R.mipmap.lubo3);

        titleid=new ArrayList<>();
        titleid.add("雕虫小技而已");
        titleid.add("我的眼里只有塔");
        titleid.add("到达胜利之前，无法回头");
        banner.setBannerTitles(titleid);
        banner.setImages(imageid).setDelayTime(3000).start();

       // gridView=view.findViewById(R.id.wechatGl);
      //  gridView.setFocusable(false);
        mapList=new ArrayList<Map<String,Object>>();
        ////加载数据
        getData();

        ////新建适配器
        String[] adapter_str={"images","titles"};
        int[] apapter_int={R.id.image,R.id.text};

        ////装在好适配器
       // simpleAdapter=new SimpleAdapter(getContext(),mapList,R.layout.wechat_item,adapter_str,apapter_int);

     //   gridView.setAdapter(simpleAdapter);




        new Thread(){
            //在新线程中发送网络请求
            public void run() {
                String appid="60752";//要替换成自己的
                String secret="5e3b9700bef54e0dbed28e2c3b5729c9";//要替换成自己的
                final String res=new ShowApiRequest( "http://route.showapi.com/341-1", appid, secret)
                        .addTextPara("page", "1")
                        .addTextPara("maxResult", "20")
                        .post();

                System.out.println(res);
//把返回内容通过handler对象更新到界面
                mHandler.post(new Thread(){
                    public void run() {
                        ////把数据放入字符串中
                       /// String apijson= res+""+new Date();
                        /**
                         * 解析数据
                         */
                        infoUtil = Fast.parseObject(res, info_util.class);//取得第一层JSONObject
                        infoDataUtil = Fast.parseObject(infoUtil.getShowapi_res_body(), info_data_util.class);//取得第二层JSONObject
                        infoArrayUtil = Fast.parseArray(infoDataUtil.getContentlist(), info_array_Util.class);//取得第三层JSONArray


                        if (infoArrayUtil.size()>0)
                        {
                            myListView.setAdapter(new LuageAdapter(getContext(),infoArrayUtil));
                        }
                    }
                });
            }
        }.start();

        return view;
    }

    /*
     * 模拟数据
     */
    private  List<Map<String,Object>> getData()
    {
     for (int i=0;i<picId.length;i++)
     {
         Map<String,Object> map=new HashMap<String,Object>();
         map.put("images",picId[i]);
         map.put("titles",undtitle[i]);
         mapList.add(map);
     }
       return mapList;
    }




}
