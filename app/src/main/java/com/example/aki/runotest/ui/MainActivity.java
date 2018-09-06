package com.example.aki.runotest.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aki.runotest.R;
import com.example.aki.runotest.adapter;
import com.example.aki.runotest.callLogs;
import com.example.aki.runotest.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private ArrayList<user> Users=new ArrayList<>();
    private RecyclerView recyclerView;
    private adapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test","opened");
        if (!checkPermission()){
        requestPermission();}
        else {recyclerView=findViewById(R.id.recyclerView);
            userAdapter= new adapter(Users);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(userAdapter);
            Cursor curLog = callLogs.getAllCallLogs(getContentResolver());
            setCallLogs(curLog);}
    }
    private void setCallLogs(Cursor curLog) {
        while (curLog.moveToNext()) {
            String callNumber = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER));
            user u=new user("","","","");
            u.setNumber(callNumber);
            String callDate = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.DATE));
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm");
            String dateString = formatter.format(new Date(Long
                    .parseLong(callDate)));
            u.setDate(dateString);
            String callType = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.TYPE));
            if (callType.equals("1")) {
                u.setType("Incoming");
            } else u.setType("Outgoing");
            String duration = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.DURATION));
            u.setTime(duration);
            Users.add(u);
        }
        userAdapter.notifyDataSetChanged();
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),READ_PHONE_STATE );
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),READ_CONTACTS );
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),READ_CALL_LOG );

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2==PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{READ_PHONE_STATE,READ_CONTACTS,READ_CALL_LOG}, PERMISSION_REQUEST_CODE);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean phoneStateAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean contactsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean callLogAccepted=grantResults[2]==PackageManager.PERMISSION_GRANTED;

                    if (phoneStateAccepted&&contactsAccepted&&callLogAccepted)
                    {
                        Intent i=getIntent();
                        finish();
                        startActivity(i);
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{READ_PHONE_STATE,READ_CONTACTS,READ_CALL_LOG},
                                PERMISSION_REQUEST_CODE);
                    }
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
