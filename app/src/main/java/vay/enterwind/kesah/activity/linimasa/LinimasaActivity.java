package vay.enterwind.kesah.activity.linimasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.auth.IntroActivity;
import vay.enterwind.kesah.library.TokenManager;

public class LinimasaActivity extends AppCompatActivity {

    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linimasa);
        ButterKnife.bind(this);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
    }

    @OnClick(R.id.logout)
    void keluar() {
        tokenManager.deleteToken();
        startActivity(new Intent(LinimasaActivity.this, IntroActivity.class));
        finish();
    }
}
