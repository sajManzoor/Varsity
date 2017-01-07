package my.firstApp.sajid.versity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.vstechlab.easyfonts.EasyFonts;


public class UsDetailedData extends AppCompatActivity{

    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialAd2;
    private com.facebook.ads.InterstitialAd interstitialAd3;

    private CardView cd1;
    private CardView cd3;
    private TextView textView;
    private String attachPassed;
    private String attachPassed2;
    private  CardView cd2;
    private CardView cd5;
    private CardView cd4;
    public static String sendScores;
    public static String collegeSpecifics;
    public static String contact;
    public static String enrollment;
    public static String usAdmDetails;

    String[] results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_detailed_data);
        textView= (TextView) findViewById(R.id.t1);


        String code=getIntent().getStringExtra("CODE");
         results=getIntent().getStringArrayExtra("scores");
        final String[] result=code.split("\n");
        attachPassed=result[1];
        attachPassed2=result[0];
        textView.setText(attachPassed2);
        textView.setTypeface(EasyFonts.robotoBold(this));
        setSendScores();
        setCollegeSpecifics();
        setContactDetails();
        setEnrollmentDetails();
        setAdminDetails();
        cd1=(CardView)findViewById(R.id.my1);
        cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             UsAdminDialog usAdminDialog=new UsAdminDialog();
                usAdminDialog.show(getFragmentManager(),"Admission Details");
            }
        });
        cd2=(CardView)findViewById(R.id.my2);
        cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsEnrollDialog usEnrollDialog=new UsEnrollDialog();
                usEnrollDialog.show(getFragmentManager(),"Enrollment");
                loadInterstitialAd2();
            }
        });

        cd3=(CardView)findViewById(R.id.my3);
        cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsContactDialog usContactDialog=new UsContactDialog();
                usContactDialog.show(getFragmentManager(),"Contact");
                loadInterstitialAd();
            }
        });

        cd4=(CardView)findViewById(R.id.my4);
        cd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsSchools usSchools=new UsSchools();
                usSchools.show(getFragmentManager(), "College Specifics");


/*
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                loadInterstitialAd();

                            }
                        });
                    }
                }, 0, 9000);


*/


            }
        });


        cd5=(CardView)findViewById(R.id.cd5);
        cd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UsTestScores ts = new UsTestScores();
                ts.show(getFragmentManager(), "Test Scores");
                loadInterstitialAd3();


            }
        });


    }

    public void setSendScores()
    {

        for(int i=0;i<46;i++)
        {
            if(results[i].equals(null)||results[i].trim().equals(""))
            {
                results[i]="n/a";
            }

            if(results[i].equals("0"))
            {
                results[i]="No";
            }
        }
        sendScores="TOEFL : "+results[0]+"\nACT 25th Percentile : "+results[1] +"\nACT 25th Percentile English : " +results[2] + "\nACT 25th Percentile Math : " + results[3] + "\nACT 25th Percentile Writing : " + results[4] +
                 "\nACT 75th Percentile : " + results[5] + "\nACT 75th Percentile English : " + results[7] + "\nACT 75th Percentile Writing : " + results[9]+"\nACT 75th Percentile Math : "+results[8]+
                "\nSAT 25th Percentile : "+results[12]+ "\nSAT 25th Percentile Math :"+results[13]+"\nSAT 25th Percentile Reading : "+results[14]+"\nSAT 25th Percentile Writing : "+results[15]
                +"\nSAT 75th Percentile : "+results[16] +"\nSat 75th Percentile Math : "+results[17]+"\nSAT 75th Percentile Reading : "+results[18]
                +"\nSAT 75th Percentile Writing : "+results[19];
    }




    public void setCollegeSpecifics()
    {

        collegeSpecifics= "Four Year graduate rate : " + results[21] + "\nCalender System : " + results[20] + "\nOffers masters : " + results[22]+"\nOffers PhD : "
                +results[25] + "\nOutState Tutuion : " + results[23] + "\nStudent to Faculty Ratio : " + results[24] ;
    }


    public void setContactDetails()
    {

        contact="City : "+results[26] +"\nClassification : " +results[27]+ "\nOnline Application : " + results[28]+ "\nPhone : " + results[29]
                + "\nState : " +results[32]+ "\nStreet Address : " + results[30]+ "\nZip Code : " + results[31];
    }


    public void setEnrollmentDetails()
    {
        enrollment="Full time Graduates : "+results[33]+"\nFull time Total : "+results[34]+"\nFull time Undergraduate : "+results[35]
                +"\nTotal Graduates :  "+ results[36]+"\nTotal Undergraduates : "+results[37]+"\nRetention Rate : " + results[38];
    }


    public void setAdminDetails()
    {
        usAdmDetails="TOEFL : "+results[39]+"\nApplication Fee : "+results[40]+"\nPercent of Financial Aid : "+results[41] +"\nSecondary Gpa : "+results[42]+"\nSecondary School Record : "
                +results[43]+"\nTeacher Recommendations : "+results[44]+"\nTest Scores : "+results[45];


    }


    private void loadInterstitialAd() {
        interstitialAd = new InterstitialAd(this, "585035018343765_631452783701988");
        interstitialAd.setAdListener(new AbstractAdListener() {
            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                interstitialAd.show();
                Log.d("showing","showing ad1");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                super.onError(ad, adError);
                Log.d("showing error1", adError.getErrorMessage());
            }

            @Override
            public void onAdClicked(Ad ad) {
                super.onAdClicked(ad);
            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                super.onInterstitialDisplayed(ad);
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
                loadInterstitialAd();
            }
        });
        interstitialAd.loadAd();

    }

    private void loadInterstitialAd2() {
        interstitialAd2 = new InterstitialAd(this, "585035018343765_655180857995847");
        interstitialAd2.setAdListener(new AbstractAdListener() {
            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                interstitialAd2.show();
                Log.d("showing", "showing ad2");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                super.onError(ad, adError);
                Log.d("showing error2", adError.getErrorMessage());
            }

            @Override
            public void onAdClicked(Ad ad) {
                super.onAdClicked(ad);
            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                super.onInterstitialDisplayed(ad);
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
                loadInterstitialAd2();
            }
        });
        interstitialAd2.loadAd();
    }



    private void loadInterstitialAd3() {
        interstitialAd3 = new com.facebook.ads.InterstitialAd(this, "585035018343765_655246427989290");
        interstitialAd3.setAdListener(new AbstractAdListener() {
            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                interstitialAd3.show();
                Log.d("showing", "showing ad3");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                super.onError(ad, adError);
                Log.d("showing error3", adError.getErrorMessage());
            }

            @Override
            public void onAdClicked(Ad ad) {
                super.onAdClicked(ad);
            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                super.onInterstitialDisplayed(ad);
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
                loadInterstitialAd3();
            }
        });
        interstitialAd3.loadAd();
    }



}






