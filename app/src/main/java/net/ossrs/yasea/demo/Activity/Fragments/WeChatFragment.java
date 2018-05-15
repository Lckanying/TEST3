package net.ossrs.yasea.demo.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import net.ossrs.yasea.demo.Activity.Adapter.RecyclerAdapter;
import net.ossrs.yasea.demo.Activity.IjkAcitivity;
import net.ossrs.yasea.demo.Activity.Uitls.GlideImageLoader;
import net.ossrs.yasea.demo.Activity.View.SearchView;
import net.ossrs.yasea.demo.R;
import java.util.ArrayList;
import java.util.List;
import github.chenupt.dragtoplayout.DragTopLayout;
import scut.carson_ho.searchview.ICallBack;

import scut.carson_ho.searchview.bCallBack;


/**
 * Created by kang on 2018/3/19.
 */


///其余三个和这个是一样的

public class WeChatFragment extends Fragment
{

    // 1. 初始化搜索框变量
    private SearchView searchView;

    ///装在图片id的集合
    List<Integer> imageid;
    List<String> titleid;

    /**
     * 复用视图的控件
     */
    private RecyclerView recyclerView;

    /**
     * 显示的数据
     */
    private ArrayList<String> mDatas;

    /**
     * RecyclerView的适配器
     */
    private RecyclerAdapter adapter;

    private DragTopLayout drag;

    private SearchView search_view_f;

   public  WeChatFragment()
   {
       ///预留构造函数
   }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_we_chat,container,false);
        search_view_f=view.findViewById(R.id.search_view_f);

        drag = (DragTopLayout)view.findViewById(R.id.drag);
        Banner banner=view.findViewById(R.id.banner);
        //设置Banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());

        ///加载图片集合以及标题集合
        imageid=new ArrayList<>();
        imageid.add(R.mipmap.bar_tab_o);
        imageid.add(R.mipmap.bar_tab_th);
        imageid.add(R.mipmap.bar_tab_two);

        titleid=new ArrayList<>();
        titleid.add("鲲归我了，因为可爱无极光");
        titleid.add("我的眼里只有塔");
        titleid.add("到达胜利之前，无法回头");
        banner.setBannerTitles(titleid);

        banner.setImages(imageid).setDelayTime(3000).start();




        // 3. 绑定组件
        searchView = (SearchView)view.findViewById(R.id.search_view_f);

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                Toast.makeText(getContext(),"开发中...",Toast.LENGTH_SHORT).show();
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                Toast.makeText(getContext(),"开发中...",Toast.LENGTH_SHORT).show();
            }
        });


        //1.找到控件
        recyclerView = (RecyclerView)view.findViewById(R.id.main_recylist);

        // 设置滚动的view，因为内容不居中可能有好几个view，所以要手动指定谁是可滚动的布局
        // 我这里是ListView，还可以是ScrollView等
        drag.setDragContentViewId(R.id.main_recylist);
        drag.setTopViewId(R.id.ct_top_t);
        drag = drag.listener(new DragTopLayout.PanelListener() {
            @Override
            public void onPanelStateChanged(DragTopLayout.PanelState panelState) {
                if (panelState == DragTopLayout.PanelState.EXPANDED){
                    drag.openTopView(false);

                }
            }

            @Override
            public void onSliding(float ratio)
            {
                ratio=2.0f;
                return;
            }

            @Override
            public void onRefresh() {

            }
        });

        //2.声名gridview布局方式  第二个参数代表是3列的网格视图 (垂直滑动的情况下, 如果是水平滑动就代表3行)
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        //3.给linearLayoutManager设置滑动的方向
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (adapter.isHeaderView(position) || adapter.isBottomView(position)) ?
                        gridLayoutManager.getSpanCount() : 1;
            }
        });

        //4.为recyclerView设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);

        initData();//初始化数据
        //3.创建适配器
        adapter = new  RecyclerAdapter(getContext(), mDatas);

        //4.设置适配器
        recyclerView.setAdapter(adapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                //得到当前显示的最后一个item的view
//                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
//                //得到lastChildView的bottom坐标值
//                int lastChildBottom = lastChildView.getBottom();
//                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
//                int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();
//                //通过这个lastChildView得到这个view当前的position值
//                int lastPosition  = recyclerView.getLayoutManager().getPosition(lastChildView);
//
//                //判断lastChildView的bottom值跟recyclerBottom
//                //判断lastPosition是不是最后一个position
//                //如果两个条件都满足则说明是真正的滑动到了底部
//                if(lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount()-1 )
//                {
//                    recyclerView.smoothScrollBy(0, lastPosition);
//                    Toast.makeText(getContext(), "滑动到底了", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });


        //添加点击事件
        adapter.setOnItemClickListener(new  RecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(MainActivity.this,"单击了:"+mDatas.get(position),Toast.LENGTH_SHORT).show();
                adapter.addItem(position,"暗夜贵公子");
                Log.i("tag", "onItemClick: "+position);
                Log.i("tag", "集合: "+mDatas.toString());
                Toast.makeText(getContext(),"ssss"+position,Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getContext(),IjkAcitivity.class);
                startActivity(intent);
            }
        });
        //设置长按事件
        adapter.setOnItemLongClickListener(new RecyclerAdapter.onRecyclerItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(MainActivity.this,"长按了:"+mDatas.get(position),Toast.LENGTH_SHORT).show();
                adapter.removeItem(position);
                Log.i("tag", "onItemLongClick: "+position);
                Log.i("tag", "集合: "+mDatas.toString());
                Toast.makeText(getContext(),"onItemLongClick: "+position,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private void startInt()
    {
        Intent intent=new Intent(getContext(), IjkAcitivity.class);
        startActivity(intent);
    }


    //初始化数据
    protected void initData(){
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'E'; i++){
            mDatas.add("暗夜贵公子");
        }
    }

}
