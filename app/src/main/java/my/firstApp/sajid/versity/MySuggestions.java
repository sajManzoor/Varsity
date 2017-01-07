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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
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

public class MySuggestions extends AppCompatActivity {

    private String estimate;
    private String[] allDataArray;
    private String userInput;
    private String examType;
    private String jsonResult;
    private SimpleAdapter simpleAdapter;
    private ListView listView;
    private String dataRank;
    private Integer compPrevRank;
    private int comRank=0;
    private Double userResultComp;
    private double resultComp = 0;
    private TextView textView;
    private InterstitialAd minterstitialAd;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_suggestions);
        requestNewInterstitial();



        textView=(TextView)findViewById(R.id.textView2);
        textView.setTypeface(EasyFonts.robotoBold(this));
        estimate=getIntent().getStringExtra("value");
        allDataArray=getIntent().getStringArrayExtra("allData");
        userInput=getIntent().getStringExtra("userInput");
        examType=getIntent().getStringExtra("examType");
        dataRank=allDataArray[1];

        if(examType.equals("International Board"))
        {
            examType="IB";
        }

        if(dataRank.equals("n/a"))
        {
            dataRank="25000";
        }
        compPrevRank=Integer.parseInt(dataRank);
        if(userInput.equals("A"))
        {
            userInput="4";
        }

        if(userInput.equals("B"))
        {
            userInput="3";
        }

        if(userInput.equals("C"))
        {
            userInput="2";
        }

        if(userInput.equals("D"))
        {
            userInput="1";
        }
        userResultComp=Double.parseDouble(userInput);

        listView=(ListView)findViewById(R.id.listView3);
        returnData();
        ListDrawer();

        if(simpleAdapter.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No Suggestions Found ",Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                if(rank.equals("n/a"))
                {
                    rank="25000";
                }

                try {
                    comRank=Integer.parseInt(rank);
                } catch(NumberFormatException nfe) {
                    // Handle parse error.
                    Toast.makeText(getApplicationContext(),rank,Toast.LENGTH_LONG);
                }

                JSONObject results =jsonChildNode.getJSONObject("Results");
                String result=results.optString(examType);

                if(!(result.equals("n/a"))&&comRank<compPrevRank&&!(result.equals("null"))&&!(result==null)) {

                    if(result.equals("A"))
                    {
                        result="4";
                    }

                    if(result.equals("B"))
                    {
                        result="3";
                    }

                    if(result.equals("C"))
                    {
                        result="2";
                    }

                    if(result.equals("D"))
                    {
                        result="1";
                    }


                    try {
                        resultComp = Double.parseDouble(result);
                    } catch (NumberFormatException nfe) {

                    }
                    if (resultComp-userResultComp<=0) {
                        String output = name + "\nRank : " + rank +
                        "\nRank Difference +"+(compPrevRank-comRank)+" places "+
                        "\nSimilar Requirements ";
                        UniList.add(createList("Malaysia", output));
                    }
                    if (resultComp-userResultComp>0) {
                        String output = name + "\nRank : " + rank +
                                "\nRank Difference +"+(compPrevRank-comRank)+" places "+
                                "\nHigher Requirements ";
                        UniList.add(createList("Malaysia", output));
                    }
                }

            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        simpleAdapter = new SimpleAdapter(this, UniList,
                R.layout.suggestion_list,
                new String[]{"Malaysia"}, new int[]{R.id.Name});
        listView.setAdapter(simpleAdapter);


    }

    private HashMap<String, String> createList(String c,String name) {
        HashMap<String,String> NameNo = new HashMap<String,String>();
        NameNo.put(c, name);
        return NameNo;


    }


    private void requestNewInterstitial()
    {
        minterstitialAd=new InterstitialAd(this);
        minterstitialAd.setAdUnitId("ca-app-pub-2857842856235200/9543928173");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        minterstitialAd.loadAd(adRequest);


        minterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("here", "ad ishere");
                if (minterstitialAd.isLoaded()) {
                    minterstitialAd.show();
                }

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                requestNewInterstitial();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                requestNewInterstitial();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                requestNewInterstitial();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                requestNewInterstitial();
            }


        });


    }



}
