package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class UserProfileFragment extends Fragment {

    private TextView username;
    private TextView nationality;
    private TextView age;
    private TextView sex;
    private TextView gpa;
    private Button updateButton;
    private String url="http://akrhcb.esy.es/userDetails.php";
    private String jsonResult;
    private String passedUser;
    private String[] passData;
    private String u_n;
    private String nat;
    private String ageVal;
    private String gen;
    private String gpaScore;

    @Override
          public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        username=(TextView)rootView.findViewById(R.id.usertv);
        passData=new String[10];
        nationality=(TextView)rootView.findViewById(R.id.nationtv);
        age=(TextView)rootView.findViewById(R.id.agetv);
        sex=(TextView)rootView.findViewById(R.id.sextv);
        gpa=(TextView)rootView.findViewById(R.id.gpatv);
        passedUser=LoginActivity.userPassed;
        if(passedUser!="guest") {
            accessWebService();
        }
        else
        {
            username.setText("Guest user");
            nationality.setText("Not registered");
            age.setText("Not registered");
            sex.setText("Not registered");
            gpa.setText("Not registered");
        }
        updateButton=(Button)rootView.findViewById(R.id.buttonUpdate);
        username.setTypeface(EasyFonts.robotoBold(getActivity()));
        nationality.setTypeface(EasyFonts.robotoLight(getActivity()));
        age.setTypeface(EasyFonts.robotoLight(getActivity()));
        sex.setTypeface(EasyFonts.robotoLight(getActivity()));
        gpa.setTypeface(EasyFonts.robotoLight(getActivity()));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passedUser!="guest") {
                    Intent intent = new Intent(getActivity(), UpdateProfile.class);
                    intent.putExtra("values", passData);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(),"Guest Mode doesnt have this privilage ",Toast.LENGTH_LONG).show();
                }

            }

        });

        return rootView;

    }



    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                url="http://akrhcb.esy.es/userDetails.php?USER="+passedUser.replaceAll(" ","+");
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                InputStream response = con.getInputStream();
                jsonResult = inputStreamToString(response).toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
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
                Toast.makeText(getActivity(), "Error..." + e.toString(),
                        Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            ListDrawer();
        }
    }// end async task


    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
// passes values for the urls string array
        task.execute(new String[]{url});
    }

    // build hash set for list view
    public void ListDrawer() {
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("details");

                JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
                u_n = jsonChildNode.optString("user_name");
                nat = jsonChildNode.optString("nationality");
                ageVal=jsonChildNode.optString("age");
                gen=jsonChildNode.optString("sex");
                gpaScore=jsonChildNode.optString("gpa");
                username.setText(u_n);
                nationality.setText(nat);
                age.setText(ageVal);
                sex.setText(gen);
                gpa.setText(gpaScore);
            passData[0] = u_n;
            passData[1] = nat;
            passData[2] = ageVal;
            passData[3] = gen;
            passData[4] = gpaScore;


        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }



    }


}

