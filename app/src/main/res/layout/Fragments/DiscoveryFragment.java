package com.example.acer.mymusic.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.acer.mymusic.Activity.NoteEdit;
import com.example.acer.mymusic.DB.NoteDB;
import com.example.acer.mymusic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kang on 2018/3/19.
 */

public class DiscoveryFragment extends Fragment implements AbsListView.OnScrollListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{

    private ListView listview;
    private TextView tv_content;
    private List<HashMap<String,Object>> mapList;
    private Button addNote;
    private NoteDB DB;
    private SimpleAdapter simp_adapter;
    private SQLiteDatabase dbread;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_discovery,container,false);

        addNote = (Button)view.findViewById(R.id.btn_editnote);
        tv_content = (TextView)view.findViewById(R.id.tv_content);
        listview = (ListView)view.findViewById(R.id.listview);
        mapList=new ArrayList<HashMap<String, Object>>();


        Inits();

        ////点击跳转事件
        addNote.setOnClickListener(new View.OnClickListener()
        {
            ////利用bundle进行传值
            @Override
            public void onClick(View v) {
                NoteEdit.ENTER_STATE = 0;
                Intent intent=new Intent(getActivity(),NoteEdit.class);
                Bundle bundle=new Bundle();
                bundle.putString("info","");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        DB=new NoteDB(getContext());
        dbread=DB.getReadableDatabase();

        RefreshNotesList();

        /////三个交互事件的实现
        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
        listview.setOnScrollListener(this);


        return  view;
    }

    ////清空数据库里面的内容
    private void RefreshNotesList()
    {
         int size=mapList.size();

         if (size>0)
         {
             mapList.removeAll(mapList);
            simp_adapter.notifyDataSetChanged();
         }
         simp_adapter=new SimpleAdapter(getContext(),getData(),R.layout.note_item,new String[]
                 { "tv_content", "tv_date" },new int[]{R.id.tv_content, R.id.tv_date});
         listview.setAdapter(simp_adapter);
    }

    private List<HashMap<String,Object>> getData()
    {

        Cursor cursor = dbread.query("note", null, "content!=\"\"", null, null,
                null, null);


        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tv_content", name);
            map.put("tv_date", date);
            mapList.add(map);
        }

        cursor.close();
        return mapList;
    }

    private void Inits()
    {


    }

    // 点击listview中某一项的监听事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        NoteEdit.ENTER_STATE = 1;

        ////通过position来获取字符串并且凭借起来
        String content = listview.getItemAtPosition(position) + "";
        String content1 = content.substring(content.indexOf("=") + 1,
                content.indexOf(","));

        ///差别就在于content1
        Cursor c = dbread.query("note", null,
                "content=" + "'" + content1 + "'", null, null, null, null);

       while (c.moveToNext())
       {
           String No = c.getString(c.getColumnIndex("_id"));
           Intent myIntent = new Intent();
           Bundle bundle = new Bundle();
           bundle.putString("info", content1);
           NoteEdit.id = Integer.parseInt(No);
           myIntent.putExtras(bundle);
           myIntent.setClass(getContext(), NoteEdit.class);
           startActivityForResult(myIntent, 1);
       }
    }

    // 滑动listview监听事件
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Log.i("main", "用户在手指离开屏幕之前，由于用力的滑了一下，视图能依靠惯性继续滑动");
            case SCROLL_STATE_IDLE:
                Log.i("main", "视图已经停止滑动");
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("main", "手指没有离开屏幕，试图正在滑动");
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        final int n=position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("删除该日志");
        builder.setMessage("确认删除吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = listview.getItemAtPosition(n) + "";
                String content1 = content.substring(content.indexOf("=") + 1,
                        content.indexOf(","));
                Cursor c = dbread.query("note", null, "content=" + "'"
                        + content1 + "'", null, null, null, null);
                while (c.moveToNext()) {
                    String id = c.getString(c.getColumnIndex("_id"));
                    String sql_del = "update note set content='' where _id="
                            + id;
                    dbread.execSQL(sql_del);
                    RefreshNotesList();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
        return true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            RefreshNotesList();
        }
    }
}
