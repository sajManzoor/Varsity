package my.firstApp.sajid.versity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniByCoun extends AppCompatActivity {
    String attachPassed;
    String url;
    String jsonResult;
    ListView listView;
    private SearchView searchView;
    private ProgressDialog loading;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_by_coun);
        listView=(ListView)findViewById(R.id.listView2);
        searchView=(SearchView)findViewById(R.id.searchView5);
        TextView textView= (TextView) findViewById(R.id.textView3);
        Intent intent=getIntent();
        String code=intent.getStringExtra(Countries.uniByCoun);
        String[] result=code.split("\n");
        attachPassed=result[0];
        textView.setText("The following universities are in "+result[0]+" :");
        url="http://akrhcb.esy.es/uniByCoun.php?iso="+result[1];
        accessWebService();
        loading = ProgressDialog.show(this, "Loading Universities...", null, true, true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String code = ((TextView)view.findViewById(R.id.Name)).getText().toString();
                Intent intent = new Intent(getApplicationContext(),DisplayUniWebsite.class);
                intent.putExtra("code", code);
                startActivity(intent);



            }

        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                simpleAdapter.getFilter().filter(text.replace(" ", ""));
                return true;
            }
        });

    }

    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
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
                Toast.makeText(getApplicationContext(), "Error..." + e.toString(),
                        Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            ListDrawer();
            loading.dismiss();
        }
    }// end async task


    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
// passes values for the urls string array
        task.execute(new String[]{url});
    }

    // build hash set for list view
    public void ListDrawer() {
        List<Map<String, String>> studentList = new ArrayList<Map<String, String>>();
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("countries");
            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String number = jsonChildNode.optString("id");
                String name = jsonChildNode.optString("name");
                String emailId=jsonChildNode.optString("url");
                String outPut = name+"\n\n"+emailId.trim();
                studentList.add(createStudent("countries", outPut));
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
         simpleAdapter = new SimpleAdapter(this, studentList,
                R.layout.unis_by_countries,
                new String[]{"countries"}, new int[]{R.id.Name});
        listView.setAdapter(simpleAdapter);
    }

    private HashMap<String, String> createStudent(String name, String number) {
        HashMap<String, String> studentNameNo = new HashMap<String, String>();
        studentNameNo.put(name, number);
        return studentNameNo;


    }

}

