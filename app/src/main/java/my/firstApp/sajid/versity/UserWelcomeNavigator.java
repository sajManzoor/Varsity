package my.firstApp.sajid.versity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;


public class UserWelcomeNavigator extends Fragment {

    private TextView notes;
    private TextView wel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_welcome_navigator, container, false);
        notes=(TextView)rootView.findViewById(R.id.textView32);
        notes.setTypeface(EasyFonts.robotoThin(getActivity()));
        wel=(TextView)rootView.findViewById(R.id.textView33);
        if(FilterActivity.innerUser==null)
        {
            setData("Welcome Guest ");
        }
        else {
            setData("Welcome " + FilterActivity.innerUser);
        }
        return rootView;
    }

    public void setData(String text)
    {
        wel.setText(text);
    }

}
