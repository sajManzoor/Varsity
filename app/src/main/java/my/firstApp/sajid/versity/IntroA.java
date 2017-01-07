package my.firstApp.sajid.versity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;


public class IntroA extends Fragment {


    TextView textView;
    TextView textView2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);
        textView=(TextView)rootView.findViewById(R.id.textView33);
        textView.setTypeface(EasyFonts.tangerineBold(getActivity()));
        textView2=(TextView)rootView.findViewById(R.id.textView32);
        textView2.setTypeface(EasyFonts.tangerineBold(getActivity()));
        return rootView;
    }


}
