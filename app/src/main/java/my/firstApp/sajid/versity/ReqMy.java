package my.firstApp.sajid.versity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Sajid on 4/19/2016.
 */
public class ReqMy extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle("Requirements");
        builder.setMessage("The requirements are as follows :" + MyDetailedData.requirements);


        // accepting the terms and conditions will to the app
        builder.setPositiveButton("OK",null);
        Dialog dialog=builder.create();

        return dialog;
    }

}
