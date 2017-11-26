package max.com.client.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import max.com.client.R;
import max.com.client.adapters.RecyclerAdapter;
import max.com.client.events.UpdateEvent;
import max.com.client.model.Message;
import max.com.client.network.NetworkService;
import max.com.client.utils.Settings;

/**
 * Created by Maxim on 10/9/2017.
 */

public class GameActivity extends AppCompatActivity {

    RecyclerAdapter recyclerAdapter;
    EditText message;
    Button send;
    ImageView white;
    ImageView black;
    String userPhone;
    RecyclerView recyclerView;
    final static String nameVariableKey = "NAME_VARIABLE";

    public static ArrayList<Message> list = new ArrayList<>();

    @Subscribe
    public void update(UpdateEvent updateEvent) {
        recyclerAdapter.notifyDataSetChanged();
    }

    public void setData() {
        try {
            NetworkService.findMessage(userPhone, "1111");
        } catch (Exception e) {
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        send = (Button) findViewById(R.id.send);
        white = (ImageView) findViewById(R.id.white);
        black = (ImageView) findViewById(R.id.black);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        message = (EditText) findViewById(R.id.Message);
        LoginActivity.sharedPreferences = getSharedPreferences(Settings.APP_PREFERENCES, MODE_PRIVATE);
        userPhone = LoginActivity.sharedPreferences.getString(Settings.APP_PREFERENSES_PHONE, "");
        recyclerAdapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        setData();
        recyclerAdapter.notifyDataSetChanged();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.send)
    public void send() {
        NetworkService.sendMessage(userPhone, "1111", message.getText().toString());
        setData();
    }

    public void logOut() {
        LoginActivity.editor = LoginActivity.sharedPreferences.edit();
        LoginActivity.editor.clear();
        LoginActivity.editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logOut:
                logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String s = message.getText().toString();
        outState.putString(nameVariableKey, s);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String s1 = savedInstanceState.getString(nameVariableKey);
        message.setText(s1);
    }

}


