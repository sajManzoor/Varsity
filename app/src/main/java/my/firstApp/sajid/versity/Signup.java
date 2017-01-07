package my.firstApp.sajid.versity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private static final String accessMode="advanced";
    private RequestQueue requestQueue;
    private StringRequest request;
    private final String URL="http://akrhcb.esy.es/insertDetails.php";
    private EditText user_name;
    private EditText password;
    private EditText nationality;
    private EditText age;
    private EditText sex;
    private EditText gpa;
    private Button submit;
    private ProgressDialog loading;
    private Spinner gender;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        user_name= (EditText) findViewById(R.id.user_name);
        password= (EditText) findViewById(R.id.password);
        nationality= (EditText) findViewById(R.id.nationality);
        age=(EditText)findViewById(R.id.age);
        gpa=(EditText)findViewById(R.id.gpa);
        submit=(Button)findViewById(R.id.submit);
        requestQueue= Volley.newRequestQueue(this);
        gender = (Spinner) findViewById(R.id.spinner);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2857842856235200/2359180174");


        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);

        if(mInterstitialAd.isLoaded())
        mInterstitialAd.show();

        List<String> spinnerArray = new ArrayList<String>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerArray.add("Male");
        spinnerArray.add("Female");

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             registerUser(v);

            }

    });
        }

    public void registerUser(View v)
    {

        request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                if(response.equals("successfully registered"))
                {
                    loading.dismiss();
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    loading.dismiss();
                }

            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error)
            {
                loading.dismiss();
            }
        }) {
            protected Map<String,String> getParams() throws AuthFailureError{
                HashMap<String,String> hashMap=new HashMap<String, String>();
                hashMap.put("user",user_name.getText().toString());
                hashMap.put("pass",password.getText().toString());
                hashMap.put("nationality", nationality.getText().toString());
                hashMap.put("age",age.getText().toString());
                hashMap.put("sex", gender.getSelectedItem().toString());
                hashMap.put("gpa",gpa.getText().toString());

                return hashMap;

            }
        };

        requestQueue.add(request);
        loading = ProgressDialog.show(this, "Please Wait...", null, true, true);

    }




    }



