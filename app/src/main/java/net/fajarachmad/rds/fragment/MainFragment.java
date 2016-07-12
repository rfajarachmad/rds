package net.fajarachmad.rds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.Gson;

import net.fajarachmad.rds.R;
import net.fajarachmad.rds.wrapper.TOC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahsyaa on 7/5/2016.
 */
public class MainFragment extends Fragment {

    private Gson gson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) view.findViewById(R.id.list_view_main);
        final List<TOC> list = new ArrayList<>();
        list.add(new TOC("bab1","BAB 1", "Keikhlasan Dan Menghadirkan Niat Dalam Segala Perbuatan"));
        list.add(new TOC("bab1","BAB 2", "Taubat"));
        list.add(new TOC("bab1","BAB 3", "Sabar"));
        list.add(new TOC("bab1","BAB 4", "Kebenaran"));
        list.add(new TOC("bab1","BAB 5", "Muraqabah (Pengintaian)"));
        list.add(new TOC("bab1","BAB 6", "Ketaqwaan"));
        list.add(new TOC("bab1","BAB 7", "Yakin Dan Tawakkal"));
        list.add(new TOC("bab1","BAB 8", "Bertindak Lurus"));
        list.add(new TOC("bab1","BAB 9", "Memikir-mikirkan Keagungan Makhluk-makhluk Allah Ta'ala"));
        list.add(new TOC("bab1","BAB 10", "Bersegera Kepada Kebaikan Dan Menganjurkan Kepada"));
        listView.setAdapter(new ListViewAdapter(view.getContext(), R.layout.list_view_main, R.id.list_view_main_text, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TOC selected = list.get(i);
                Bundle bundle = new Bundle();
                Fragment fragment = new MainDetailFragment();
                bundle.putString(TOC.class.getName(), gson.toJson(selected));
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
        return view;
    }

    public class ListViewAdapter extends ArrayAdapter<TOC> {

        private List<TOC> listData;

        public ListViewAdapter(Context context, int resource, int txtViewId, List<TOC> data) {
            super(context, resource, txtViewId, data);
            listData = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.list_view_main, null);
            }
            TOC toc = listData.get(position);
            ((TextView)view.findViewById(R.id.list_view_main_text)).setText(toc.getDescription());
            String firstLetter = String.valueOf(toc.getDescription().charAt(0));

            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(toc);

            TextDrawable drawable = TextDrawable.builder().buildRound(firstLetter, color);
            ((ImageView) view.findViewById(R.id.list_view_main_image)).setImageDrawable(drawable);

            return view;
        }
    }
}
