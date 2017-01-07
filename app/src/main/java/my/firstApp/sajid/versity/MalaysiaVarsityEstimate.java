package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.vstechlab.easyfonts.EasyFonts;

public class MalaysiaVarsityEstimate extends AppCompatActivity {

    TextView tv44;
    TextView tv;
    TextView tv38;
    TextRoundCornerProgressBar roundCornerProgressBar;
    private CardView est10;
    private String[] data;
    private String value;
    private String userInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malaysia_varsity_estimate);

        tv44=(TextView)findViewById(R.id.tvesttext);
        tv38=(TextView)findViewById(R.id.textView38);
        roundCornerProgressBar=(TextRoundCornerProgressBar)findViewById(R.id.rcp);
        data=getIntent().getStringArrayExtra("allData");
        value=getIntent().getStringExtra("estimate");
        String sign=getIntent().getStringExtra("sign");
        final String examType=getIntent().getStringExtra("text");
        userInput=getIntent().getStringExtra("userValue");

        Float estVal=Float.parseFloat(value);
        Toast.makeText(getApplicationContext()," "+estVal,Toast.LENGTH_LONG).show();
        roundCornerProgressBar.setProgress(estVal);
        roundCornerProgressBar.setTextProgressMargin(-1);
        roundCornerProgressBar.setProgressText(""+estVal+"%");
        roundCornerProgressBar.setTextProgressColor(R.color.col);
        roundCornerProgressBar.setTextProgressSize(40);
        est10=(CardView)findViewById(R.id.est10);
        est10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MySuggestions.class);
                intent.putExtra("examType",examType);
                intent.putExtra("allData",data);
                intent.putExtra("value",value);
                intent.putExtra("userInput",userInput);
                startActivity(intent);
            }
        });


        tv=(TextView)findViewById(R.id.estName);
        tv.setText(data[0]);
        tv.setTypeface(EasyFonts.robotoBold(this));
        tv44.setTypeface(EasyFonts.robotoBold(this));

        tv38.setText("Exam Type seleceted : "+examType);
        tv38.setTypeface(EasyFonts.robotoBold(this));


    }
    }
