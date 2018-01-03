package com.sldeveloper.baiscopelink;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import java.util.Calendar;

public class developer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simulateDayNight(/* DAY */ 0);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logodeveloper)
                .setDescription("All the codes of this software have been designed by DroidLanka " +
                        "If you want to make this work too, call me")
                .addGroup("Connect with us")
                .addItem(new Element().setTitle("TEL: 0750912191"))
                .addItem(new Element().setTitle("TEL: 07506164586"))
                .addEmail("lankadroid@gmail.com")
                .addFacebook("sanoj.jayathilaka1")
                .addTwitter("@Sanojprabath")
                .addYoutube("UCxLv753NA8j78TewzkY59Fw")
                .addGitHub("00sanoj00")
                .addInstagram("sanojprabath")
                .addItem(new Element().setTitle("special thanks"))
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("ViksaaSkool/AwesomeSplash"))
                .addWebsite("https://github.com/ViksaaSkool/AwesomeSplash")
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("PSD-Company/duo-navigation-drawer"))
                .addWebsite("https://github.com/PSD-Company/duo-navigation-drawer/blob/master/README.md")
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("AlexKolpa/fab-toolbar"))
                .addWebsite("https://github.com/AlexKolpa/fab-toolbar")
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("intuit/sdp"))
                .addWebsite("https://github.com/intuit/sdp")
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("medyo/android-about-page"))
                .addWebsite("https://github.com/medyo/android-about-page")
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("florent37/TutoShowcase"))
                .addWebsite("https://github.com/florent37/TutoShowcase")
                .addItem(new Element().setTitle("--"))
                .addItem(new Element().setTitle("andyxialm/ColorDialog"))
                .addWebsite("https://github.com/andyxialm/ColorDialog")
                .addItem(new Element().setTitle("--"))
                .addItem(getCopyRightsElement())
                .create();


        setContentView(aboutPage);
    }


    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return copyRightsElement;
    }

    void simulateDayNight(int currentSetting) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }
}