package com.example.carrentalprototype.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carrentalprototype.admin.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrentalprototype.R;
import com.example.carrentalprototype.pojo.pojoBookingList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class adapterbookinglist extends RecyclerView.Adapter<adapterbookinglist.ViewHolder> {

    List<pojoBookingList> ar1;
    Context context;
    pojoBookingList hn ;
    public adapterbookinglist(Context context, List<pojoBookingList> ar1) {
        this.context=context;
        this.ar1=ar1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsbookinglist, parent, false);

        ViewHolder holder=new ViewHolder(listItem);

        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        hn=new pojoBookingList();
        pojoBookingList pj=ar1.get(position);
        holder.nm.setText(pj.getFullname());
        holder.vehiclenm.setText(pj.getTitle());
        holder.datepcup.setText(pj.getPickUpDate());
        holder.timepcup.setText(pj.getPickUpTime());
        holder.id.setText(pj.getBookingId());
        hn=pj;
        FirebaseStorage firebaseStorage2;
        StorageReference storageReference2;

        firebaseStorage2 = FirebaseStorage.getInstance();
        storageReference2 = firebaseStorage2.getReference();

        StorageReference imageRef2 = storageReference2.child("Images/"+pj.getProfilePic());

        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //  Picasso.get().load(uri).into(holder.);

                Glide.with(context.getApplicationContext())
                        .load(uri)
                        .circleCrop()
                        .into(holder.imguser);


                // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
            }
        });



        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  String tempvsr=holder.id.getText().toString();
                //  Toast.makeText(context,tempvsr,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,adminViewBooking.class);
                intent.putExtra("bkndin",holder.id.getText().toString());
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
        TextView nm,vehiclenm,datepcup,timepcup,id;
        ImageView imguser;
        LinearLayout lout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.idd);
            nm=itemView.findViewById(R.id.nameuser);
            vehiclenm=itemView.findViewById(R.id.namecar);
            imguser=itemView.findViewById(R.id.imageView);
            datepcup=itemView.findViewById(R.id.datepickup);
            timepcup=itemView.findViewById(R.id.timepickup);
            lout=itemView.findViewById(R.id.booklist);

        }

    }
}



