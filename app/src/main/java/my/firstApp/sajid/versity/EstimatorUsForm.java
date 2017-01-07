package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EstimatorUsForm extends AppCompatActivity {

    private String[] scores;
    private int act25;
    private int act25Eng;
    private int act25Math;
    private int act25Writing;
    private int act75;
    private int act75Eng;
    private int act75Math;
    private int act75Writing;
    private double finalEstimate;

    private int sat25;
    private int sat25Eng;
    private int sat25Math;
    private int sat25Writing;
    private int sat75;
    private int sat75Reading;
    private int sat75Math;
    private int sat75Writing;
    private EditText ed2;
    private EditText ed3;
    private EditText ed4;
    private Double userInput1;
    private Double userInput2;
    private Double userInput3;
    private Double userScore;
    private String text;
    private  String edt1;
    private  String edt2;
    private  String edt3;
    private Button est;
    private String image;
    private String code;
    private String sign;
    private Spinner sItems;
    private TextView toefl;
    private TextInputLayout e1;
    private TextInputLayout e2;
    private TextInputLayout e3;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimator_us);
         scores= getIntent().getStringArrayExtra("scores");
        image=getIntent().getStringExtra("Image");
        code=getIntent().getStringExtra("code");
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);
        ed4=(EditText)findViewById(R.id.editText4);
        est=(Button)findViewById(R.id.buttonEstimator);
        toefl=(TextView)findViewById(R.id.textView99);
        e1=(TextInputLayout)findViewById(R.id.ed2);
        e2=(TextInputLayout)findViewById(R.id.ed3);
        e3=(TextInputLayout)findViewById(R.id.ed4);
        toefl.setText("TOEFL : "+scores[0]);
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("SAT");
        spinnerArray.add("ACT");

        uid=getIntent().getStringExtra("id");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.usspinner);
        sItems.setAdapter(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String texts = sItems.getSelectedItem().toString();

                if (texts.equals("SAT")) {
                    e1.setHint("Reading");
                    e2.setHint("Writing");
                    e3.setHint("Math");
                }
                if (texts.equals("ACT")) {
                    e1.setHint("English");
                    e2.setHint("Science/Composite");
                    e3.setHint("Math");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        processData(scores);


        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estimate();
            }
        });
    }

    public void processData(String[] scores)
    {
        //act25 scores
        if(scores[1].equals("null")||scores[1].trim().equals(""))
        {

            if(scores[2].equals("null")||scores[2].trim().equals(""))
            {
                scores[2]="19";
            }
            if(scores[3].equals("null")||scores[3].trim().equals(""))
            {

                scores[3]="20";
            }
            if(scores[4].equals("null")||scores[4].trim().equals(""))
            {
                scores[4]="19";
            }
          try {
              act25Eng = Integer.parseInt(scores[2]);
              act25Math = Integer.parseInt(scores[3]);
              act25Writing = Integer.parseInt(scores[4]);
              act25 = (act25Eng + act25Math + act25Writing) / 3;
          }
          catch (NumberFormatException ne){
              Toast.makeText(getApplicationContext(),"Invalid Input ",Toast.LENGTH_LONG).show();

          }


       }
            else
        {
            act25=Integer.parseInt(scores[1]);
        }


        //act75 scores

        if(scores[5].equals("null")||scores[5].trim().equals(""))
        {
            if(scores[7].equals("null")||scores[7].trim().equals(""))
            {
                scores[7]="25";
            }
            if(scores[8].equals("null")||scores[8].trim().equals(""))
            {
                scores[8]="26";
            }
            if(scores[9].equals("null")||scores[9].trim().equals(""))
            {
                scores[9]="24";
            }

            act75Eng=Integer.parseInt(scores[7]);
            act75Math=Integer.parseInt(scores[8]);
            act75Writing=Integer.parseInt(scores[9]);
            act75=(act75Eng+act75Math+act75Writing)/3;
        }

        else
        {
            act75=Integer.parseInt(scores[5]);
        }





        //sat25 scores

        if(scores[12].equals("null")||scores[12].trim().equals(""))
        {
            if(scores[13].equals("null")||scores[13].trim().equals(""))
            {
                scores[13]="511";
            }
            if(scores[14].equals("null")||scores[14].trim().equals(""))
            {
                scores[14]="495";
            }
            if(scores[15].equals("null")||scores[15].trim().equals(""))
            {
                scores[15]="484";
            }
            sat25Eng=Integer.parseInt(scores[14]);
            sat25Math=Integer.parseInt(scores[13]);
            sat25Writing=Integer.parseInt(scores[15]);
            sat25=(sat25Eng+sat25Math+sat25Writing)/3;
        }

        else
        {
            sat25=Integer.parseInt(scores[12]);
        }

        //sat75 scores

        if(scores[16].equals("null")||scores[16].trim().equals(""))
        {
            if(scores[17].equals("null")||scores[17].trim().equals(""))
            {
                scores[17]="660";
            }
            if(scores[18].equals("null")||scores[18].trim().equals(""))
            {
                scores[18]="630";
            }
            if(scores[19].equals("null")||scores[19].trim().equals(""))
            {
                scores[19]="610";
            }

            sat75Reading=Integer.parseInt(scores[18]);
            sat75Math=Integer.parseInt(scores[17]);
            sat75Writing=Integer.parseInt(scores[19]);
            sat75=(sat75Reading+sat75Math+sat75Writing)/3;

        }

        else
        {
            sat75=Integer.parseInt(scores[16]);
        }

       // Toast.makeText(this, sat25 + " "+sat75+" "+act75+" "+act25, Toast.LENGTH_LONG).show();









    }


    public void estimate()
    {


        edt1=ed2.getText().toString();
        edt2=ed3.getText().toString();
        edt3=ed4.getText().toString();


        try {
            int userComp1 = Integer.parseInt(edt1);
            int userComp2 = Integer.parseInt(edt2);
            int userComp3 = Integer.parseInt(edt3);
            int userTot = (userComp1 + userComp2 + userComp3) / 3;
            userInput1 = Double.parseDouble(edt1);
            userInput2 = Double.parseDouble(edt2);
            userInput3 = Double.parseDouble(edt3);


            userScore = (userInput1 + userInput2 + userInput3) / 3;

            text = sItems.getSelectedItem().toString();

            if (text.equals("ACT")) {
                if (userTot >= act75) {
                    sign = ">75%";

                    finalEstimate = 75 + (((userScore - act75) / 36) * 25);
                  //  Toast.makeText(this, " 75 " + userTot + " act : " + act75 + "result " + finalEstimate, Toast.LENGTH_LONG).show();

                }

                if (userTot < act75 && userTot >= act25) {
                    sign = "25-75%";
                    finalEstimate = 25 + (((userScore - act25) / 36) * 50);
                 //   Toast.makeText(this, " 25 " + userTot + " act : " + act25 + "result " + finalEstimate, Toast.LENGTH_LONG).show();

                }

                if (userTot < act25) {
                    sign = "<25%";
                    finalEstimate = 25 - (((act25 - userScore) / 36) * 25);
                  //  Toast.makeText(this, "< 25 " + userTot + " act : " + act25 + "result " + finalEstimate, Toast.LENGTH_LONG).show();
                }
            } else if (text.equals("SAT")) {
                if (userTot >= sat75) {
                    sign = ">75%";
                    finalEstimate = 75 + (((userScore - sat75) / 2400) * 25);
                   // Toast.makeText(this, " 75 " + userTot + " sat : " + sat75 + "result " + finalEstimate, Toast.LENGTH_LONG).show();
                }

                if (userTot < sat75 && userTot >= sat25) {
                    sign = "25-75%";
                    finalEstimate = 25 + (((userScore - sat25) / 2400) * 50);
                  //  Toast.makeText(this, " 25 " + userTot + " sat : " + sat25 + "result " + finalEstimate, Toast.LENGTH_LONG).show();
                }

                if (userTot < sat25) {
                    sign = "<25%";
                    finalEstimate = 25 - (((sat25 - userScore) / 2400) * 25);
                  //  Toast.makeText(this, "< 25 " + userTot + " sat : " + sat25 + "result " + finalEstimate, Toast.LENGTH_LONG).show();
                }


            }


            String sendEst=String.valueOf(finalEstimate);
            Intent intent=new Intent(this,UsVarsityEstimator.class);
            intent.putExtra("Image",image);
            intent.putExtra("code",code);
            intent.putExtra("sign",sign);
            intent.putExtra("userTotal",String.valueOf(userScore));
            intent.putExtra("text",text);
            intent.putExtra("estimate",sendEst);
            intent.putExtra("appFee",scores[40]);
            intent.putExtra("id",uid);
            startActivity(intent);

        }

        catch (NumberFormatException ne){
            Toast.makeText(getApplicationContext(),"Invalid Input ",Toast.LENGTH_LONG).show();

        }


    }

}