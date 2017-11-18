package max.com.client.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.OnClick;
import max.com.client.R;
import max.com.client.events.ErrorMessageEvent;
import max.com.client.events.LoginEvent;
import max.com.client.model.User;
import max.com.client.network.NetworkService;
import max.com.client.network.RetrofitConfig;
import max.com.client.utils.Settings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    NetworkService networkService;
    EditText phone;
    EditText password;
    Button enter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        networkService = new NetworkService();
        phone = (EditText) findViewById(R.id.editPhone);
        password = (EditText) findViewById(R.id.editPassword);
        enter = (Button) findViewById(R.id.login_button);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void enter() {
        networkService.register(phone.getText().toString(), password.getText().toString());
        sharedPreferences = getSharedPreferences(Settings.APP_PREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Settings.APP_PREFERENSES_PHONE, phone.getText().toString());
        editor.commit();
        EventBus.getDefault().post(new LoginEvent());
    }

    @Override
    protected void onStart() {
        sharedPreferences = getSharedPreferences(Settings.APP_PREFERENCES, MODE_PRIVATE);
        String s = sharedPreferences.getString(Settings.APP_PREFERENSES_PHONE, "");
        if (!s.equals("")) EventBus.getDefault().post(new LoginEvent());
        super.onStart();
    }

    @Subscribe
    public void loginEvent(LoginEvent loginEvent) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    @Subscribe
    public void onMessageEvent(ErrorMessageEvent errorMessageEvent) {
        Toast.makeText(this, errorMessageEvent.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}


