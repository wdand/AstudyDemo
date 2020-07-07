package com.example.studydemo.recyclerviewchecked.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studydemo.R;
import com.example.studydemo.recyclerviewchecked.adapter.BgMultipleChoiceRecyAdapter;
import com.example.studydemo.recyclerviewchecked.bean.Person;
import com.example.studydemo.recyclerviewchecked.view.ChoiceItemLayout;
import com.example.studydemo.recycleviewdemo.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;


public class BgMultipleChoiceActivity extends AppCompatActivity {

    private static final String TAG = "多选框背景色";
    private RecyclerView recyclerView;
    private List<Person> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private Button btn;
    private BgMultipleChoiceRecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg_multiple_choice);

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
        adapter = new BgMultipleChoiceRecyAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BgMultipleChoiceRecyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "position=" + position);

                BgMultipleChoiceRecyAdapter.MyHolder holder = (BgMultipleChoiceRecyAdapter.MyHolder) recyclerView.getChildViewHolder(view);
                SwipeMenuLayout itemView = (SwipeMenuLayout) holder.itemView;
                //先改数据，再改变背景色
                Person person = list.get(position);
                person.setChecked(!person.isChecked());

                itemView.setChecked(person.isChecked());
            }
        });

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    Person person = list.get(i);
                    if (person.isChecked()) {
                        sb.append(person.getName() + ",");
                    }
                }
                Log.d(TAG, "sb=" + sb.toString());
                Toast.makeText(BgMultipleChoiceActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
