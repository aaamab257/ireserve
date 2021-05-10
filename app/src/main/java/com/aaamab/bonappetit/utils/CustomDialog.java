package com.aaamab.bonappetit.utils;

import android.app.AlertDialog;
import android.content.Context;


import com.aaamab.bonappetit.R;

import dmax.dialog.SpotsDialog;




public class CustomDialog {
    private AlertDialog progressDialog;
    Context mCon ;

    public CustomDialog(Context mCon) {
        this.mCon = mCon;
        progressDialog = new SpotsDialog(mCon, R.style.Custom );
    }

    public void showDialog(){
        progressDialog.show();
    }
    public void dismissDialog(){
        progressDialog.cancel();
    }
}
