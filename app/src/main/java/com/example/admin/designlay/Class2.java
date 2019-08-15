package com.example.admin.designlay;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Class2 extends AppCompatActivity implements View.OnClickListener{
    LinearLayout attachmentlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class2);
        getSupportActionBar().setTitle("Homework");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backarrow2);
        attachmentlay=(LinearLayout)findViewById(R.id.attachmentlay);
        attachmentlay.setOnClickListener(this);

    }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.attachmentlay:
                    final BottomSheetDialog dialog = new BottomSheetDialog(Class2.this); dialog.setContentView(R.layout.dialoglay);
                    LinearLayout dismissdialog;
                    dismissdialog=(LinearLayout)dialog.findViewById(R.id.dismissdialog);
                    dismissdialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    break;
            }
        }
    }

