package com.example.strong.remotecontrol;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2015/11/6.
 */
public abstract class SingleFragmentActivity extends Activity{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager=getFragmentManager();
        Fragment fragment =fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment==null){
            fragment=createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer,fragment).commit();
        }
    }
}
