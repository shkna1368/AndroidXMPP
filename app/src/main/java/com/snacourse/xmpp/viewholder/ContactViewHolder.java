package com.snacourse.xmpp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.snacourse.xmpp.R;


public class ContactViewHolder extends RecyclerView.ViewHolder {

    public TextView textFileName;
    public ImageView imageStatus;
    View itemView;

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public ContactViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.textFileName = (TextView) itemView.findViewById(R.id.textView);
        this.imageStatus = (ImageView) itemView.findViewById(R.id.imageView);





    }


}
