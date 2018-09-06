package com.example.aki.runotest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.aki.runotest.R;

public class DialogActivity extends Activity {
    TextView num;
    String phone_no;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setFinishOnTouchOutside(false);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dialog);
            num=findViewById(R.id.number);
            btn=findViewById(R.id.btn);
            phone_no    =   getIntent().getExtras().getString("phone_no");
            num.setText(phone_no);
            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    DialogActivity.this.finish();
                    System.exit(0);
                }
            });

        }
        catch (Exception e){
            Log.d("Exception", e.toString());
            e.printStackTrace();
        }
    }

}
