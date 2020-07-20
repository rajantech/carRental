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
import com.example.carrentalprototype.admin.adminViewBooking;
import com.example.carrentalprototype.pojo.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterquerylist extends RecyclerView.Adapter<adapterquerylist.ViewHolder>{
    List<pojoQuery> ar1;
    Context context;
    public adapterquerylist(Context context,List<pojoQuery> ar1) {
        this.context=context;
        this.ar1 = ar1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsquerylist, parent, false);
        ViewHolder holder=new ViewHolder(listItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        pojoQuery pj=ar1.get(position);

        holder.username.setText(pj.getUserName()+" "+pj.getUserLastName());
        holder.message.setText(pj.getMessage());
        holder.id.setText(pj.getQueryId());
        if(pj.getVisit().equals("Unvisited"))
        {
            holder.visit.setText("New");
        }
        else{
            holder.visit.setText("");
        }

        FirebaseStorage firebaseStorage2;
        StorageReference storageReference2;

        firebaseStorage2 = FirebaseStorage.getInstance();
        storageReference2 = firebaseStorage2.getReference();

        StorageReference imageRef2 = storageReference2.child("Images/"+pj.getUserProfilePic());

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
        //Glide.with(context).load(pj.getUserProfilePic()).into(holder.imageView);

        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,adminViewQuery.class);
                intent.putExtra("queryid",holder.id.getText().toString());
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
        TextView id;
        TextView username;
        TextView message;
        ImageView imageView;
        LinearLayout lout;
        TextView visit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.qid);
            username= itemView.findViewById(R.id.uName);
            message=itemView.findViewById(R.id.queryMessage);
            lout=itemView.findViewById(R.id.querylist);
            imageView = itemView.findViewById(R.id.imageView);
            visit= itemView.findViewById(R.id.visit);
        }
    }
}

