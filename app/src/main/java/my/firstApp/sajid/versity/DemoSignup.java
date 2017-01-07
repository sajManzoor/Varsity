package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//just a description class
public class DemoSignup extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_demo_signup, container, false);
        rootView.findViewById(R.id.goToDemoSignUp).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginActivity.userPassed="guest";
               Intent intent=new Intent(getContext(),FilterActivity.class);
                intent.putExtra("userType","guest");
                startActivity(intent);
            }
        });
        return rootView;
    }


}
