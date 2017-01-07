package my.firstApp.sajid.versity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectorMy extends AppCompatActivity {

    private String result;
    private String[] interim;
    private String selectedUniv;
    private CardView browseMy;
    private CardView mapMy;
    private CardView estimateMy;
    private String[] storageAll=new String[50];
    private String logo;
    private String link;
    private ImageView imgV3;
    private TextView tv34;
    private ProgressDialog loading;
    private TextView tv36;
    private TextView tv37;
    private TextView tv55;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_my);
        logo=getIntent().getStringExtra("logo");
        link=getIntent().getStringExtra("link");
        imgV3=(ImageView)findViewById(R.id.imageView3);
        result=getIntent().getStringExtra("jsonfile");
        tv34=(TextView)findViewById(R.id.textView34);
        tv36=(TextView)findViewById(R.id.textView36);
        tv37=(TextView)findViewById(R.id.textView37);
        tv55=(TextView)findViewById(R.id.textView55);
        selectedUniv = getIntent().getStringExtra("CODE");
        selectedUniv=selectedUniv.replaceAll("-"," ");
        estimateMy=(CardView)findViewById(R.id.estMyForm);

        estimateMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FilterActivity.innerUser==null)
                {
                    Toast.makeText(getApplicationContext(),"NO Guest User Privilage",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MalaysiaFormEstimate.class);
                    intent.putExtra("alldata", storageAll);
                    startActivity(intent);
                }
            }
        });

        interim=selectedUniv.split("\n");
        tv34.setText(interim[0]);
        tv34.setTypeface(EasyFonts.robotoBold(this));
        tv55.setText(interim[1]);
        tv34.setTypeface(EasyFonts.robotoLight(this));
        parseJson(result);
        tv36.setText("Website : ");
        tv37.setText(storageAll[37]);
        Linkify.addLinks(tv37, Linkify.ALL);
       // loading = ProgressDialog.show(this, "Loading Varsity Profile...", null, true, true);
        Picasso.with(getApplicationContext())
                .load(storageAll[38])
                .placeholder(R.drawable.loading3)
                .error(R.drawable.no_image_found)
                .resize(486,340)
                .into(imgV3);




        browseMy=(CardView)findViewById(R.id.browseMy);
        mapMy=(CardView)findViewById(R.id.mapMy);



        mapMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + interim[0] + "(Google+Malaysia)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });



        Toast.makeText(getApplicationContext()," "+ storageAll[0], Toast.LENGTH_LONG).show();
        browseMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyDetailedData.class);
                intent.putExtra("jsonfile", result);
                intent.putExtra("code", interim[0]);
                intent.putExtra("results", storageAll);

                startActivity(intent);
            }
        });



    }

    public String[] parseJson(String result)
    {
        try {
            JSONObject jsonResponse = new JSONObject(result);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Malaysia");
            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                JSONObject jsonObject = jsonChildNode.getJSONObject("University");

                if (jsonObject.optString("name").equals(interim[0])) {
                    storageAll[0]=jsonObject.optString("name");
                    storageAll[1]= jsonObject.optString("rank");
                    storageAll[2]= jsonObject.optString("course");
                    storageAll[3]= jsonObject.optString("fees");
                    JSONObject req=jsonChildNode.getJSONObject("Requirements");
                    storageAll[4]=req.optString("TOEFL");
                    storageAll[5]=req.optString("IELTS");
                    storageAll[6]=req.optString("SPM or O-Levels");
                    storageAll[7]=req.optString("STPM or GCSE or A-Levels");
                    storageAll[8]=req.optString("AUSMAT");
                    storageAll[9]=req.optString("CPU");
                    storageAll[10]=req.optString("UEC");
                    storageAll[11]=req.optString("IB");
                    storageAll[12]=req.optString("HKDSE");
                    storageAll[13]=req.optString("WAUPP");
                    storageAll[14]=req.optString("IB");
                    storageAll[15]=req.optString("HKDSE");
                    storageAll[16]=req.optString("WAUPP");
                    storageAll[17]=req.optString("Ontario Gr 12");
                    storageAll[18]=req.optString("Indian Board");
                    storageAll[19]=req.optString("Comments");
                    storageAll[20]=req.optString("Others");
                    JSONObject intakes=jsonChildNode.getJSONObject("Intakes");
                    storageAll[21]=intakes.optString("Month");
                    JSONObject facilities=jsonChildNode.getJSONObject("Facilities");
                    storageAll[22]=facilities.optString("Accomodation");
                    storageAll[23]=facilities.optString("Computer Facilities");
                    storageAll[24]=facilities.optString("Cafeteria");
                    storageAll[25]=facilities.optString("Sports Center");
                    storageAll[26]=facilities.optString("Health Center");
                    storageAll[27]=facilities.optString("Convenience Store");
                    storageAll[28]=facilities.optString("Library");
                    storageAll[29]=facilities.optString("Student Labs");
                    storageAll[30]=facilities.optString("Shuttle Services");
                    storageAll[31]=facilities.optString("Culinary Studio");
                    storageAll[32]=facilities.optString("Aircraft Simulator rooms");
                    storageAll[33]=facilities.optString("Research Labs");
                    storageAll[34]=facilities.optString("Music Studio");
                    storageAll[35]=facilities.optString("Mosque");
                    storageAll[36]=facilities.optString("Fashion Workshop");
                    storageAll[37]=jsonObject.optString("link");
                    storageAll[38]=jsonObject.optString("logo");

                    JSONObject results=jsonChildNode.getJSONObject("Results");
                    storageAll[39]=results.optString("TOEFL");
                    storageAll[40]=results.optString("IELTS");
                    storageAll[41]=results.optString("SPM or O-Levels");
                    storageAll[42]=results.optString("STPM or GCSE or A-Levels");
                    storageAll[43]=results.optString("AUSMAT");
                    storageAll[44]=results.optString("CPU");
                    storageAll[45]=results.optString("UEC");
                    storageAll[46]=results.optString("IB");
                    storageAll[47]=results.optString("Indian Board");
                }
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }

         return storageAll;
    }
}
