package com.snacourse.xmpp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.snacourse.xmpp.R;


public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView textChat;
    public TextView textDate;

    View itemView;

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public ChatViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.textChat = (TextView) itemView.findViewById(R.id.textView);
        this.textDate = (TextView) itemView.findViewById(R.id.textDate);





    }


}
