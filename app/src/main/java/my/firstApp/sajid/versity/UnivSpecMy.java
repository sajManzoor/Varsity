package my.firstApp.sajid.versity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Sajid on 4/19/2016.
 */
public class UnivSpecMy extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle("Details :");
        MyDetailedData obj=new MyDetailedData();
        builder.setMessage("The Details are as follows :" + MyDetailedData.uniSpec);


        // accepting the terms and conditions will to the app
        builder.setPositiveButton("OK",null);
        Dialog dialog=builder.create();

        return dialog;
    }

}
