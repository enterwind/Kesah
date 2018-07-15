package vay.enterwind.kesah.activity.auth;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.roger.catloadinglibrary.CatLoadingView;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

import vay.enterwind.kesah.R;
import vay.enterwind.kesah.activity.linimasa.LinimasaActivity;
import vay.enterwind.kesah.entities.AccessToken;
import vay.enterwind.kesah.entities.ApiError;
import vay.enterwind.kesah.library.TokenManager;
import vay.enterwind.kesah.library.Utils;
import vay.enterwind.kesah.network.ApiService;
import vay.enterwind.kesah.network.RetrofitBuilder;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Context mContext = RegisterActivity.this;

    @BindView(R.id.scrollView) ScrollView scrollView;

    @BindView(R.id.txtSandi) TextInputEditText txtSandi;
    @BindView(R.id.txtUlangiSandi) TextInputEditText txtUlangiSandi;
    @BindView(R.id.switchSetuju) Switch switchSetuju;

    @BindView(R.id.inputNama) TextInputLayout inputNama;
    @BindView(R.id.inputEmail) TextInputLayout inputEmail;
    @BindView(R.id.inputSandi) TextInputLayout inputSandi;
    @BindView(R.id.inputUlangiSandi) TextInputLayout inputUlangiSandi;

    ApiService service;
    Call<AccessToken> call;
    AwesomeValidation validator;
    TokenManager tokenManager;
    CatLoadingView loading;

    boolean agree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        loading = new CatLoadingView();

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if(tokenManager.getToken().getAccessToken() != null){
            startActivity(new Intent(RegisterActivity.this, LinimasaActivity.class));
            finish();
        }

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        txtSandi.setTransformationMethod(new PasswordTransformationMethod());
        txtUlangiSandi.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.btnBack)
    void kembali() {
        finish();
    }

    @OnClick(R.id.btnSimpan)
    void simpan() {
        loading.show(getSupportFragmentManager(), "");

        String nama = inputNama.getEditText().getText().toString();
        String email = inputEmail.getEditText().getText().toString();
        String password = inputSandi.getEditText().getText().toString();
        String repeat = inputUlangiSandi.getEditText().getText().toString();

        switchSetuju.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                agree = isChecked;
            }
        });

        if(agree) {
            call = service.register(nama, email, password, repeat);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Log.w(TAG, "onResponse: " + response);
                    if (response.isSuccessful()) {
                        Log.w(TAG, "onResponses: " + response.body() );
                        tokenManager.saveToken(response.body());
                        startActivity(new Intent(RegisterActivity.this, LinimasaActivity.class));
                        finish();
                    } else {
                        handleErrors(response.errorBody());
                    }
                    loading.dismiss();
                }
                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());
                    loading.dismiss();
                }
            });
        } else {
            Toast.makeText(mContext, "Anda belum menyetujui syarat dan ketentuan.", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        }
    }

    private void handleErrors(ResponseBody response) {
        ApiError apiError = Utils.converErrors(response);
        for(Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()){
            if(error.getKey().equals("nama")){
                inputNama.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("email")){
                inputEmail.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("password")){
                inputSandi.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("repeat_password")){
                inputUlangiSandi.setError(error.getValue().get(0));
            }
        }
        loading.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null) {
            call.cancel();
            call = null;
        }
    }
}
