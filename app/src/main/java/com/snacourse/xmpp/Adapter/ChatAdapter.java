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
import com.snacourse.xmpp.model.XmppMessage;
import com.snacourse.xmpp.viewholder.ChatViewHolder;
import com.snacourse.xmpp.viewholder.ContactViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sh-Java on 12/10/2016.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder>  {
    private List<XmppMessage> xmppMessageList;

    Context ctx;
  int  malekId;
    ContactTap contactTap;
   // private EventBus bus = EventBus.getDefault();

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_row, parent, false);
 ctx=view.getContext();


        ChatViewHolder myViewHolder = new ChatViewHolder(view);
        return myViewHolder;
    }




    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        TextView textChat = holder.textChat;
        TextView textDate = holder.textDate;




  final XmppMessage xmppMsg=xmppMessageList.get(position);
        String msgBody=xmppMsg.getBody();
        textChat.setText(msgBody);


        textDate.setText(xmppMsg.getDate());


        Log.e("dt=",xmppMsg.getDate());






    }
    public void addItem( XmppMessage xmppMessage){
  xmppMessageList.add(xmppMessage);
        notifyItemInserted(xmppMessageList.size() - 1);
       // notifyItemChanged(position,xmppMessage);;
        //  contactList.add(position, contact);
        //notifyItemInserted(position);
        // notifyItemRangeChanged(position, contactList.size());
    }


    public ChatAdapter(List<XmppMessage> xmppMessages) {
        this.xmppMessageList = xmppMessages;

        this.contactTap=contactTap;
    }

    @Override
    public int getItemCount() {
        return xmppMessageList.size();
    }



}
