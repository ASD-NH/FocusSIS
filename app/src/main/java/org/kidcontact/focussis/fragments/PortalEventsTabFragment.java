package org.kidcontact.focussis.fragments;

/**
 * Created by slensky on 4/2/17.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.kidcontact.focussis.util.GsonSingleton;
import org.kidcontact.focussis.data.Portal;
import org.kidcontact.focussis.views.DividerItemDecoration;
import org.kidcontact.focussis.views.adapters.PortalEventAdapter;
import org.kidcontact.focussis.R;

public class PortalEventsTabFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Portal portal;

    public PortalEventsTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = GsonSingleton.getInstance();
        portal = gson.fromJson(getArguments().getString(getString(R.string.EXTRA_PORTAL)), Portal.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_portal_empty, container, false);
        if (portal.hasEvents()) {
            view = inflater.inflate(R.layout.fragment_portal_events_tab, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
            recyclerView.setLayoutManager(layoutManager);
            adapter = new PortalEventAdapter(portal.getEvents());
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }

}