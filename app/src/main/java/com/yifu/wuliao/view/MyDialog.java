package com.yifu.wuliao.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yifu.wuliao.R;

/**
 * Created by lijilei on 2016/10/25.
 */
public class MyDialog {

    public static void inputDiaglo(final Context activity) {

        // final EditText editText = new EditText(activity);
        // AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // builder.setNegativeButton("确定", new DialogInterface.OnClickListener()
        // {
        //
        // public void onClick(DialogInterface dialog, int which) {
        // getInputContent = editText.getText().toString();
        // Toast.makeText(activity, "输入的内容为：" + editText.getText().toString(),
        // Toast.LENGTH_LONG).show();
        // inoutDialog.InputContent(getInputContent);
        // }
        // });
        // builder.setTitle(inputContent).setView(editText).setPositiveButton("取消",
        // new DialogInterface.OnClickListener() {
        //
        // @Override
        // public void onClick(DialogInterface dialog, int which) {
        // // TODO Auto-generated method stub
        // inoutDialog.CancleContent();
        // dialog.dismiss();
        // }
        // });
        //
        // builder.show();
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = View.inflate(activity,R.layout.input_dialog,null);
        dialog.setContentView(v);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        //dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Dialog mDialog = dialog;
        final EditText editText = (EditText) v.findViewById(R.id.yf_input_edittext);
        Button positiveButton = (Button) v.findViewById(R.id.positiveButton);
        Button negativeButton = (Button) v.findViewById(R.id.negativeButton);
        TextView textView = (TextView) v.findViewById(R.id.title);
       // textView.setText(inputContent);
        positiveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             //   getInputContent = editText.getText().toString();

                dialog.dismiss();
            }
        });
        negativeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
