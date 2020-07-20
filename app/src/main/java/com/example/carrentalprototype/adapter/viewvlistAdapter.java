package com.example.carrentalprototype.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.carrentalprototype.pojo.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carrentalprototype.admin.*;

import com.example.carrentalprototype.R;

import java.util.ArrayList;
import java.util.List;

public class viewvlistAdapter extends RecyclerView.Adapter<viewvlistAdapter.ViewHolder> {
    Context context;
    List<pojoVehicle> ar1;
//  ArrayList<String> ar2;
    //  ArrayList<String> ar3;


    public viewvlistAdapter(Context context, List<pojoVehicle> ar1) {
        this.context=context;
        this.ar1=ar1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.items_adminvehiclelist, parent, false);
        ViewHolder holder = new ViewHolder(listItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        pojoVehicle pj=ar1.get(position);

       // holder.vname.setText(pj.getTitle());
        //holder.vseats.setText(pj.getSeats());
        //holder.vtype.setText(pj.getClas());
        //holder.vehid.setText(pj.getVehicleId());
        Toast.makeText(context,"onbind",Toast.LENGTH_SHORT).show();
        //holder.lout.setOnClickListener(new View.OnClickListener() {
        /*    @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,adminViewVehicle.class);
                intent.putExtra("vehicleid",holder.vehid.getText().toString());
                context.startActivity(intent);

            }
        });

         */

    }

    @Override
    public int getItemCount()
    {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView vname;
        TextView vseats;
        TextView vtype;
        TextView vehid;
        LinearLayout lout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //img=itemView.findViewById(R.id.imageView1);
            //vname=itemView.findViewById(R.id.vehiclename);
            //vseats=itemView.findViewById(R.id.vehiclecity);
            //vtype=itemView.findViewById(R.id.availability);
           // lout=itemView.findViewById(R.id.vehlist);
          // vehid=itemView.findViewById(R.id.vid);
        }
    }
}


