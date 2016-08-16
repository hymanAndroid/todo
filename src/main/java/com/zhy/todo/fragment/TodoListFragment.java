package com.zhy.todo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.todo.R;
import com.zhy.todo.bean.Todo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 16/6/6.
 */
public class TodoListFragment extends Fragment
{

    private int type = Todo.TYPE_UNDO;
    private static final String KEY_TYPE = "key_type";


    public static TodoListFragment newInstance(int type)
    {
        TodoListFragment todoListFragment = new TodoListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);
        todoListFragment.setArguments(bundle);
        return todoListFragment;
    }

    private RecyclerView mRvTodoList;
    private RecyclerView.Adapter mAdapter;
    private List<Todo> mDatas = new ArrayList<>();
    private View mRootView;
    private FloatingActionButton mFabAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null)
        {
            type = arguments.getInt(KEY_TYPE, Todo.TYPE_UNDO);
        }
        mDatas.addAll(DataSupport.where(" type = ? ", type + "").find(Todo.class));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mRootView == null)
        {
            mRootView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mRvTodoList = (RecyclerView) view.findViewById(R.id.id_rv_to_list);
        mFabAdd = (FloatingActionButton) view.findViewById(R.id.id_fab_add);
        mFabAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                InputDialog inputDialog = new InputDialog();

                inputDialog.setOnButtonClickListener(new InputDialog.OnButtonClickListener()
                {
                    @Override
                    public void onSure(String content)
                    {
                        Todo todo = new Todo(content);
                        todo.save();
                        mDatas.clear();
                        mDatas.addAll(DataSupport.where(" type = ? ", type + "").find(Todo.class));
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancel()
                    {

                    }
                });
                inputDialog.show(fm, "input_dialog");
            }
        });
        mAdapter = new CommonAdapter<Todo>(getActivity(), R.layout.item_to_list, mDatas)
        {
            @Override
            protected void convert(ViewHolder holder, Todo o, int position)
            {
                holder.setText(R.id.id_tv_time, o.getDateTime());
                holder.setText(R.id.id_tv_content, o.getContent());
            }
        };

        mRvTodoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTodoList.setAdapter(mAdapter);
    }
}
