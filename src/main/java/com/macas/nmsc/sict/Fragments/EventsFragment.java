package com.macas.nmsc.sict.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.macas.nmsc.sict.MotionStyle;
import com.macas.nmsc.sict.MotionToast;
import com.macas.nmsc.sict.R;
import com.macas.nmsc.sict.databinding.EventsFragmentActivityBinding;
import com.macas.nmsc.sict.useractivities.OnboardActivity;
import com.moos.library.HorizontalProgressView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EventsFragment extends Fragment {
    EventsFragmentActivityBinding binding;
    ViewPager2 vpager;
    TabLayout tbl;
    Handler handler;
    String[] bgcolors = {"#fee4cb", "#e9e7fd", "#ffd3e2", "#dbf6fd", "#c8f7dc"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        binding = EventsFragmentActivityBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.events_fragment_activity, container, false);
        vpager = view.findViewById(R.id.viewpager);
        tbl = view.findViewById(R.id.tab_layout);
        handler = new Handler(Looper.getMainLooper());
        return view;
    }

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setUpTabs();
    }

    public void setUpTabs() {
        vpager.setAdapter(new EventsListPager());
        new TabLayoutMediator(
                        tbl,
                        vpager,
                        new TabLayoutMediator.TabConfigurationStrategy() {
                            @Override
                            public void onConfigureTab(TabLayout.Tab tab, int position) {

                                if (position == 0) {
                                    tab.setText("Ongoing");
                                } else if (position == 1) {
                                    tab.setText("Upcoming");
                                } else if (position == 2) {
                                    tab.setText("All");
                                }
                            }
                        })
                .attach();

        tbl.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (tab.getPosition() == 0) {

                        } else if (tab.getPosition() == 1) {

                        } else if (tab.getPosition() == 2) {

                        }
                    }
                });
    }

    public void setTabs() {

        showWarningMsg("wtf");
        tbl.addTab(new TabLayout.Tab().setText("jsjs"));
        tbl.addTab(new TabLayout.Tab().setText("test"));
        binding.tabLayout.addTab(new TabLayout.Tab().setText("test"));
    }

    public class EventsListPager extends RecyclerView.Adapter<EventsListPager.ViewHolder> {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        public EventsListPager() {}

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _v = inflater.inflate(R.layout.event_by_section, null);
            View view = inflater.inflate(R.layout.event_by_section, null);
            RecyclerView.LayoutParams _lp =
                    new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(_lp);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder _holder, final int position) {
            View v = _holder.itemView;
            // TextInputEditText ed = v.findViewById(R.id.searchedtxt);
            RecyclerView eventBySec = v.findViewById(R.id.eventlistrecyc);
            loadItemLists(eventBySec, position);
            showWarningMsg("exec" + position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }
    }

    public void loadItemLists(RecyclerView recv, int pos) {
        ArrayList<HashMap<String, Object>> itemsgot = new ArrayList<>();
        ListItemEvents itemsadapter;
        if (pos == 0) {
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "Monthly Mass");
                data.put("datestart", "May 26, 2022");
                data.put("datecreated", "May 12, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "SICT DAY");
                data.put("datestart", "June 29, 2022");
                data.put("datecreated", "May 08, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "NMSCST Orientation");
                data.put("datestart", "May 24, 2022");
                data.put("datecreated", "May 09, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }

        } else if (pos == 1) {
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "SICT DAY");
                data.put("datestart", "May 29, 2022");
                data.put("datecreated", "June 30, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }
        } else if (pos == 2) {
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "Monthly Mass");
                data.put("datestart", "May 26, 2022");
                data.put("datecreated", "May 12, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "SICT DAY");
                data.put("datestart", "June 29, 2022");
                data.put("datecreated", "May 08, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }
            {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name", "NMSCST Orientation");
                data.put("datestart", "May 24, 2022");
                data.put("datecreated", "May 09, 2022");
                data.put("participant", "All students");
                itemsgot.add(data);
            }
        }

        recv.setAdapter(new ListItemEvents(itemsgot));
        recv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void errorToast(String msg) {

        MotionToast motionToast =
                new MotionToast(
                        getActivity(),
                        1,
                        MotionStyle.LIGHT,
                        MotionStyle.ERROR,
                        MotionStyle.BOTTOM,
                        "ERROR",
                        msg,
                        Toast.LENGTH_LONG);
        motionToast.show();
        // Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    }

    public void showWarningMsg(String msg) {

        MotionToast motionToast =
                new MotionToast(
                                getActivity(),
                                1,
                                MotionStyle.LIGHT,
                                MotionStyle.WARNING,
                                MotionStyle.BOTTOM,
                                "WARNING",
                                msg,
                                Toast.LENGTH_LONG)
                        .show();
    }

    public class ListItemEvents extends RecyclerView.Adapter<ListItemEvents.ViewHolder> {
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        public ListItemEvents(ArrayList<HashMap<String, Object>> data) {
            this.data = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.event_list_item, null);
            RecyclerView.LayoutParams _lp =
                    new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(_lp);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder _holder, final int position) {
            View v = _holder.itemView;
            Random random = new Random();
            String eventname = data.get(position).get("name").toString();
             String datestr = data.get(position).get("datecreated").toString() + " - " +data.get(position).get("datestart").toString();
            
            String participantstr = data.get(position).get("participant").toString();
            int randomRange = 4;
            TextView title = v.findViewById(R.id.eventname);
            TextView datetv = v.findViewById(R.id.date);
            TextView partictype = v.findViewById(R.id.participants);
            title.setText(eventname);
            datetv.setText(datestr);
            partictype.setText(participantstr);
            HorizontalProgressView hpv = v.findViewById(R.id.progressView_horizontal);
            hpv.setEndProgress(random.nextInt(100));
            hpv.startProgressAnimation();
            CardView carditem = v.findViewById(R.id.carditem);
            //carditem.setCardBackgroundColor(Color.parseColor(bgcolors[random.nextInt(randomRange)]));
            carditem.setOnClickListener(view -> {
                oboardAct();
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }
        public void oboardAct(){
            Intent i = new Intent(getActivity(), OnboardActivity.class);
            startActivity(i);
        }
    }
}
