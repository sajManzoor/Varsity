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

public class MalaysiaFormEstimate extends AppCompatActivity {

    private Spinner myItems;
    private TextView toefl;
    private TextView ielts;
    private String[] myData;
    private Button estimator;
    private String text;
    private EditText userInput;
    private String estValue;
    private double compValue;
    private String userInputValue;
    private String sign;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malaysia_form_estimate);
        userInput=(EditText)findViewById(R.id.editText2);
        toefl=(TextView)findViewById(R.id.textView99);
        estimator=(Button)findViewById(R.id.buttonEstimatorMy);
        ielts=(TextView)findViewById(R.id.textView100);
        myData=getIntent().getStringArrayExtra("alldata");
        textInputLayout=(TextInputLayout)findViewById(R.id.ed2);
        if(myData[39].trim().equals("")||myData[39].equals("n/a")||myData[39]==null||myData.equals("null"))
        {
            toefl.setText("");
        }
        else
            toefl.setText("TOEFL : " +myData[39]);

        if(myData[40].trim().equals("")||myData[40].equals("n/a")||myData[40]==null||myData.equals("null"))
        {
            ielts.setText("");
        }
       else
        ielts.setText("IELTS : "+myData[40]);


        List<String> spinnerArray = new ArrayList<String>();
        if(!(myData[41].equals("n/a"))&&!(myData[41].equals(""))&&!(myData[41].equals("null"))&&!(myData[41]==null))
        {
            spinnerArray.add("SPM or O-levels");
        }

        if(!(myData[42].equals("n/a"))&&!(myData[42].equals(""))&&!(myData[42].equals("null"))&&!(myData[42]==null))
        {
            spinnerArray.add("STPM or GCSE or A-Levels");
        }

        if(!(myData[43].equals("n/a"))&&!(myData[43].equals(""))&&!(myData[43].equals("null"))&&!(myData[43]==null))
        {
            spinnerArray.add("AUSMAT");
        }


        if(!(myData[44].equals("n/a"))&&!(myData[44].equals(""))&&!(myData[44].equals("null"))&&!(myData[44]==null))
        {
            spinnerArray.add("CPU");
        }

        if(!(myData[45].equals("n/a"))&&!(myData[45].equals(""))&&!(myData[45].equals("null"))&&!(myData[45]==null))
        {
            spinnerArray.add("UEC");
        }
        if(!(myData[46].equals("n/a"))&&!(myData[46].equals(""))&&!(myData[46].equals("null"))&&!(myData[46]==null))
        {
            spinnerArray.add("International Board");
        }

        if(!(myData[47].equals("n/a"))&&!(myData[47].equals(""))&&!(myData[47].equals("null"))&&!(myData[47]==null))
        {
            spinnerArray.add("Indian Board");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myItems = (Spinner) findViewById(R.id.myspinner);
        myItems.setAdapter(adapter);



        myItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text = myItems.getSelectedItem().toString();

                if(text.equals("SPM or O-levels")) {
                    textInputLayout.setHint("Grade or gpa/4");
                }
                if(text.equals("STPM or GCSE or A-Levels")) {
                    textInputLayout.setHint("Gpa/4 :");
                }
                if(text.equals("AUSMAT")) {
                    textInputLayout.setHint("Percentage :");
                }

                if(text.equals("CPU")) {
                    textInputLayout.setHint("Percentage :");
                }

                if(text.equals("UEC")) {
                    textInputLayout.setHint("Gpa/4 :");
                }

                else if(text.equals("International Board"))
                {
                    textInputLayout.setHint("Score /45:");
                }

                else if(text.equals("Indian Board"))
                {
                    textInputLayout.setHint("Percentage :");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        estimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estimate();
            }
        });


    }


    public void estimate() {
      try {
          text = myItems.getSelectedItem().toString();
          userInputValue = userInput.getText().toString().toUpperCase();

          //SPM
          if (text.equals("SPM or O-levels")) {
              if (userInputValue.equals("A")) {
                  estValue = "4.0";
              } else if (userInputValue.equals("B")) {
                  estValue = "3.0";
              } else if (userInputValue.equals("C")) {
                  estValue = "2.0";
              } else {
                  estValue = userInputValue;
              }

              if (myData[41].equals("A")) {
                  myData[41] = "4.0";
              }

              if (myData[41].equals("B")) {
                  myData[41] = "3.0";
              }
              if (myData[41].equals("C")) {
                  myData[41] = "2.0";
              }

                  Double estValComp = Double.parseDouble(estValue);
                  Double compare = Double.parseDouble(myData[41]);

                  if (estValComp - compare >= 0) {
                      sign = "Good chances";
                      compValue = 60 + ((estValComp - compare) / 4) * 40;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  } else if (estValComp - compare < 0) {
                      sign = "Low Chances";
                      compValue = 50 + ((estValComp - compare) / 4) * 50;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  }


          }

          if (text.equals("STPM or GCSE or A-Levels")) {
              Double estValComp = Double.parseDouble(userInputValue);
              Double compare = Double.parseDouble(myData[42]);

              if (estValComp - compare >= 0) {
                  sign = "Good chances";
                  compValue = 60 + ((estValComp - compare) / 4) * 40;
              } else if (estValComp - compare < 0) {
                  sign = "Low Chances";
                  compValue = 50 + ((estValComp - compare) / 4) * 50;
                  Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
              }

          }

          if (text.equals("AUSMAT")) {


                  Double estValComp = Double.parseDouble(userInputValue);
                  Double compare = Double.parseDouble(myData[43]);

                  if (estValComp - compare >= 0) {
                      sign = "Good chances";
                      compValue = 60 + ((estValComp - compare) / 100) * 40;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  } else if (estValComp - compare < 0) {
                      sign = "Low Chances";
                      compValue = 50 + ((estValComp - compare) / 100) * 50;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  }


          }


          if (text.equals("CPU")) {


                  Double estValComp = Double.parseDouble(userInputValue);
                  Double compare = Double.parseDouble(myData[44]);

                  if (estValComp - compare >= 0) {
                      sign = "Good chances";
                      compValue = 60 + ((estValComp - compare) / 100) * 40;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  } else if (estValComp - compare < 0) {
                      sign = "Low Chances";
                      compValue = 50 + ((estValComp - compare) / 100) * 50;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  }




          }


          if (text.equals("UEC")) {

                  Double estValComp = Double.parseDouble(userInputValue);
                  Double compare = Double.parseDouble(myData[45]);

                  if (estValComp - compare >= 0) {
                      sign = "Good chances";
                      compValue = 60 + ((estValComp - compare) / 4) * 40;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  } else if (estValComp - compare < 0) {
                      sign = "Low Chances";
                      compValue = 50 + ((estValComp - compare) / 4) * 50;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  }


          }

          if (text.equals("International Board")) {

                  Double estValComp = Double.parseDouble(userInputValue);
                  Double compare = Double.parseDouble(myData[46]);

                  if (estValComp - compare >= 0) {
                      sign = "Good chances";
                      compValue = 60 + ((estValComp - compare) / 45) * 40;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  } else if (estValComp - compare < 0) {
                      sign = "Low Chances";
                      compValue = 50 + ((estValComp - compare) / 45) * 50;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  }
              }





          if (text.equals("Indian Board")) {

                  Double estValComp = Double.parseDouble(userInputValue);
                  Double compare = Double.parseDouble(myData[47]);

                  if (estValComp - compare >= 0) {
                      sign = "Good chances";
                      compValue = 60 + ((estValComp - compare) / 100) * 40;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  } else if (estValComp - compare < 0) {
                      sign = "Low Chances";
                      compValue = 50 + ((estValComp - compare) / 100) * 50;
                      Toast.makeText(this, " " + compValue, Toast.LENGTH_LONG).show();
                  }



          }

          Intent intent = new Intent(getApplicationContext(), MalaysiaVarsityEstimate.class);
          String value = String.valueOf(compValue);
          intent.putExtra("estimate", value);
          intent.putExtra("allData", myData);
          intent.putExtra("text", text);
          intent.putExtra("sign", sign);
          intent.putExtra("userValue", userInputValue);
          startActivity(intent);

      }
      catch (NumberFormatException nfe)
      {
          Toast.makeText(getApplicationContext(),"Invalid Input ",Toast.LENGTH_LONG).show();
      }

    }
    }



