package com.zhy.todo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zhy.todo.bean.Todo;
import com.zhy.todo.fragment.TodoListFragment;

public class MainActivity extends AppCompatActivity
{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int mTabCount = 2;
    private String[] mTabTitle = {"计划", "已完成"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                switch (position)
                {
                    case 1:
                        return TodoListFragment.newInstance(Todo.TYPE_DONE);
                    default:
                        return TodoListFragment.newInstance(Todo.TYPE_UNDO);
                }
            }

            @Override
            public int getCount()
            {
                return mTabCount;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return mTabTitle[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
