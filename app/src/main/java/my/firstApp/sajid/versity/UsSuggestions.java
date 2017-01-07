package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsSuggestions extends AppCompatActivity implements InterstitialAdListener {


    private InterstitialAd interstitialAd;
    private String jsonResultUs;
    private SimpleAdapter simpleAdapter;
    private ListView listView;
    private String userScore;
    private String text;
    private String sign;
    private Double userTotal;
    private int counterL=0;
    private int counterH=0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_suggestions);
        loadInterstitialAd();
        listView=(ListView)findViewById(R.id.listView25);
        textView=(TextView)findViewById(R.id.textView6);
        textView.setTypeface(EasyFonts.robotoBold(this));
        userScore=getIntent().getStringExtra("userScore");
        text=getIntent().getStringExtra("text");
        sign=getIntent().getStringExtra("sign");
        userTotal=Double.parseDouble(userScore);


        returnData();
        ListDrawer();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String code = ((TextView) view.findViewById(R.id.Name)).getText().toString();
                String[] sendValue=code.split("\n");
                String value=sendValue[0]+"\n"+sendValue[3];
                Intent intent = new Intent(getApplicationContext(), Selector.class);
                intent.putExtra("CODE",value);
                startActivity(intent);


            }

        });
    }


    public String returnData()
    {
        try {
            InputStream is = getAssets().open("UsSuggestions.json");
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
            for (int i = 1; i <jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                if(text.equals("SAT")) {
                    if(sign.equals("25-75%")||sign.equals("<25%"))
                    {
                    String name = jsonChildNode.optString("FIELD1");
                    String rank = jsonChildNode.optString("FIELD2");
                        String sat251 = jsonChildNode.optString("FIELD3");
                        String sat252 = jsonChildNode.optString("FIELD5");
                        String sat253 = jsonChildNode.optString("FIELD7");
                        if(!(sat251.trim().equals("")||sat252.trim().equals("")||sat253.trim().equals(""))) {
                            try{
                                Double s251=Double.parseDouble(sat251);

                                Double s252=Double.parseDouble(sat252);
                                Double s253=Double.parseDouble(sat253);
                            if((userTotal-((s251+s252+s253))/3>0)&counterL<=10) {
                                String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Similar or lower Requirements than the user's score\n"+name;
                                List.add(createList("US", outPut));
                                counterL++;

                            }


                                if((userTotal-((s251+s252+s253))/3<0)&&counterH<=10) {
                                    String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Higher Requirements than the user's score\n"+name;
                                    List.add(createList("US", outPut));
                                    counterH++;

                                }

                        }catch (NumberFormatException nfe)
                            {
                                Toast.makeText(getApplicationContext(),"Data Formatting Error"+sat251+sat252+sat253,Toast.LENGTH_SHORT).show();
                            }
                     //   }
                  }

                    }

                    if(sign.equals(">75%"))
                    {
                        String name = jsonChildNode.optString("FIELD1");
                        String rank = jsonChildNode.optString("FIELD2");
                        String sat251 = jsonChildNode.optString("FIELD4");
                        String sat252 = jsonChildNode.optString("FIELD6");
                        String sat253 = jsonChildNode.optString("FIELD8");
                        if(!(sat251.trim().equals("")||sat252.trim().equals("")||sat253.trim().equals(""))) {
                            try{
                                Double s251=Double.parseDouble(sat251);
                                Double s252=Double.parseDouble(sat252);
                                Double s253=Double.parseDouble(sat253);

                                if((userTotal-((s251+s252+s253))/3>0)&counterL<=10) {
                                    String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Similar or Lower Requirements than the user's score\n"+name;
                                    List.add(createList("US", outPut));
                                    counterL++;

                                }
                                    if((userTotal-((s251+s252+s253))/3<0)&&counterH<=10) {
                                        String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Higher Requirements than the user's score\n"+name;
                                        List.add(createList("US", outPut));
                                        counterH++;
                                    }

                            }catch (NumberFormatException nfe)
                            {
                                Toast.makeText(getApplicationContext(),"Data formatting Error"+sat251+sat252+sat253,Toast.LENGTH_SHORT).show();
                            }
                            //   }
                        }

                    }



                }

                if(text.equals("ACT")) {
                    if(sign.equals("25-75%")||sign.equals("<25%"))
                    {
                        String name = jsonChildNode.optString("FIELD1");
                        String rank = jsonChildNode.optString("FIELD2");
                        String sat251 = jsonChildNode.optString("FIELD9");
                        String sat252 = jsonChildNode.optString("FIELD11");
                        String sat253 = jsonChildNode.optString("FIELD13");
                        if(!(sat251.trim().equals("")||sat252.trim().equals("")||sat253.trim().equals(""))) {
                            try{
                                Double s251=Double.parseDouble(sat251);

                                Double s252=Double.parseDouble(sat252);
                                Double s253=Double.parseDouble(sat253);
                                if((userTotal-((s251+s252+s253))/3>0)&counterL<=10) {
                                    String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Similar or lower Requirements than the user's score.\n"+name;
                                    List.add(createList("US", outPut));
                                    counterL++;

                                }


                                if((userTotal-((s251+s252+s253))/3<0)&&counterH<=10) {
                                    String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Higher Requirements than the user's score"+"\n"+name;
                                    List.add(createList("US", outPut));
                                    counterH++;

                                }

                            }catch (NumberFormatException nfe)
                            {
                                Toast.makeText(getApplicationContext(),"Data Formatting Error"+sat251+sat252+sat253,Toast.LENGTH_SHORT).show();
                            }
                            //   }
                        }

                    }

                    if(sign.equals(">75%"))
                    {
                        String name = jsonChildNode.optString("FIELD1");
                        String rank = jsonChildNode.optString("FIELD2");
                        String sat251 = jsonChildNode.optString("FIELD14");
                        String sat252 = jsonChildNode.optString("FIELD12");
                        String sat253 = jsonChildNode.optString("FIELD10");
                        if(!(sat251.trim().equals("")||sat252.trim().equals("")||sat253.trim().equals(""))) {
                            try{
                                Double s251=Double.parseDouble(sat251);
                                Double s252=Double.parseDouble(sat252);
                                Double s253=Double.parseDouble(sat253);

                                if((userTotal-((s251+s252+s253))/3>0)&counterL<=10) {
                                    String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Similar or lower Requirements than the user's score\n"+name;
                                    List.add(createList("US", outPut));
                                    counterL++;

                                }
                                if((userTotal-((s251+s252+s253))/3<0)&&counterH<=10) {
                                    String outPut = rank.replaceAll(" ", "-") +"\n\nSame Test Type with Higher Requirements than the user's score.\n"+name;
                                    List.add(createList("US", outPut));
                                    counterH++;
                                }

                            }catch (NumberFormatException nfe)
                            {
                                Toast.makeText(getApplicationContext(),"Data Formatting Error",Toast.LENGTH_SHORT).show();
                            }
                            //   }
                        }

                    }



                }

            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        simpleAdapter = new SimpleAdapter(this, List,
                R.layout.suggestionus,
                new String[]{"US"}, new int[]{R.id.Name});
        listView.setAdapter(simpleAdapter);


    }

    private HashMap<String,String> createList(String name,String number) {
        HashMap<String, String> NameNo = new HashMap<String,String>();
        NameNo.put(name, number);
        return NameNo;


    }

    private void loadInterstitialAd() {
        interstitialAd = new InterstitialAd(this, "585035018343765_631452783701988");
        interstitialAd.setAdListener(this);
        interstitialAd.loadAd();
    }

    @Override
    public void onError(Ad ad, AdError error) {
        // Ad failed to load
        Log.d("fb error", "Error loading ad " + error.getErrorMessage());
    }

    @Override
    public void onAdLoaded(Ad ad) {
        // Ad is loaded and ready to be displayed
        // You can now display the full screen add using this code:
        interstitialAd.show();
    }

    @Override
    public void onAdClicked(Ad ad) {

    }


    @Override
    public void onInterstitialDisplayed(Ad ad) {

    }

    @Override
    public void onInterstitialDismissed(Ad ad) {
        loadInterstitialAd();

    }
}
