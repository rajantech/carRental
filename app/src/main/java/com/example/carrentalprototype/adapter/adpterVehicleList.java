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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carrentalprototype.R;
import com.example.carrentalprototype.admin.*;
import com.example.carrentalprototype.pojo.pojoUser;
import com.example.carrentalprototype.pojo.pojoVehicle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class adpterVehicleList extends RecyclerView.Adapter<adpterVehicleList.ViewHolder>{
    Context context;
    List<pojoVehicle> ar1;
    public adpterVehicleList(Context context, List<pojoVehicle> ar1) {

        this.context=context;
        this.ar1=ar1;
    }

    @NonNull
    @Override
    public adpterVehicleList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.items_adminvehiclelist, parent, false);

        ViewHolder holder=new ViewHolder(listItem);

        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        pojoVehicle pj=ar1.get(position);

        holder.vehicleName.setText(pj.getTitle());
        holder.vehicleCity.setText(pj.getClas());
        holder.model.setText(pj.getModel());
       // holder.model.setText(pj.getVehicleImage());

        holder.vid.setText(pj.getVehicleId());

        // Toast.makeText(context, pj.getVehicleImage(), Toast.LENGTH_SHORT).show();

        FirebaseStorage firebaseStorage2;
        StorageReference storageReference2;

        firebaseStorage2 = FirebaseStorage.getInstance();
        storageReference2 = firebaseStorage2.getReference();

        StorageReference imageRef2 = storageReference2.child("Images/"+pj.getVehicleImage());
       // StorageReference imageRef2 = storageReference2.child("Images/vehicle1593802308634.png");



        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //  Picasso.get().load(uri).into(holder.);

                Glide.with(context)
                        .load(uri)
                        .into(holder.imageView);


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
                Intent i = new Intent(context, adminViewVehicle.class);
                i.putExtra("vehicleid", holder.vid.getText().toString());
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout lout;
        TextView vehicleName;
        TextView vehicleCity, model, vid;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          vid=itemView.findViewById(R.id.vid);
           model=itemView.findViewById(R.id.availability);
           vehicleCity=itemView.findViewById(R.id.vehiclecity);
            vehicleName=itemView.findViewById(R.id.vehiclename);
            lout=itemView.findViewById(R.id.vehlist);
            imageView  = itemView.findViewById(R.id.imageView1);
        }
    }
}

