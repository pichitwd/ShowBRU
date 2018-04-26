package pichitwandee.th.ac.bru.showbru.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import pichitwandee.th.ac.bru.showbru.R;

public class MyAlert {

    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void namalDialog(String titleString, String messageString) {
        //Shift + Ctrl + Enter
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //Can't undo you must click OK button
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_name);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            //old version int i, new version int which
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


}
