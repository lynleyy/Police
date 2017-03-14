package com.rzice.lynley.police;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.rzice.lynley.police.bean.Tab;
import com.rzice.lynley.police.fragment.MineFragment;
import com.rzice.lynley.police.fragment.ServiceFragment;
import com.rzice.lynley.police.fragment.ShouyeFragment;
import com.rzice.lynley.police.weiget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        initTab();
    }

    private void initTab() {
        Tab tab_shouye = new Tab(R.string.home, R.drawable.selector_icon_home, ShouyeFragment.class);
      //  Tab tab_hot = new Tab(R.string.hot, R.drawable.selector_icon_hot, ServiceFragment.class);
        Tab tab_sersvice = new Tab(R.string.catagory, R.drawable.selector_icon_category, ServiceFragment.class);
     //   Tab tab_cart = new Tab(R.string.cart, R.drawable.selector_icon_cart, CartFragment.class);
        Tab tab_mine = new Tab(R.string.mine, R.drawable.selector_icon_mine, MineFragment.class);

        mTabs.add(tab_shouye );
 //       mTabs.add(tab_hot);
        mTabs.add(tab_sersvice);
//        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
