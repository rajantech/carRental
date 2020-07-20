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
import com.example.carrentalprototype.pojo.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class adapteruserlist extends RecyclerView.Adapter<adapteruserlist.ViewHolder>{
    Context context;
    List<pojoUser> ar1;
    public adapteruserlist(Context context,List<pojoUser> ar1) {

        this.context=context;
        this.ar1=ar1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsuserlist, parent, false);

        ViewHolder holder=new ViewHolder(listItem);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        pojoUser pj=ar1.get(position);

        holder.nm.setText(pj.getFirstName()+" "+pj.getLastName());
        holder.eml.setText(pj.getEmail());
        holder.loc.setText(pj.getCity());
        holder.uid.setText(pj.getUserId());

        FirebaseStorage firebaseStorage2;
        StorageReference storageReference2;

        firebaseStorage2 = FirebaseStorage.getInstance();
        storageReference2 = firebaseStorage2.getReference();

        StorageReference imageRef2 = storageReference2.child("Images/"+pj.getProfilePic());

        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //  Picasso.get().load(uri).into(holder.);

                Glide.with(context)
                        .load(uri)
                        .circleCrop()
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
                Intent intent=new Intent(context,adminViewUser.class);
                intent.putExtra("userid",holder.uid.getText().toString());
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
        LinearLayout lout;
        TextView uid;
        TextView nm,eml,loc;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nm=itemView.findViewById(R.id.name);
            eml=itemView.findViewById(R.id.email);
            loc=itemView.findViewById(R.id.location);
            uid=itemView.findViewById(R.id.usid);
            lout=itemView.findViewById(R.id.ulist);
            imageView  = itemView.findViewById(R.id.imageView1);
        }
    }
}

