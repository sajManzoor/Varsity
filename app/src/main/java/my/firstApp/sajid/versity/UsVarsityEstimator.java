package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.vstechlab.easyfonts.EasyFonts;

public class UsVarsityEstimator extends AppCompatActivity {

    ImageView imageView;
    TextView tv44;
    TextView tv;
    TextView tv38;
    TextRoundCornerProgressBar roundCornerProgressBar;
    private CardView est10;
    private String code;
    private String userScore;
    private String text;
    private String sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_varsity_estimator);
        tv44=(TextView)findViewById(R.id.tvesttext);
        imageView=(ImageView)findViewById(R.id.imageView11);
        tv38=(TextView)findViewById(R.id.textView38);
        roundCornerProgressBar=(TextRoundCornerProgressBar)findViewById(R.id.rcp);
        String est=getIntent().getStringExtra("estimate");
        sign=getIntent().getStringExtra("sign");

        userScore=getIntent().getStringExtra("userTotal");
        text=getIntent().getStringExtra("text");



        Float estVal=Float.parseFloat(est);
        if(sign.equals("25-75%")) {
            float v = (float) 50.3;
            estVal = v;
        }

        if(sign.equals("<25%")) {
            float v = (float) 25.3;
            estVal = v;
        }

        roundCornerProgressBar.setProgress(estVal);
        roundCornerProgressBar.setProgressText(sign);
        roundCornerProgressBar.setTextProgressMargin(-1);
        roundCornerProgressBar.setTextProgressColor(R.color.myBlue);
        roundCornerProgressBar.setTextProgressSize(40);
        est10=(CardView)findViewById(R.id.est10);
        est10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UsSuggestions.class);
                intent.putExtra("userScore",userScore);
                intent.putExtra("text",text);
                intent.putExtra("sign",sign);
                startActivity(intent);

            }
        });


        String code=getIntent().getStringExtra("code");
        tv=(TextView)findViewById(R.id.estName);
        tv.setText(code);
        tv.setTypeface(EasyFonts.robotoBold(this));
         tv44.setTypeface(EasyFonts.robotoBold(this));
        String appFee=getIntent().getStringExtra("appFee");
        if(appFee.equals("null")||appFee.trim().equals("")||appFee.equals("n/a")||appFee.equals("na")||appFee.equals("0"))
        {
            tv38.setText("Application Fee : n/a");
        }
        else
        {
            tv38.setText("Application Fee : $"+appFee);
        }
        tv38.setTypeface(EasyFonts.robotoBold(this));


    }
}
