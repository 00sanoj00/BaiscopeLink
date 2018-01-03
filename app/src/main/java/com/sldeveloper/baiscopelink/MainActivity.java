package com.sldeveloper.baiscopelink;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import android.widget.*;
import cn.refactor.lib.colordialog.PromptDialog;
import com.github.alexkolpa.fabtoolbar.FabToolbar;
import com.github.florent37.tutoshowcase.TutoShowcase;
import org.w3c.dom.Document;


import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import com.sldeveloper.baiscopelink.BuildConfig;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.sldeveloper.baiscopelink.R.attr.title;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    String TextFileURL = "http://baiscopelink.com/app_update.txt" ;
    TextView textViewver ;
    TextView sanoj ;
    URL url ;
    String TextHolder = "" , TextHolder2 = "";
    BufferedReader bufferReader ;
    Handler mHandler = new Handler();
    private ViewPager viewPager;
    private WelcomeActivity.MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;




    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    private FabToolbar fabToolbar;
    ProgressBar progressBar;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {

        }
        else{
            displayTuto();
        }
        //displayTuto();



        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        myWebView = (WebView) findViewById(R.id.baiscopelinks);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.setWebChromeClient(new WebChromeClientDemo());
        myWebView.setBackgroundColor(Color.BLACK);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //myWebView.loadUrl("https://www.baiscopelink.com");

        textViewver = (TextView)findViewById(R.id.textver);




        if(haveNetworkConnection()){
           myWebView.loadUrl("https://www.baiscopelink.com");
        } else {
           myWebView.loadUrl("file:///android_asset/error.html");

        }


       ////////////////////////////////////////////////////////////////////////////////////
        //update chq
        //mHandler.removeCallbacks(this);

        new GetNotePadFileFromServer().execute();


        Handler ha=new Handler();
        ha.postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewver = (TextView)findViewById(R.id.textver);
                String versionName = BuildConfig.VERSION_NAME;
                String str1 = textViewver.getText().toString();

                if (str1.equals(versionName)){


                }
                else if (str1.equals("ver")){


                }
                else if (str1.equals("")){


                }
                else if (str1.equals(" ")){


                }
                else{

                    updateavailble();

                }

            }
        }, 10000);


        ////////////////////////////////////////////////////////////////////////////////////


        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));

        fabToolbar.setColor(getResources().getColor(R.color.red));

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, R.string.button_clicked, Toast.LENGTH_SHORT).show();
        fabToolbar.hide();

    }

    public void setting(View V) {
        WebView myWebView = (WebView) findViewById(R.id.baiscopelinks);
        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //myWebView.loadUrl("https://www.baiscopelink.com");
        if(haveNetworkConnection()){
            myWebView.loadUrl( "https://www.baiscopelink.com" );
        } else {
            myWebView.loadUrl("file:///android_asset/error.html");
        }
        fabToolbar.setColor(getResources().getColor(R.color.red));
        fabToolbar.hide();
    }

    public void help(View V) {
        WebView myWebView = (WebView) findViewById(R.id.baiscopelinks);

        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //myWebView.loadUrl("https://www.baiscopelink.com");
        if(haveNetworkConnection()){
            myWebView.loadUrl("https://www.baiscopelink.com/how-to-download-movies");
        } else {
            myWebView.loadUrl("file:///android_asset/error.html");
        }
        fabToolbar.setColor(getResources().getColor(R.color.colorPrimary));
        fabToolbar.hide();
    }

    public void about(View V) {
        startActivity(new Intent(MainActivity.this, About.class));
        fabToolbar.setColor(getResources().getColor(R.color.black));
        fabToolbar.hide();
    }

    public void developer(View V) {
        startActivity(new Intent(MainActivity.this, developer.class));
        fabToolbar.setColor(getResources().getColor(R.color.pink_pressed));
        fabToolbar.hide();
    }

    public void backing(View V) {
        WebView myWebView = (WebView) findViewById(R.id.baiscopelinks);
        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //myWebView.loadUrl("https://www.baiscopelink.com");
        if(haveNetworkConnection()){
            myWebView.loadUrl( "javascript:window.location.reload( true )" );
        } else {
           myWebView.loadUrl("file:///android_asset/error.html");
        }

        fabToolbar.setColor(getResources().getColor(R.color.red));
        fabToolbar.hide();
    }


    private class MyWebViewClient extends WebViewClient {


        ///////////////////////////////////////////////////////////////

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //view.loadUrl(url);
            //view.loadUrl(url);
            if (Uri.parse(url).getHost().equals("www.baiscopelink.com")) {
                // This is my web site, so do not override; let my WebView load the page
                //or we can add more allowed host in if condition
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            progressBar.setProgress(100);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
        }




    }
    private class WebChromeClientDemo extends WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
        }



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        } else {
            //finish();
            new PromptDialog(this)
                    .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                    .setAnimationEnable(true)
                    .setTitleText(getString(R.string.exiti))
                    .setContentText(getString(R.string.exit))
                    .setPositiveListener(getString(R.string.ok), new PromptDialog.OnPositiveListener() {
                        @Override
                        public void onClick(PromptDialog dialog) {
                            finish();
                        }
                    }).show();


        }
        return super.onKeyDown(keyCode, event);
    }

    protected void displayTuto() {
        prefManager.setFirstTimeLaunch(false);

        TutoShowcase.from(this)
                .setListener(new TutoShowcase.Listener() {
                    @Override
                    public void onDismissed() {
                        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    }
                })
                .setContentView(R.layout.show_case)
                .setFitsSystemWindows(false)
                .on(R.id.textViewggg)
                .addCircle()
                .withBorder()
                .onClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })

                .on(R.id.fab_toolbar)
                .displaySwipableLeft()
                .delayed(399)
                .animated(true)

                .show();
    }
    public class GetNotePadFileFromServer extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(TextFileURL);

                bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((TextHolder2 = bufferReader.readLine()) != null) {

                    TextHolder += TextHolder2;
                }
                bufferReader.close();

            } catch (MalformedURLException malformedURLException) {

                // TODO Auto-generated catch block
               //malformedURLException.printStackTrace();
                //TextHolder = malformedURLException.toString();

            } catch (IOException iOException) {

                // TODO Auto-generated catch block
                //iOException.printStackTrace();

                //TextHolder = iOException.toString();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void finalTextHolder) {

            textViewver.setText(TextHolder);

            super.onPostExecute(finalTextHolder);
        }

    }


    public void updateavailble(){

        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setTitleText(getString(R.string.Update))
                .setContentText(getString(R.string.dilas))
                .setPositiveListener(getString(R.string.Download), new PromptDialog.OnPositiveListener() {
            @Override
            public void onClick(PromptDialog dialog) {

                if(haveNetworkConnection()){
                    myWebView.loadUrl("https://www.baiscopelink.com/download-our-official-app");
                } else {
                    myWebView.loadUrl("file:///android_asset/error.html");

                }
                dialog.dismiss();
            }
        }).show();


    }

}







