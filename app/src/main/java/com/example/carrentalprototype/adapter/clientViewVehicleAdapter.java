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
import com.example.carrentalprototype.admin.adminViewVehicle;
import com.example.carrentalprototype.pojo.pojoVehicle;
import com.example.carrentalprototype.*;
import com.example.carrentalprototype.pojo.*;
import com.example.carrentalprototype.ui.home.ClientHomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class clientViewVehicleAdapter extends RecyclerView.Adapter<clientViewVehicleAdapter.ViewHolder> {
    Context context;
    List<pojoVehicle> ar1;
    String dateBefore;
    String dateAfter;
    float daysBetween;
    String vehid;
    long difference;
    String dyRate;
    String ptime,dtime;
    String datapickup,datadropoff;
    int dayRate;
//  ArrayList<String> ar2;
    //  ArrayList<String> ar3;


    public clientViewVehicleAdapter(Context context, List<pojoVehicle> ar1,String dateBefore,String dateAfter,String datapickup,String datadropoff,String vehid,String ptime,String dtime) {
        this.context=context;
        this.ar1=ar1;
        this.dateBefore=dateBefore;
        this.datapickup=datapickup;
        this.datadropoff=datadropoff;
        this.dateAfter=dateAfter;
        this.vehid=vehid;
        this.ptime=ptime;
        this.dtime=dtime;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itemsclientvehiclelist, parent, false);
        ViewHolder holder = new ViewHolder(listItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final pojoVehicle pj=ar1.get(position);
        String aa=pj.getTitle();
        // Toast.makeText(context,aa,Toast.LENGTH_SHORT).show();
        holder.title.setText(pj.getTitle()+" "+pj.getModel());
        holder.clas.setText(pj.getClas());
        holder.milage.setText("MILEAGE PER LITER : "+pj.getMillage());
        holder.seats.setText(pj.getSeats());
        holder.doors.setText(pj.getDoors());
        holder.id.setText(pj.getVehicleId());
        int prc=Integer.parseInt(pj.getHourRate());

        FirebaseStorage firebaseStorage2;
        StorageReference storageReference2;

        firebaseStorage2 = FirebaseStorage.getInstance();
        storageReference2 = firebaseStorage2.getReference();

        StorageReference imageRef2 = storageReference2.child("Images/"+pj.getVehicleImage());

        imageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Picasso.get().load(uri).into(userpic);
                Glide.with(context.getApplicationContext()).load(uri).into(holder.imgvw);


                // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Toast.makeText(getContext(),"fail.",Toast.LENGTH_SHORT).show();
            }
        });


        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        try {
            Date dateAfte = myFormat.parse(dateAfter);
            Date dateBefo = myFormat.parse(dateBefore);
            difference = dateAfte.getTime() - dateBefo.getTime();
            daysBetween = (difference / (1000*60*60*24));
            if(daysBetween==0)
            {
                dayRate= (int) (1*prc);
            }
            else if (daysBetween<0)
            {
                Intent intent=new Intent(context.getApplicationContext(),MainActivity.class);
                context.startActivity(intent);
                Toast.makeText(context.getApplicationContext(),"Please Enter Valid Journey Period",Toast.LENGTH_LONG).show();
            }
            else
            {
                dayRate= (int) (daysBetween*prc);
            }

            dyRate=String.valueOf(dayRate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.total.setText(dyRate);






        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,"lelo : "+ holder.id.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,clientBookingDetails.class);
                intent.putExtra("vehicleid",holder.id.getText().toString());
                intent.putExtra("rate",holder.total.getText().toString());
                intent.putExtra("datepickup",dateBefore);
                intent.putExtra("datedropoff",dateAfter);
                intent.putExtra("adresspickup",datapickup);
                intent.putExtra("adressdropoff",datadropoff);
                intent.putExtra("picktime",ptime);
                intent.putExtra("droptime",dtime);
                intent.putExtra("imagevehicle",holder.imgvw.toString());
                //pojoBooking.vehicleId = Integer.parseInt(holder.id.getText().toString());
                context.startActivity(intent);
            }
        });

        //  Toast.makeText(context,"onbind",Toast.LENGTH_SHORT).show();
   /*  holder.lout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context,clientViewBooking.class);
             // intent.putExtra("vehicleid",holder.id.getText().toString());
              context.startActivity(intent);

          }
      });
*/


    }

    @Override
    public int getItemCount()
    {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // ImageView img;
        TextView title;
        TextView clas;
        TextView milage;
        TextView seats;
        TextView doors;
        TextView id;
        ImageView imgvw;
        TextView total;
        LinearLayout lout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lout = itemView.findViewById(R.id.clientvehicllist);

            clas=itemView.findViewById(R.id.vehicleClass);
            title=itemView.findViewById(R.id.title);
            total=itemView.findViewById(R.id.total);
            milage=itemView.findViewById(R.id.Millage);
            seats =itemView.findViewById(R.id.seats);
            doors=itemView.findViewById(R.id.doors);
            id =itemView.findViewById(R.id.id);
            imgvw=itemView.findViewById(R.id.imgcar);

//            lout=itemView.findViewById(R.id.vehlist);

        }
    }
}





