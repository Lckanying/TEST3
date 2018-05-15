package net.ossrs.yasea.demo.Activity.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ossrs.yasea.demo.Activity.Uitls.Fast;
import net.ossrs.yasea.demo.Activity.Uitls.info_array_Util;
import net.ossrs.yasea.demo.Activity.Uitls.info_data_util;
import net.ossrs.yasea.demo.Activity.Uitls.info_util;
import net.ossrs.yasea.demo.R;
import com.show.api.ShowApiRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kang on 2018/3/19.
 */

public class ContactsFragment extends Fragment
{
    ////装在这json数据的三个model
    private info_util infoUtil=new info_util();
    private info_data_util infoDataUtil=new info_data_util();
    private List<info_array_Util> infoArrayUtil=new ArrayList<>();


    //以下代码为纯java实现，并未依赖第三方框架,具体传入参数请参看接口描述详情页.
    protected Handler mHandler =  new Handler();


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View view=inflater.inflate(R.layout.fragment_contacts,container,false);


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
                mHandler.post(new Thread()
                {
                    public void run()
                    {
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
//                            myListView.setAdapter(new LuageAdapter(getContext(),infoArrayUtil));
                        }
                    }
                });
            }
        }.start();
        return view;
    }


}
