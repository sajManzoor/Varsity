package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONDataMY extends AppCompatActivity{

    private com.facebook.ads.InterstitialAd interstitialAd;

    private String jsonResult=null;
    private ListView Data;
    private SearchView searchView;
    private SimpleAdapter simpleAdapter;
    private EditText editText;
    private String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsondata_my);
        Data = (ListView) findViewById(R.id.ranks);
        returnData();
        ListDrawer();
        searchView = (SearchView) findViewById(R.id.searchView1);

        loadInterstitialAd();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
             //   Data.setFilterText(text);
                simpleAdapter.getFilter().filter(text.replace(" ",""));
                return true;
            }
        });


            Data.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    String code = ((TextView) view.findViewById(R.id.Name)).getText().toString();
                    Intent intent = new Intent(getApplicationContext(), SelectorMy.class);
                    intent.putExtra("CODE", code);
                    intent.putExtra("jsonfile", jsonResult);
                    startActivity(intent);


                }

            });
    }


    public String returnData()
    {
        try {
            InputStream is = getAssets().open("Mydetailed.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonResult = new String(buffer, "UTF-8");

     } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonResult;

    }

    // build hash set for list view
    public void ListDrawer() {
        List<Map<String, String>> UniList = new ArrayList<Map<String,String>>();
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Malaysia");
            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                JSONObject jsonObject =jsonChildNode.getJSONObject("University");
                String name = jsonObject.optString("name");
                String rank=jsonObject.optString("rank");
                String output=name.replaceAll(" ","-")+"\nRank : "+rank;
                UniList.add(createList("Malaysia",output));
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
         simpleAdapter = new SimpleAdapter(this, UniList,
                R.layout.malaysianlist,
                new String[]{"Malaysia"}, new int[]{R.id.Name});
       Data.setAdapter(simpleAdapter);


    }

    private HashMap<String, String> createList(String c,String name) {
        HashMap<String,String> NameNo = new HashMap<String,String>();
        NameNo.put(c, name);
        return NameNo;


    }


    private void loadInterstitialAd() {
        interstitialAd = new InterstitialAd(this, "585035018343765_631452783701988");
        interstitialAd.setAdListener(new AbstractAdListener() {
            public void onAdLoaded(Ad ad) {

                interstitialAd.show();
            }

            public void onError(Ad ad, AdError error) {


                Log.d("hey","eroooooooor"+error.getErrorMessage());
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Use this function to resume your app's flow
            }
        });

        interstitialAd.loadAd();
    }
}

