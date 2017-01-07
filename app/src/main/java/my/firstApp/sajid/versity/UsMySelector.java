package my.firstApp.sajid.versity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class UsMySelector extends Fragment {

View root;
    CardView c1;
    CardView c2;
    private InterstitialAd minterstitialAd;
    private com.facebook.ads.InterstitialAd interstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        minterstitialAd=new InterstitialAd(getActivity());
        minterstitialAd.setAdUnitId("ca-app-pub-2857842856235200/7290295771");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        minterstitialAd.loadAd(adRequest);

        if(minterstitialAd.isLoaded())
        {
            minterstitialAd.show();
        }

        loadInterstitialAd();




        root=inflater.inflate(R.layout.fragment_us_my_selector, container, false);
        c1=(CardView)root.findViewById(R.id.my1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minterstitialAd.isLoaded())
                {
                    minterstitialAd.show();
                }
                Intent intent= new Intent(getContext(),JSONDataMY.class);
                startActivity(intent);
            }
        });
        c1.setPreventCornerOverlap(false);

        c2=(CardView)root.findViewById(R.id.my2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minterstitialAd.isLoaded())
                {
                    minterstitialAd.show();
                }
                Intent intent= new Intent(getContext(),UsData.class);
                startActivity(intent);
            }
        });
        c1.setPreventCornerOverlap(false);
        return root;
    }

    private void loadInterstitialAd() {
        interstitialAd = new com.facebook.ads.InterstitialAd(getActivity(), "585035018343765_631452783701988");
        interstitialAd.setAdListener(new AbstractAdListener() {
            public void onAdLoaded(Ad ad) {

                interstitialAd.show();
            }

            public void onError(Ad ad, AdError error) {


                Log.d("hey", "eroooooooor" + error.getErrorMessage());
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Use this function to resume your app's flow
            }
        });

        interstitialAd.loadAd();
    }


}
