package com.snacourse.xmpp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by a on 8/27/2017.
 */

public class Contact implements Parcelable {
    private String jid;
    private String name;
    private int  status;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(jid);
        parcel.writeString(name);
        parcel.writeInt(status);
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Contact(Parcel in) {
        jid = in.readString();
        name = in.readString();
        status = in.readInt();
    }

    public Contact() {
    }
}
