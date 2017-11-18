package max.com.client.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import max.com.client.R;

/**
 * Created by Maxim on 11/7/2017.
 */

public class Menu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        TextView menu = (TextView) view.findViewById(R.id.menu);
        Button logout = (Button) view.findViewById(R.id.logout);

        return view;

    }
}
