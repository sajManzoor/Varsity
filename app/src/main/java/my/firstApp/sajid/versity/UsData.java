package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//This class is to get the data for the us universities.
public class UsData extends AppCompatActivity {

    private InterstitialAd interstitialAd;

    private String jsonResultUs;
    private ListView listView;
    private SearchView searchView;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_data);
        listView = (ListView) findViewById(R.id.lvUs);
        returnData();
        ListDrawer();
        searchView = (SearchView) findViewById(R.id.searchView2);

        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-2857842856235200/7290295771");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);

        if(interstitialAd.isLoaded())
        {
            interstitialAd.show();
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(interstitialAd.isLoaded())
                {
                    interstitialAd.show();
                }

                String code = ((TextView) view.findViewById(R.id.Name)).getText().toString();
                Intent intent = new Intent(getApplicationContext(), Selector.class);
                intent.putExtra("CODE", code);
                startActivity(intent);

            }

        });

    }
    public String returnData()
    {
        try {
            InputStream is = getAssets().open("keyUs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonResultUs = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonResultUs;

    }

    public void ListDrawer() {
        List<Map<String, String>> List = new ArrayList<Map<String, String>>();
        try {
            JSONObject jsonResponse = new JSONObject(jsonResultUs);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("US");
            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String name = jsonChildNode.optString("FIELD1");
                String rank=jsonChildNode.optString("FIELD2");
                String outPut = rank.replaceAll(" ","-")+"\n"+name;
                List.add(createList("US", outPut));
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        simpleAdapter = new SimpleAdapter(this, List,
                R.layout.uslist,
                new String[]{"US"}, new int[]{R.id.Name});
        listView.setAdapter(simpleAdapter);


    }

    private HashMap<String,String> createList(String name,String number) {
        HashMap<String, String> NameNo = new HashMap<String,String>();
        NameNo.put(name, number);
        return NameNo;


    }
}
