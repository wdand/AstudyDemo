package com.example.studydemo.recyclerviewchecked.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studydemo.R;
import com.example.studydemo.recyclerviewchecked.adapter.SingleChoiceRecyAdapter;
import com.example.studydemo.recyclerviewchecked.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceActivity extends Activity {

    private static final String TAG = "单选按钮";
    private RecyclerView recyclerView;
    private List<Person> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private Button btn;
    private SingleChoiceRecyAdapter adapter;
    private int checkedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);

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
        adapter = new SingleChoiceRecyAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SingleChoiceRecyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setChecked(false);
                }
                list.get(position).setChecked(true);
                checkedPosition = position;
                adapter.notifyDataSetChanged();
            }
        });


        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "单选的position=" + checkedPosition);
                Toast.makeText(SingleChoiceActivity.this, "" + checkedPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
