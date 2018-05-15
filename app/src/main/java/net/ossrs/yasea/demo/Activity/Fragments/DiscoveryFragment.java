package net.ossrs.yasea.demo.Activity.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ossrs.yasea.demo.R;


/**
 * Created by kang on 2018/3/19.
 */

public class DiscoveryFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_discovery,container,false);


        return  view;
    }
}
