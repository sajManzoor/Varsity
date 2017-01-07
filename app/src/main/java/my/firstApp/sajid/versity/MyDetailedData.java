package my.firstApp.sajid.versity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.vstechlab.easyfonts.EasyFonts;

public class MyDetailedData extends AppCompatActivity {

    private InterstitialAd interstitialAd;

    private String result;
    private String selectedUniv;
    private TextView uniName;
    private CardView my1;
    private CardView my2;
    private CardView my3;
    private CardView my4;
    public static String requirements;
    public static String facilities;
    private String[] storage;
    public static String intakes;
    private String[] intakesAll;
    private String intakesTemp;
    public static String uniSpec;
    private InterstitialAd minterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detailed_data);
        selectedUniv=getIntent().getStringExtra("code");
        result=getIntent().getStringExtra("result");
        uniName=(TextView)findViewById(R.id.m1);
        result=getIntent().getStringExtra("jsonfile");
        selectedUniv=getIntent().getStringExtra("code");
        storage=getIntent().getStringArrayExtra("results");
        my2=(CardView)findViewById(R.id.mycv2);
        my1=(CardView)findViewById(R.id.mycv1);
        my3=(CardView)findViewById(R.id.mycv3);
        my4=(CardView)findViewById(R.id.mycv4);
        sendUniSpecMy();
        sendRequirements();
        sendIntakes();
        sendFacilities();
        uniName.setText(selectedUniv + " : ");
        uniName.setTypeface(EasyFonts.robotoThin(this));

        minterstitialAd=new InterstitialAd(this);
        minterstitialAd.setAdUnitId("ca-app-pub-2857842856235200/9543928173");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        minterstitialAd.loadAd(adRequest);

        if(minterstitialAd.isLoaded())
        {
            minterstitialAd.show();
        }


        my1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minterstitialAd.isLoaded())
                {
                    minterstitialAd.show();
                }

                giveMeUnivSpecs();
            }
        });

        my2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minterstitialAd.isLoaded())
                {
                    minterstitialAd.show();
                }


                giveMeRequirements();

            }
        });

        my3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(minterstitialAd.isLoaded())
                {
                    minterstitialAd.show();
                }
                giveMeIntakes();

            }
        });


        my4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minterstitialAd=new InterstitialAd(getApplicationContext());
                minterstitialAd.setAdUnitId("ca-app-pub-2857842856235200/9543928173");
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();

                minterstitialAd.loadAd(adRequest);
                giveFacilities();

            }
        });


    }





    public void sendRequirements()
    {

        for(int i=4;i<20;i++)
        {
            if(storage[i].equals("")||storage[i]==null)
            {
                storage[i]="not req";
            }
        }

        requirements="\nTOEFL : "+storage[4]+"\nIELTS : "+ storage[5]+"\nSPM or O-Levels : "+storage[6]+"\nSTPM or GCSE or A-Levels : "+storage[7]
                    +"\nAUSMAT : "+storage[8]+"\nCPU : "+storage[9]+"\nUEC : "+storage[10]+"\nIB : "+storage[11]+"\nHKDSE : "+storage[12]+"\nWAUPP : "+
                       storage[13]+"\nWAUPP : "+storage[16]+"\nOntario Gr 12 : "+storage[17]+"\nIndian Board : "+ storage[18]+"\nComments : "+storage[19]+"\nOthers : "+storage[20];


    }


    public void sendFacilities()
    {

        for(int i=22;i<37;i++)
        {
            if(storage[i].equals("")||storage[i]==null)
            {
                storage[i]="-";
            }
        }
        facilities="\nAccomodation : "+storage[22]+"\nComputer Facilities : "+storage[23]+"\nCafeteria : "+storage[24]+"\nSports Center : "+storage[25]
                    +"\nHealth Center : "+storage[36]+"\nConvenience Store : "+storage[27]+"\nLibrary : "+storage[28]+"\nStudent Labs : "+storage[29]+
                     "\nShuttle Services : "+storage[30]+"\nCulinary Studio : "+storage[31]+"\nAircraft Simulator rooms"+storage[32]+"\nResearch Labs : "
                    +storage[33]+"\nMusic Studio : "+storage[34]+"\nMosque : "+storage[35]+"\nFashion Workshop : "+storage[36];

    }

    public void sendIntakes()
    {
        intakesTemp=storage[21];
        intakesAll=intakesTemp.split(",");
        intakes="\nMonth :";
        for(int i=0;i<intakesAll.length;i++)
        {
            intakes+=intakesAll[i]+"\n             ";
        }

    }

    public void sendUniSpecMy()
    {
        uniSpec="\nName :"+storage[0] + "\nRank :"+storage[1]+"\nCourses : "+storage[2]+"\nFees :"+storage[3];
    }



    public void giveMeRequirements()
    {
        ReqMy reqmy=new ReqMy();
        reqmy.show(getFragmentManager(), "Requirements");
    }

    public void giveMeIntakes()
    {


        IntakesMy intakesMy=new IntakesMy();
        intakesMy.show(getFragmentManager(), "Intakes");
    }

    public void giveMeUnivSpecs()
    {

        UnivSpecMy uniSpecMy=new UnivSpecMy();
        uniSpecMy.show(getFragmentManager(), "Details");
    }

    public void giveFacilities()
    {

        FacMy facMy=new FacMy();
        facMy.show(getFragmentManager(), "Facilities");
    }
}
