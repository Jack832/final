package com.bridgelabz.com.appscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment
{
    private RecyclerView recyclerView;
    private ViewAdapter adapter;
    public static ContentFragment getInstance(int position)
    {
        ContentFragment myFragment=new ContentFragment();
        Bundle args=new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_content,container,false);

        recyclerView=(RecyclerView)layout.findViewById(R.id.List);
        adapter=new ViewAdapter(getActivity(),GetData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }
    public static List<MyData> GetData()
    {
        List<MyData> data=new ArrayList<>();
        int [] mainIcon={R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};
        int[] shareIcon={R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share,R.drawable.share};
        String[] mainTitle={"Title1","Title2","Title3","Title1","Title2","Title3","Title1","Title2","Title3","Title1","Title2","Title3"};
        String[] statusTitle={"Opened","Clicked","Closed","Opened","Clicked","Closed","Opened","Clicked","Closed","Closed","Closed","Closed"};
        String[] timeTitle={"Today at 10AM","Yesterday","Today at 8PM","Today at 10AM","Yesterday","Today at 8PM","Today at 10AM","Yesterday","Today at 8PM","Today at 8PM","Today at 8PM","Today at 8PM"};
        String[] viewTitle={"45 Views","10 Views","20 Views","45 Views","10 Views","20 Views","45 Views","10 Views","20 Views","20 Views","20 Views","20 Views"};
        String[] partTitle={"100 participant","700 participant","350 participant","100 participant","700 participant","350 participant",
                "100 participant","700 participant","350 participant","350 participant","350 participant","350 participant"};

        for (int i=0;i<mainTitle.length && i<timeTitle.length && i<mainIcon.length;i++)
        {
            MyData current=new MyData();
            current.mainIcon=mainIcon[i];
            current.shareIcon=shareIcon[i];

            current.mainTitle=mainTitle[i];
            current.statusTitle=statusTitle[i];
            current.viewTitle=viewTitle[i];
            current.timeTitle=timeTitle[i];
            current.partTitle=partTitle[i];

            data.add(current);
        }
        return data;
    }
}