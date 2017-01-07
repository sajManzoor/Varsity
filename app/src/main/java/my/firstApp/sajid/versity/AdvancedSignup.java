package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AdvancedSignup extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_advanced_signup, container, false);
        rootView.findViewById(R.id.signUpAdvanced).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                goToAdvancedSignup();
            }
        });
        return rootView;
    }

public void goToAdvancedSignup()
{
    Intent intent=new Intent(getContext(),Signup.class);
    startActivity(intent);
}
}
