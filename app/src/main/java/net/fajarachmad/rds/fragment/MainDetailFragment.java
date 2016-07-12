package net.fajarachmad.rds.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import net.fajarachmad.rds.MainActivity;
import net.fajarachmad.rds.R;
import net.fajarachmad.rds.wrapper.TOC;

/**
 * Created by Mahsyaa on 7/5/2016.
 */
public class MainDetailFragment extends Fragment {

    private TOC toc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Gson gson = new Gson();
        toc = gson.fromJson(getArguments().getString(TOC.class.getName()), TOC.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_detail, container, false);

        MainActivity activity = ((MainActivity)getActivity());
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(toc.getName());
        actionBar.setSubtitle(toc.getDescription());
        actionBar.setDisplayHomeAsUpEnabled(true);
        ((DrawerLayout)activity.findViewById(R.id.drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getContext(), String.valueOf(item.getItemId())+"-"+String.valueOf(android.R.id.home), Toast.LENGTH_SHORT).show();
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
            ((DrawerLayout)getActivity().findViewById(R.id.drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}
