package com.example.carrentalprototype.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.carrentalprototype.pojo.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carrentalprototype.*;
import com.example.carrentalprototype.admin.adminViewUser;
import com.example.carrentalprototype.pojo.*;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.pojo.pojoUser;

import java.util.List;

public class adapteruserbookinglist extends RecyclerView.Adapter<adapteruserbookinglist.ViewHolder> {
    Context context;
    List<pojoBookingList> ar1;
    public adapteruserbookinglist(Context context,List<pojoBookingList> ar1) {
        this.context=context;
        this.ar1=ar1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsuserbookinglist, parent, false);

        adapteruserbookinglist.ViewHolder holder=new adapteruserbookinglist.ViewHolder(listItem);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        pojoBookingList pj=ar1.get(position);

        holder.vname.setText(pj.getTitle());
        holder.pickup.setText(pj.getPickUpLocation());
        holder.dropoff.setText(pj.getDropOffLocation());
        holder.datetime.setText(pj.getPickUpDate()+"  "+pj.getPickUpTime());
        holder.bookid.setText(pj.getBookingId());
        holder.status.setText(pj.getBkstatus());

        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, clientViewBooking.class);
                intent.putExtra("bookingid",holder.bookid.getText().toString());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView vname,pickup,dropoff,datetime,bookid;
        ImageView img;
        LinearLayout lout;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vname=itemView.findViewById(R.id.vehiclename);
            pickup=itemView.findViewById(R.id.pickuploc);
            dropoff=itemView.findViewById(R.id.dropoffloc);
            datetime=itemView.findViewById(R.id.dateandtime);
            img=itemView.findViewById(R.id.imageView);
            lout=itemView.findViewById(R.id.loadViewBooking);
            bookid=itemView.findViewById(R.id.bid);
            status=itemView.findViewById(R.id.bookingstatus);

        }
    }
}


