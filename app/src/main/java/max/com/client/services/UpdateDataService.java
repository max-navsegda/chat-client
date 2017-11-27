package max.com.client.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import max.com.client.R;
import max.com.client.activity.GameActivity;
import max.com.client.adapters.RecyclerAdapter;
import max.com.client.events.ErrorMessageEvent;
import max.com.client.events.UpdateEvent;
import max.com.client.model.Message;

/**
 * Created by Maxim on 11/26/2017.
 */

public class UpdateDataService extends Service {

    RecyclerAdapter recyclerAdapter;
    public static boolean isRun = true;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void someTask() {
        new Thread(new Runnable() {
            public void run() {
                while (isRun) {
                    GameActivity.setData();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }
}
