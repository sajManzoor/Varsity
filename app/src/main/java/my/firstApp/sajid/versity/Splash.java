package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

public class Splash extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private TextView textView;
    private TextView textView2;


    @Override
    public void onStart() {
        super.onStart();


    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        textView=(TextView)findViewById(R.id.textView33);
        textView.setTypeface(EasyFonts.tangerineBold(this));
        textView2=(TextView)findViewById(R.id.textView32);
        textView2.setTypeface(EasyFonts.tangerineBold(this));



        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this, LoginActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}