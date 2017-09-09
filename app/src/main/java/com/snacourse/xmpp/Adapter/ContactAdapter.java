package com.snacourse.xmpp.Adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.snacourse.xmpp.R;
import com.snacourse.xmpp.model.Contact;
import com.snacourse.xmpp.viewholder.ContactViewHolder;

import java.util.List;

/**
 * Created by Sh-Java on 12/10/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>  {
    private List<Contact> contactList;

    Context ctx;
  int  malekId;
    ContactTap contactTap;
   // private EventBus bus = EventBus.getDefault();

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);
 ctx=view.getContext();


        ContactViewHolder myViewHolder = new ContactViewHolder(view);
        return myViewHolder;
    }


    public void addItem(int position, Contact contact){
        notifyItemChanged(position,contact);;
      //  contactList.add(position, contact);
        //notifyItemInserted(position);
       // notifyItemRangeChanged(position, contactList.size());
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        TextView textName = holder.textFileName;

        ImageView imgStatus = holder.imageStatus;


  final Contact selectedFtpFile=contactList.get(position);
        String fileName=selectedFtpFile.getName();
        textName.setText(fileName);
imgStatus.setImageResource(selectedFtpFile.getStatus());

/*
String format =getType(fileName);



        if (format.equals("mp3")||format.equals("MP3")){
            imgType.setImageResource(R.drawable.mpthree);
        }

        else if (format.equals("png")||format.equals("PNG")||format.equals("jpg")||format.equals("jpeg")){
            imgType.setImageResource(R.drawable.pic);
        }*/



        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     bus.post(malekTo);
contactTap.onItemTap(selectedFtpFile);

           /*     Intent i=new Intent(ctx,AddMalekActivity.class);
                i.putExtra("melkId",selectedMalek.getMelkId());
                ctx.startActivity(i);*/

            }
        });








    }

    public ContactAdapter(List<Contact> contacts, ContactTap contactTap) {
        this.contactList = contacts;

        this.contactTap=contactTap;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



}
