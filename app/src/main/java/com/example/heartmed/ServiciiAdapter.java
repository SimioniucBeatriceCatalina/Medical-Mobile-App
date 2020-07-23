package com.example.heartmed;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class ServiciiAdapter  extends ArrayAdapter<Serviciu> {


    private Context context;
    private int resource;
    private List<Serviciu> servicii;
    private LayoutInflater layout;



    public ServiciiAdapter(Context context, int resource, List<Serviciu> servicii, LayoutInflater layout) {
       super(context,resource,servicii);
        this.context=context;
        this.resource= resource;
        this.servicii = servicii;
        this.layout = layout;
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = layout.inflate(resource, viewGroup, false);
        Serviciu serviciu=servicii.get(i);

        if(cview==null){
            cview=layout.inflate(R.layout.item_servicii,null,true);
        }

        TextView den = cview.findViewById(R.id.tvserviciu);
        TextView p= cview.findViewById(R.id.tvpr);

        serviciu=servicii.get(i);

   den.setText(serviciu.getDenumire());
   p.setText(""+serviciu.getPret());

        return cview;
    }
}
