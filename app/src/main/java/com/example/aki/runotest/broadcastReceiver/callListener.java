package com.example.aki.runotest.broadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.aki.runotest.ui.DialogActivity;

import java.util.Date;

public class callListener extends Calls {
    Context context;
    @Override
    protected void onIncomingCallStarted(final Context ctx, String number, Date start)
    {   context =   ctx;
        final Intent intent = new Intent(context, DialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("phone_no",number);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                context.startActivity(intent);
            }
        },2000);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
    }
    @Override
    protected void outgoingCallStarted(final Context ctx, String number, Date start){
        context =   ctx;
        final Intent intent = new Intent(context, DialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("phone_no",number);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                context.startActivity(intent);
            }
        },2000);
    }
}
