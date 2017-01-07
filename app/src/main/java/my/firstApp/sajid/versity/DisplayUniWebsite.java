package my.firstApp.sajid.versity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayUniWebsite extends AppCompatActivity {

    private WebView webView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_uni_website);
        webView=(WebView)findViewById(R.id.webView);
        textView=(TextView)findViewById(R.id.textView14);

        webView.getSettings().setJavaScriptEnabled(true);
        String code=getIntent().getStringExtra("code");
        String[] value=code.split("\n\n");
        textView.setText("Website : "+value[0]);
        Toast.makeText(this,value[1],Toast.LENGTH_LONG).show();
        try {
            webView.loadUrl(value[1]);
        }
        catch (Exception e){

        }
    }
}
