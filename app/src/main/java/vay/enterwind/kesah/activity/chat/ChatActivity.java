package vay.enterwind.kesah.activity.chat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.BaseActivity;
import vay.enterwind.kesah.activity.linimasa.LinimasaActivity;

public class ChatActivity extends BaseActivity {

    private static final String TAG = ChatActivity.class.getSimpleName();
    private Context mContext = ChatActivity.this;
    private static final int ACTIVITY_NUM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setupBottomNavigationView(mContext, ACTIVITY_NUM);
    }
}
