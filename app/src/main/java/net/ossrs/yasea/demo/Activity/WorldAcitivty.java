package net.ossrs.yasea.demo.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import net.ossrs.yasea.demo.Activity.Adapter.myFragmentPagerAdapter;
import net.ossrs.yasea.demo.Activity.Fragments.ContactsFragment;
import net.ossrs.yasea.demo.Activity.Fragments.DiscoveryFragment;
import net.ossrs.yasea.demo.Activity.Fragments.MeFragment;
import net.ossrs.yasea.demo.Activity.Fragments.WeChatFragment;
import net.ossrs.yasea.demo.Activity.Uitls.RadioButtonUtil;
import net.ossrs.yasea.demo.R;
import java.util.ArrayList;

/**
 * Created by kang on 2018/3/15.
 */


public class WorldAcitivty extends FragmentActivity
{

    private RadioGroup mGroup;
    private RadioButton rbChat,rbContacts,rbDiscovery,rbMe;
    private ArrayList<Fragment> fragmentList;
    private ViewPager mPager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ///设置全屏操作
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.world_layout);


        //初始化界面组件
        initView();
        //初始化ViewPager
        initViewPager();


        int[] rb_id={R.id.rb_chat,R.id.rb_contacts,R.id.rb_discovery,R.id.rb_me};

        RadioButtonUtil radioButton=new RadioButtonUtil(rb_id,this,1,5);
    }

    public void initView(){
        mPager=(ViewPager)findViewById(R.id.viewPager);
        mGroup=(RadioGroup)findViewById(R.id.radiogroup);
        rbChat=(RadioButton)findViewById(R.id.rb_chat);
        rbContacts=(RadioButton)findViewById(R.id.rb_contacts);
        rbDiscovery=(RadioButton)findViewById(R.id.rb_discovery);
        rbMe=(RadioButton)findViewById(R.id.rb_me);
        //RadioGroup选中状态改变监听
        mGroup.setOnCheckedChangeListener(new myCheckChangeListener());

    }

    private void initViewPager(){
        WeChatFragment weChatFragment=new WeChatFragment();
        ContactsFragment contactsFragment=new ContactsFragment();
        DiscoveryFragment discoveryFragment=new DiscoveryFragment();
        MeFragment meFragment=new MeFragment();
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(weChatFragment);
        fragmentList.add(contactsFragment);
        fragmentList.add(discoveryFragment);
        fragmentList.add(meFragment);
        //ViewPager设置适配器
        mPager.setAdapter(new myFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));

        //ViewPager显示第一个Fragment
        mPager.setCurrentItem(0);
        //ViewPager页面切换监听
        mPager.addOnPageChangeListener(new myOnPageChangeListener());
    }


    /**
     *RadioButton切换Fragment
     */
    private class myCheckChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_chat:
                    //ViewPager显示第一个Fragment且关闭页面切换动画效果
                    mPager.setCurrentItem(0,false);
                    break;
                case R.id.rb_contacts:
                    mPager.setCurrentItem(1,false);
                    break;
                case R.id.rb_discovery:
                    mPager.setCurrentItem(2,false);
                    break;
                case R.id.rb_me:
                    mPager.setCurrentItem(3,false);
                    break;
            }
        }
    }


    /**
     *ViewPager切换Fragment,RadioGroup做相应变化
     */
    private class myOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    mGroup.check(R.id.rb_chat);
                    break;
                case 1:
                    mGroup.check(R.id.rb_contacts);
                    break;
                case 2:
                    mGroup.check(R.id.rb_discovery);
                    break;
                case 3:
                    mGroup.check(R.id.rb_me);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
