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
import org.kidcontact.focussis.data.PortalCourse;
import org.kidcontact.focussis.views.DividerItemDecoration;
import org.kidcontact.focussis.views.adapters.PortalAssignmentCourseAdapter;
import org.kidcontact.focussis.R;

import java.util.Collections;
import java.util.List;

public class PortalAssignmentsTabFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Portal portal;

    public PortalAssignmentsTabFragment() {
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
        boolean hasAssignments = false;
        for (PortalCourse c : portal.getCourses()) {
            if (c.hasAssignments()) {
                hasAssignments = true;
                break;
            }
        }
        if (hasAssignments) {
            view = inflater.inflate(R.layout.fragment_portal_assignments_tab, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.assignments_course_recycler_view);
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
            recyclerView.setLayoutManager(layoutManager);
            List<PortalCourse> sortedCourses = portal.getCourses();
            Collections.sort(sortedCourses);
            adapter = new PortalAssignmentCourseAdapter(sortedCourses);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
    }

}