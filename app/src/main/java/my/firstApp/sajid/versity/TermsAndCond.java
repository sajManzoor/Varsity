package my.firstApp.sajid.versity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Sajid on 2/21/2016.
 * Terms and conditions dialog box will be initiated after three seconds of the splash screen.
 */


public class TermsAndCond extends DialogFragment {

   @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle("Terms and Conditions ");
        builder.setMessage(R.string.tandc);

        // declining the terms and conditions lead to exit.
        builder.setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "You cannot proceed to the application", Toast.LENGTH_SHORT).show();

            }
        });

   // accepting the terms and conditions will to the app
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //change to login
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        Dialog dialog=builder.create();

        return dialog;
    }
}
