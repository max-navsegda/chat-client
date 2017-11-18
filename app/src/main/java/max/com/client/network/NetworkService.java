package max.com.client.network;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import max.com.client.activity.GameActivity;
import max.com.client.events.UpdateEvent;
import max.com.client.events.ConnectionErrorEvent;
import max.com.client.events.ErrorMessageEvent;
import max.com.client.events.LoginEvent;
import max.com.client.model.Message;
import max.com.client.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim on 9/27/2017.
 */

public class NetworkService {

    static RetrofitConfig retrofitConfig;

    public NetworkService() {
        retrofitConfig = new RetrofitConfig();
    }

    public void register(String phone, String password) {

        Call<User> call = retrofitConfig.getApiNetwork().login(phone, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    String s = response.errorBody().string();
                    switch (s) {
                        case "Wrong pass":
                            EventBus.getDefault().post(new ErrorMessageEvent("Wrong pass"));
                            break;
                        case "Enter phone":
                            EventBus.getDefault().post(new ErrorMessageEvent("Enter phone"));
                            break;
                        case "Enter pass":
                            EventBus.getDefault().post(new ErrorMessageEvent("Enter pass"));
                            break;
                        case "Register ok":
                            EventBus.getDefault().post(new ErrorMessageEvent("Register ok"));
                        case "Login":
                            EventBus.getDefault().post(new LoginEvent());
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                EventBus.getDefault().post(new ConnectionErrorEvent("Turn on wifi or gprs"));
            }
        });
    }

    public static void findMessage(String usePhone, String senderPhone) {

        Call<List<Message>> call = retrofitConfig.getApiNetwork().findMessage(usePhone, senderPhone);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                GameActivity.list.clear();
                GameActivity.list.addAll(response.body());
                EventBus.getDefault().post(new UpdateEvent());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });

    }

    public static void sendMessage(String userPhone, String senderPhone, String message) {

        Call<Message> call = retrofitConfig.getApiNetwork().createMessage(userPhone, senderPhone, message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}

