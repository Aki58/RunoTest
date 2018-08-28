package com.example.aki.runotest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class callLogs {
    public static Cursor getAllCallLogs(ContentResolver cr) {
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Uri callUri = Uri.parse("content://call_log/calls");
        Cursor curCallLogs = cr.query(callUri, null, null, null, strOrder);
        return curCallLogs;
    }
}
