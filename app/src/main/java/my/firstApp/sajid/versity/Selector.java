package my.firstApp.sajid.versity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Selector extends AppCompatActivity {


    private CardView cardView;
    private String url="https://nearbycolleges.info/api/everything/";
    private String jsonResult;
    private CardView browse;
    private SelectableRoundedImageView imageView;
    private String code;
    private String[] result;
    private String uid;
    private String ln;
    private String website;
    private String lg;
    private CardView buttonMap;
    private String name;
    private CardView estimate;
    private TextView tv;
    private String state;
    private TextView tv37;
    private TextView tv36;
    private String toeflReq;
    private String[] usTestData;
    private ProgressDialog loading;
    private TextView tv55;
    private String img;
    private boolean searchIt=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        tv=(TextView)findViewById(R.id.textView34);

        tv37=(TextView)findViewById(R.id.textView37);
        tv36=(TextView)findViewById(R.id.textView36);
        usTestData=new String[50];

        code = getIntent().getStringExtra("CODE");
        estimate = (CardView) findViewById(R.id.estimate);
        browse = (CardView) findViewById(R.id.browse);

        tv55=(TextView)findViewById(R.id.textView55);
        result = code.split("\n");
        name = result[0];
        uid = result[1];
        imageView = (SelectableRoundedImageView) findViewById(R.id.imageView3);

        tv.setTypeface(EasyFonts.robotoBold(this));
        accessWebService();
        loading = ProgressDialog.show(this, "Loading Varsity Profile...", null, true, true);





        estimate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if (FilterActivity.innerUser == null) {
                                                Toast.makeText(getApplicationContext(), "No Guest User Privilage", Toast.LENGTH_LONG).show();
                                            } else {

                                                if(searchIt) {
                                                    Intent intent = new Intent(getApplicationContext(), EstimatorUsForm.class);
                                                    intent.putExtra("Image", img);
                                                    intent.putExtra("code", name);
                                                    intent.putExtra("id",uid);
                                                    intent.putExtra("scores", usTestData);
                                                    startActivity(intent);
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    }
        );
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchIt) {
                    Intent intent = new Intent(getApplicationContext(), UsDetailedData.class);
                    intent.putExtra("CODE", code);
                    intent.putExtra("scores", usTestData);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonMap = (CardView) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:" + ln + "," + lg + "?q=" + result[0]);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

    cardView = (CardView) findViewById(R.id.uniProf);
        cardView.setPreventCornerOverlap(false);

    }



    private class JsonReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                url="https://nearbycolleges.info/api/everything/"+uid;
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                InputStream response = con.getInputStream();
                jsonResult = inputStreamToString(response).toString();
            } catch (MalformedURLException e) {
                Toast.makeText(getApplicationContext(),"No Information available for the selected university at the moment .",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"No Information available for the selected university at the moment .",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), "Error..." + e.toString(),
                        Toast.LENGTH_LONG).show();
            }
            return answer;
        }


        @Override
        protected void onPostExecute(String result) {
            Display();
         loading.dismiss();

        }
    }// end async task


    public void accessWebService() {

        JsonReadTask task = new JsonReadTask();
// passes values for the urls string array
        task.execute(new String[]{url});
    }
    // build hash set for list view
    public void Display() {

        try {
            JSONObject jsonString = new JSONObject(jsonResult);
            JSONObject jsonResponse=jsonString.getJSONObject("result");
            JSONObject jsSecObj=jsonResponse.getJSONObject("school");

             img = jsSecObj.optString("img");
            Picasso.with(getApplicationContext())
                    .load(img)
                    .placeholder(R.drawable.loading3)
                    .error(R.drawable.no_image_found)
                    .resize(486,340)
                    .into(imageView);



            JSONObject jsThirdObj=jsonResponse.getJSONObject("location");
            ln=jsThirdObj.optString("lat");
            lg=jsThirdObj.optString("lng");
            website=jsThirdObj.optString("website");
            state=jsThirdObj.optString("state");
            JSONObject jsonAdminReq=jsonResponse.getJSONObject("admission");
            toeflReq=jsonAdminReq.optString("TOEFL");
            JSONObject jsonResponse2=jsonResponse.getJSONObject("test");
            String act25 = jsonResponse2.optString("act25");

            String act25Eng = jsonResponse2.optString("act25Eng");
            String act25Math = jsonResponse2.optString("act25Math");
            String act25Writing = jsonResponse2.optString("act25Writing");
            String act75 = jsonResponse2.optString("act75");
            String act75English = jsonResponse2.optString("act75English");
            String act75Math = jsonResponse2.optString("act75Math");
            String act75writing= jsonResponse2.optString("act75writing");
            String percentusedACT = jsonResponse2.optString("percentusedACT");
            String percentusedSAT=jsonResponse2.optString("percentusedSAT");
            String sat25=jsonResponse2.optString("saat25");
            String sat25Math=jsonResponse2.optString("sat25Math");
            String sat25Reading=jsonResponse2.optString("sat25Reading");
            String sat25Writing=jsonResponse2.optString("sat25Writing");
            String sat75=jsonResponse2.optString("saat75");
            String sat75Math=jsonResponse2.optString("sat75Math");
            String sat75Reading=jsonResponse2.optString("sat75Reading");
            String sat75Writing=jsonResponse2.optString("sat75Writing");

            JSONObject school=jsonResponse.getJSONObject("school");

            String csys = school.optString("calenderSystem");
            String fourYearGradRate = school.optString("fourYearGradRate");
            String offMasters = school.optString("offersMasters");
            String outstateTution = school.optString("outstateTuition");
            String sfr = school.optString("studentFacultyRatio");
            String offPhd=school.optString("offersPhD");

            JSONObject location=jsonResponse.getJSONObject("location");

            String aw = location.optString("city");
            String classif = location.optString("classification");
            String oA = location.optString("onlineApplication");
            String pn = location.optString("phone");
            String sad = location.optString("streetAddress");
            String zipCode = location.optString("zipCode");

            JSONObject enrollment=jsonResponse.getJSONObject("enrollment");

            String fullg = enrollment.optString("fulltimeGrad");
            String fullt = enrollment.optString("fulltimeTotal");
            String fullU=enrollment.optString("fulltimeUndergrad");
            String gradT=enrollment.optString("gradTotal");
            String ugTotal=enrollment.optString("undergradTotal");
            String retRate=enrollment.optString("retentionRate");

            JSONObject admission=jsonResponse.getJSONObject("admission");

            String toefl =admission.optString("TOEFL");
            String appFee=admission.optString("applicationFee");
            String pfa=admission.optString("percentOnFA");
            String secGpa=admission.optString("secondaryGPA");
            String secScRec=admission.optString("secondarySchoolRecord");
            String trs=admission.optString("teacherRecommendations");
            String testScores=admission.optString("testScores");

            if (csys.equals("0")||csys.trim().equals(""))
                csys = "na";
            if(fourYearGradRate.equals("0"))
                fourYearGradRate = "na";
            if(offMasters.equals("0"))
                offMasters = "na";
            if(outstateTution.equals("0"))
                outstateTution = "na";
            if(sfr.equals("0"))
                sfr = "na";


            usTestData[0]=toeflReq;
            usTestData[1]=act25;
            usTestData[2]=act25Eng;
            usTestData[3]=act25Math;
            usTestData[4]=act25Writing;
            usTestData[5]=act75;
            usTestData[6]=act75English;
            usTestData[7]=act75English;
            usTestData[8]=act75Math;
            usTestData[9]=act75writing;
            usTestData[10]=percentusedACT;
            usTestData[11]=percentusedSAT;
            usTestData[12]=sat25;
            usTestData[13]=sat25Math;
            usTestData[14]=sat25Reading;
            usTestData[15]=sat25Writing;
            usTestData[16]=sat75;
            usTestData[17]=sat75Math;
            usTestData[18]=sat75Reading;
            usTestData[19]=sat75Writing;
            usTestData[20]=csys;
            usTestData[21]=fourYearGradRate;
            usTestData[22]=offMasters;
            usTestData[23]=outstateTution;
            usTestData[24]=sfr;
            usTestData[25]=offPhd;
            usTestData[26]=aw;
            usTestData[27]=classif;
            usTestData[28]=oA;
            usTestData[29]=pn;
            usTestData[30]=sad;
            usTestData[31]=zipCode;
            usTestData[32]=state;
            usTestData[33]=fullg;
            usTestData[34]=fullt;
            usTestData[35]=fullU;
            usTestData[36]=gradT;
            usTestData[37]=ugTotal;
            usTestData[38]=retRate;
            usTestData[39]=toefl;
            usTestData[40]=appFee;
            usTestData[41]=pfa;
            usTestData[42]=secGpa;
            usTestData[43]=secScRec;
            usTestData[44]=trs;
            usTestData[45]=testScores;

            for(int i=0;i<usTestData.length;i++)
            {
                if(usTestData[i].equals("null"))
                {
                    searchIt=false;
                }
                else
                {
                    searchIt=true;
                    break;
                }
            }


            tv.setText(result[0]);
            tv55.setText(state);
            tv55.setTypeface(EasyFonts.robotoLight(this));
            tv37.setText(website);
            tv36.setTypeface(EasyFonts.robotoLight(this));
            Linkify.addLinks(tv37,Linkify.ALL);
        }

        catch (JSONException e) {

            Toast.makeText(getApplicationContext(),"No Image Found",
                    Toast.LENGTH_SHORT).show();
        }



    }



    }


