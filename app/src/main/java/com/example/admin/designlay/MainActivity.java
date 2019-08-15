package com.example.admin.designlay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    String[] roll_no = new String[]{"13", "208","13", "208","13", "208","13", "208"};
    String[] name = new String[]{"Abhishek Mathur", "Ramesh Kumar Thorati","Abhishek Mathur", "Ramesh Kumar Thorati","Abhishek Mathur", "Ramesh Kumar Thorati","Abhishek Mathur", "Ramesh Kumar Thorati"};
    String[] pa = new String[]{"0", "1","0", "1","0", "1","0", "1"};
    String[] comment = new String[]{"he came late", "he came late again","he came late", "he came late again","he came late", "he came late again","he came late", "he came late again"};
    ListView studentlist;
    StudentAdapter studentAdapter;
    LinearLayout submitlay;
    Spinner classspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Class Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backarrow);
        studentlist = (ListView) findViewById(R.id.studentlist);
        submitlay=(LinearLayout)findViewById(R.id.submitlay);
        classspinner=(Spinner)findViewById(R.id.classspinner);
        final List<String> list = new ArrayList<String>();
        list.add("Class1-A");
        list.add("Class4A");
        list.add("Class12A");
        list.add("Class12-B");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinneritemlayout, list);
        dataAdapter.setDropDownViewResource(R.layout.spinneritemlayout);
        classspinner.setAdapter(dataAdapter);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String cname=pref.getString("name","aa");
        studentAdapter =new StudentAdapter(this,roll_no,name,pa,comment);
        studentlist.setAdapter(studentAdapter);
        submitlay.setOnClickListener(this);
        studentlist.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitlay:
                Intent i=new Intent(this,Class2.class);
                startActivity(i);
                break;
        }
    }

    public class StudentAdapter extends BaseAdapter{

        Context s_context;
        String[] s_name,s_rollno,s_pa,s_comment;
        TextView rollnotxt,nametxt;
        EditText commenttxt;
        ImageView paimg;

        StudentAdapter(Context context,String[] rollno,String[] name,String[] pa,String[] comment ){
            s_context=context;
            s_rollno=rollno;
            s_name=name;
            s_pa=pa;
            s_comment=comment;
        }
        @Override
        public int getCount() {
            return s_rollno.length;
        }

        @Override
        public Object getItem(int i) {
            return s_rollno[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(s_context).
                        inflate(R.layout.listitemlayout, viewGroup, false);
            }

            rollnotxt=(TextView)view.findViewById(R.id.rollnotxt);
            nametxt=(TextView)view.findViewById(R.id.nametxt);
            commenttxt=(EditText) view.findViewById(R.id.commenttxt);
            paimg=(ImageView)view.findViewById(R.id.paimg);

            rollnotxt.setText(s_rollno[i]);
            nametxt.setText(s_name[i]);
            commenttxt.setText(s_comment[i]);

            String icon=s_pa[i];
            if(icon=="0"){
               paimg.setImageResource(R.drawable.present);
            }else{
                paimg.setImageResource(R.drawable.absent);
            }

            paimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("click","yes");
                    if(s_pa[i]=="0"){
                        Log.e("click","yes1");
                        s_pa[i]="1";
                        notifyDataSetChanged();
                        //paimg.setImageResource(R.drawable.absent);
                    }else{
                        Log.e("click","yes2");
                        s_pa[i]="0";
                        notifyDataSetChanged();
                       // paimg.setImageResource(R.drawable.present);
                    }

                }
            });
            return view;
        }
    }
}
