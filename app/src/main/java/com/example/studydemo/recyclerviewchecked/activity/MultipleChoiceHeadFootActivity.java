package com.example.studydemo.recyclerviewchecked.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studydemo.R;
import com.example.studydemo.recyclerviewchecked.adapter.MultipleChoiceHeadFootAdapter;
import com.example.studydemo.recyclerviewchecked.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceHeadFootActivity extends AppCompatActivity {

    private static final String TAG = "多选+头尾布局";
    private RecyclerView recyclerView;
    private List<Person> list = new ArrayList<>();
    private MultipleChoiceHeadFootAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_head_foot);

        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setName("张" + i);
            person.setAge(i);
            person.setChecked(false);
            list.add(person);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MultipleChoiceHeadFootAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultipleChoiceHeadFootAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "position=" + position);
                MultipleChoiceHeadFootAdapter.MyHolder holder = (MultipleChoiceHeadFootAdapter.MyHolder) recyclerView.getChildViewHolder(view);
                holder.checkBox.toggle();
                list.get(position-1).setChecked(holder.checkBox.isChecked());
            }
        });

        View headView = LayoutInflater.from(this).inflate(R.layout.layout_header, recyclerView, false);
        View footView = LayoutInflater.from(this).inflate(R.layout.layout_footer, recyclerView, false);
        adapter.addHeadView(headView);
        adapter.addFootView(footView);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        sb.append(list.get(i).getName() + ",");
                    }
                }
                Log.d(TAG, "sb=" + sb.toString());
                Toast.makeText(MultipleChoiceHeadFootActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //方法一：错误，holder=null
    //                MultipleChoiceHeadFootAdapter.MyHolder holder = (MultipleChoiceHeadFootAdapter.MyHolder) view.getTag();
    //方法二：checkBox= null
    //               View view2 = recyclerView.getChildAt(position - layoutManager.findFirstVisibleItemPosition());
    //                MultipleChoiceHeadFootAdapter.MyHolder holder = (MultipleChoiceHeadFootAdapter.MyHolder) recyclerView.getChildViewHolder(view2);
}
