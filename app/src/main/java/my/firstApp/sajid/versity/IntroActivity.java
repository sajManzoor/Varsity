package my.firstApp.sajid.versity;

// Please DO NOT override onCreate. Use init

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {
        // Please DO NOT override onCreate. Use init
        @Override
        public void init(Bundle savedInstanceState) {


           IntroA f1=new IntroA();
           addSlide(f1);
            SecondIntro f2 =new SecondIntro();
            addSlide(f2);
            IntroEstimate f3=new IntroEstimate();
            addSlide(f3);
          //  setDepthAnimation();
            setFlowAnimation();
            // setSlideOverAnimation();
           // setFadeAnimation();
            //addSlide(AppIntroFragment.newInstance("hh", "hhh", R.drawable.varlogo, R.color.col));
               setBarColor(Color.parseColor("#039BE5"));
               setSeparatorColor(Color.parseColor("#039BE5"));
            showStatusBar(true);


                /* Animations -- use only one of the below. Using both could cause errors.

                setZoomAnimation(); // OR
                 // OR
                 // OR

                setCustomTransformer(yourCustomTransformer);
*/

        }

        @Override
        public void onNextPressed() {
                // Do something when users tap on Next button.
        }

        @Override
        public void onDonePressed() {
            TermsAndCond tandc=new TermsAndCond();
            tandc.show(getFragmentManager(), "Terms And Conditions ");
        }

        @Override
        public void onSlideChanged() {
                // Do something when slide is changed
        }

    @Override
    public void onSkipPressed()
    {
        TermsAndCond tandc=new TermsAndCond();
        tandc.show(getFragmentManager(), "Terms And Conditions ");
    }



}