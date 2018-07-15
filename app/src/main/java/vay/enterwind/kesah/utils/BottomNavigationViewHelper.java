package vay.enterwind.kesah.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.chat.ChatActivity;
import vay.enterwind.kesah.activity.facility.FacilityActivity;
import vay.enterwind.kesah.activity.linimasa.LinimasaActivity;
import vay.enterwind.kesah.activity.profil.ProfilActivity;
import vay.enterwind.kesah.activity.report.ReportActivity;

/**
 * Created by novay on 15/07/18.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    /**
     * Setup Bottom Navigation View
     * @param bottomNavigationViewEx
     */
    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextSize(10);
        bottomNavigationViewEx.setTextVisibility(true);
    }

    /**
     * Emable Navigation Setup
     * @param context
     * @param callingActivity
     * @param view
     */
    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_linimasa:
                        Intent intent1 = new Intent(context, LinimasaActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;

                    case R.id.ic_fasilitas:
                        Intent inbox  = new Intent(context, FacilityActivity.class);
                        context.startActivity(inbox);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        inbox.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        break;

                    case R.id.ic_laporan:
                        Intent intent2  = new Intent(context, ReportActivity.class);
                        context.startActivity(intent2);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        break;

                    case R.id.ic_obrolan:
                        Intent intent3  = new Intent(context, ChatActivity.class);
                        context.startActivity(intent3);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        break;

                    case R.id.ic_profil:
                        Intent intent4 = new Intent(context, ProfilActivity.class);
                        context.startActivity(intent4);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        break;

                }
                return false;
            }
        });
    }

}
