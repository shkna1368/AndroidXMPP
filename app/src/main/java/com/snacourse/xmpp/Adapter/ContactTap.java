package com.snacourse.xmpp.Adapter;

import com.snacourse.xmpp.model.Contact;


/**
 * Created by a on 8/24/2017.
 */

public interface ContactTap {

    public void onItemTap(Contact ftpFile);
    public void onLongItemClick(Contact ftpFile);
}
