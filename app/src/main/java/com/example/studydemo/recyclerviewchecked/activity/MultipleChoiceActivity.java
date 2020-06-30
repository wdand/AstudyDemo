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
import com.example.studydemo.recyclerviewchecked.adapter.MulipleRecyAdapter;
import com.example.studydemo.recyclerviewchecked.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceActivity extends AppCompatActivity {

    private static final String TAG = "多选框";
    private RecyclerView recyclerView;
    private List<Person> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private Button btn;
    private List<Integer> posiList = new ArrayList<>();
    private MulipleRecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

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
        adapter = new MulipleRecyAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MulipleRecyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "position=" + position);

                MulipleRecyAdapter.MyHolder holder = (MulipleRecyAdapter.MyHolder) recyclerView.getChildViewHolder(view);
                //方式一：（有时会出错，不建议这样使用）
//                holder.checkBox.toggle();
//                list.get(position).setChecked(holder.checkBox.isChecked());

                //方式二：
                Person person = list.get(position);
                person.setChecked(!person.isChecked());
                holder.checkBox.setChecked(person.isChecked());
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
                        sb.append(i + ",");
                    }
                }
                Toast.makeText(MultipleChoiceActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
