package my.firstApp.sajid.versity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UpdateProfile extends AppCompatActivity {

    private String[] getData;
    private String url="http://akrhcb.esy.es/updateUser.php?USER=";
    private String jsonResult;
    private EditText gpa, nation, name, age;
    private Spinner gender;
    private Button update;
    private String sendGpa,sendNation,sendName,sendAge,sendSex;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getData = getIntent().getStringArrayExtra("values");
        gpa = (EditText) findViewById(R.id.gpaEd);
        nation = (EditText) findViewById(R.id.nationEd);
        name = (EditText) findViewById(R.id.nameEd);
        age = (EditText) findViewById(R.id.ageEd);
        gender = (Spinner) findViewById(R.id.spinnerEd);

        List<String> spinnerArray = new ArrayList<String>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerArray.add("Male");
        spinnerArray.add("Female");

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        name.setText(getData[0]);
        nation.setText(getData[1]);
        age.setText(getData[2]);
        gpa.setText(getData[4]);
        update = (Button) findViewById(R.id.buttonUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateIt();
            }
        });
    }


        public void updateIt()
    {

        sendGpa=gpa.getText().toString();
        sendAge=age.getText().toString();
        sendSex=gender.getSelectedItem().toString();
        sendName=name.getText().toString();
        sendNation=nation.getText().toString();
        accessWebService();
        loading = ProgressDialog.show(this, "Please Wait...", null, true, true);
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }




    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                url = "http://akrhcb.esy.es/updateUser.php?USER="+sendName+"&nation="+sendNation+"&age="+sendAge+"&sex="+sendSex+"&gpa="+sendGpa;
                    URL ulrn = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                    InputStream response = con.getInputStream();
                    jsonResult = inputStreamToString(response).toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                Toast.makeText(getApplicationContext(),"User name taken..",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
// e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error..." + e.toString(),
                        Toast.LENGTH_LONG).show();
            }
            return answer;
        }


        @Override
        protected void onPostExecute(String result) {
            loading.dismiss();
        }
    }// end async task


    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
// passes values for the urls string array
        task.execute(new String[]{url});
    }


    }



