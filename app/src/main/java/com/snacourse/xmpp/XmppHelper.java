package com.snacourse.xmpp;

import android.content.Context;
import android.util.Log;

import com.snacourse.xmpp.model.*;
import com.snacourse.xmpp.model.XmppEvent;
import org.greenrobot.eventbus.EventBus;


import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by a on 8/27/2017.
 */

public class XmppHelper  {

    private static XmppHelper instance;
    List<Contact> contactList=null;


    int contactIndex=0;

    public static XmppHelper getInstance(){
        if(instance == null){
            instance = new XmppHelper();
        }
        return instance;
    }



    EventBus bus;

    AbstractXMPPConnection connection;


    private XmppHelper() {
       /* this.context=context;
        this.event=event;*/



bus=EventBus.getDefault();

   //   bus.post(55555l);
      //  EventBus.getDefault().post(50555555l);
    //    Log.e("bus=",  EventBus.getDefault().toString());
    }


    public String getMyAccount(){
        VCard me = new VCard();
       String myid= me.getJabberId();
        return myid;


    //   String myid= me.getFirstName();
       // me.load(conn);

    }

    public static void setInstance(XmppHelper instance) {
        XmppHelper.instance = instance;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public int getCotactIndex() {
        return contactIndex;
    }

    public void setCotactIndex(int cotactIndex) {
        this.contactIndex = cotactIndex;
    }

    public EventBus getBus() {
        return bus;
    }

    public void setBus(EventBus bus) {
        this.bus = bus;
    }

    public AbstractXMPPConnection getConnection() {
        return connection;
    }

    public void setConnection(AbstractXMPPConnection connection) {
        this.connection = connection;
    }

    private void register(){
        XMPPTCPConnectionConfiguration connConfig =
                XMPPTCPConnectionConfiguration.builder()
                        .setHost("192.168.1.55")  // Name of your Host
                        .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                        .setPort(5222)          // Your Port for accepting c2s connection
                        .setDebuggerEnabled(true)
                        .setServiceName("localhost")
                        .build();
         connection = new XMPPTCPConnection(connConfig);

        try {
            // connecting...
            connection.connect();
            Log.i("TAG", "Connected to " + connection.getHost());

            // Registering the user
            AccountManager accountManager = AccountManager.getInstance(connection);
            accountManager.sensitiveOperationOverInsecureConnection(true);
            accountManager.createAccount("farhadgsm", "123456");   // Skipping optional fields like email, first name, last name, etc..
            Log.e("Done Regidter", "Doneeee" +
                    "");
        } catch (SmackException | IOException | XMPPException e) {
            Log.e("TAG", e.getMessage());
            Log.e("ERROR", e.toString());
            // xmppClient.setConnection(null);
        }

    }



public void login(){

    XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
            .setUsernameAndPassword("shabab", "123456")
            .setHost("192.168.1.5")
            .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
            .setServiceName("localhost")
            .setPort(5222)
            .setDebuggerEnabled(true) // to view what's happening in detail
            .build();

    connection = new XMPPTCPConnection(config);
    try {
        connection.connect();
        if(connection.isConnected())
        {
            Log.w("app", "conn done");
            Log.e("app", "conn done");
        }
        connection.login();
        if(connection.isAuthenticated())
        {

            Log.w("app", "Auth done");
            Log.e("app", "Auth done");


        bus.post(  XmppEvent.getInstance().getLoginInstance(true));


            //          bus.post( 5);
          //  event.onSuccessLogin();
Log.e("post in service","dgf");
    // searchAlluser();
//addNew("shkna1368@localhost","shabab");
      getAllContacts();

        }
    }
    catch (Exception e) {
        EventBus.getDefault().post(  XmppEvent.getInstance().getLoginInstance(false));
        Log.w("app", e.toString());
        Log.e("app", e.toString());
    }
}


private Contact searchInContact(String user){
   // contact.setName(entry.getName());
    //contact.setJid(entry.getUser());
    int i=0;
    for (Contact contact:contactList){

        if( contact.getJid().equals(user)){
contactIndex=i;
            return  contact;
        }

i++;
    }
    return null;
}


private void eventListenert(final Roster roster){
    roster.addRosterListener(new RosterListener() {
        // Ignored events public void entriesAdded(Collection<String> addresses) {}
        public void entriesDeleted(Collection<String> addresses) {

        }
        public void entriesUpdated(Collection<String> addresses) {

        }
        public void presenceChanged(Presence presence) {


            roster.getPresence(presence.getFrom());
                 boolean ex=   roster.contains(presence.getFrom());
RosterEntry entry=roster.getEntry(presence.getFrom());
            Log.e("user exist=",ex+"");
presence.getStatus();
      /*      Contact contact=new Contact();
            contact.setName(entry.getName());
            contact.setJid(entry.getUser());

            String status=presence.getStatus();
            if (status.equals("available")){
                contact.setStatus(R.drawable.avail);}
            else{
                contact.setStatus(R.drawable.noavail);
            }*/
            Contact contact1=   searchInContact(entry.getUser() );

           /* Presence entryPresence = roster.getPresence(entry.getUser());
            Presence.Type type = entryPresence.getType();

            String status=type.toString();*/
        String status=   presence.getType().toString();
            if (status.equals("available")){
                contact1.setStatus(R.drawable.avail);}
            else{
                contact1.setStatus(R.drawable.noavail);
            }



bus.post(XmppEvent.getInstance().getPrecense(contact1,contactIndex));
            Log.e("precebse=",presence.getFrom()+"-"+presence+"---"+presence.getFrom());

            System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
        }
        @Override
        public void entriesAdded(Collection<String> arg0) {
            // TODO Auto-generated method stub

        }
    });

}


public void getAllContacts() throws SmackException.NotConnectedException, InterruptedException {
    Thread.sleep(3000);

    Roster roster = Roster.getInstanceFor(connection);
    if (!roster.isLoaded())
        try {
            roster.reloadAndWait();
        } catch (SmackException.NotLoggedInException e) {
            Log.e("err,", e.toString());
            e.printStackTrace();
        }


    eventListenert  (roster)  ;
    setReciver();

    Collection<RosterEntry> entries = roster.getEntries();
    Log.e("Users in roster size ", entries.size()+"");
contactList=new ArrayList<>(entries.size());
    for (RosterEntry entry : entries) {
        Presence entryPresence = roster.getPresence(entry.getUser());
        Presence.Type type = entryPresence.getType();
        Log.e("Type",type.toString());
        //   presence = roster.getPresence(entry.getUser());
       Log.e("Users in roster ", entry.getUser());
     Log.e("name in roster ", entry.getName());
     Log.e("status in roster ",type.toString());
Contact contact=new Contact();
        contact.setName(entry.getName());
        contact.setJid(entry.getUser());
        String status=type.toString();
        if (status.equals("available")){
            contact.setStatus(R.drawable.avail);}
        else{
            contact.setStatus(R.drawable.noavail);
        }


        contactList.add(contact);


      //  Log.e("Name of user in roster ", presence.getType().name());
       // Log.e("Status in roster", presence.getStatus());
    }
   bus.post( XmppEvent.getInstance().getContactsInstance(contactList));
   //bus.post( contactList);

}





private void setReciver(){
    ChatManager chatManager = ChatManager.getInstanceFor(connection);


    chatManager.addChatListener(
            new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally)
                {


                    chat.addMessageListener(new ChatMessageListener()
                    {
                        @Override
                        public void processMessage(Chat chat, final Message message) {
                                       System.out.println("Received message: "
                            + (message != null ? message.getBody() : "NULL"));

                            try {
                                if(null!=message.getBody()) {

                                    String fromName = message.getFrom();

                                    String msg=message.getBody();
                                    XmppMessage xmppMessage=new XmppMessage();
                                    xmppMessage.setFrom(fromName);
                                    xmppMessage.setBody(msg);
                                    Log.e("message received=",msg);
                                bus.post( XmppEvent.getInstance().getXmppMessageInstance(xmppMessage));
//event.onMessageReceived(fromName,msg);
                                }
                            }
                            catch (Exception e){
                                Log.e("Exception", e.toString());

                            }


                        }


                    });

                    Log.w("app", chat.toString());
                    Log.e("app", chat.toString());
                }
            });



}


    public void sendMessage(String to,String mesage) {
        ChatManager chatManager = ChatManager.getInstanceFor(connection);

Chat chat=chatManager.createChat(to);

        Message msg = new Message();
       // msg.setSubject("Any subject you want");
        msg.setBody(mesage);
// Create a XHTMLExtension Package and add it to the message


// User1 sends the message that contains the XHTML to user2
        try {
            chat.sendMessage(msg);
           // Thread.sleep(200);
        }
        catch (Exception e) {
            Log.e("An error occ XHTML",e.toString());
        }
    }












      /*  chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean createdLocally) {
                chat.
            }
        });
*/

/*
        chatManager.
        Message message = new Message(to, Message.Type.chat);
        message.setFrom(from);
        message.setBody(mesage);
        message.addExtension(new DefaultExtensionElement("from",from));
        message.addExtension(new DefaultExtensionElement("to", to));
        try {
            connection.sendStanza(message);
        } catch (SmackException.NotConnectedException e) {
            Log.e("eeror",e.toString());
            e.printStackTrace();
        }*/











 /*   public void chat(){
    ChatManager chatManager = ChatManager.getInstanceFor(connection);
    chatManager.addChatListener(
            new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally)
                {
                   // SecondActivity.this.chat=  chat;

                    chat.addMessageListener(new ChatMessageListener()
                    {
                        @Override
                        public void processMessage(Chat chat, final Message message) {
                                //    *//**//*        System.out.println("Received message: "
                           // + (message != null ? message.getBody() : "NULL"));*//**//*

                            try {
                                if(null!=message.getBody()) {
                                    event.onMessageReceived(message.getBody());

                                 *//*   runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String str=edtHistory.getText().toString()+ "\n"+message.getBody();

                                            edtHistory.setText(str);
                                            //Your code to run in GUI thread here
                                        }//public void run() {
                                    });*//*

                                }
                            }
                            catch (Exception e){
                                Log.e("Exception", e.toString());

                            }


                        }


                    });

                    Log.w("app", chat.toString());
                    Log.e("app", chat.toString());
                }
            });
}

*/



private void addNew(String jid,String user){
    Roster roster = Roster.getInstanceFor(connection);

  //  Roster roster = xmppConnection.getRoster();
    // jid: String, user: String, groups: String[]
    try {
        roster.createEntry(jid, user, null);
        Log.e("done add","done");

    } catch (SmackException.NotLoggedInException e) {
        Log.e("NotLoggedInException",e.toString());
        e.printStackTrace();
    } catch (SmackException.NoResponseException e) {
        Log.e("NoResponseException",e.toString());
        e.printStackTrace();
    } catch (XMPPException.XMPPErrorException e) {
        Log.e("XMPPErrorException",e.toString());
        e.printStackTrace();
    } catch (SmackException.NotConnectedException e) {
        Log.e("NotConnectedException",e.toString());
        e.printStackTrace();
    }
}
    private void searchAlluser() throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        UserSearchManager usm = new UserSearchManager(connection);

        Form searchForm = usm.getSearchForm("search." + connection.getServiceName());
//Here i am searching for the service name

        Form answerForm = searchForm.createAnswerForm();

        UserSearch userSearch = new UserSearch();
        answerForm.setAnswer("Username", true);

        answerForm.setAnswer("search", "*");

        ReportedData data = userSearch.sendSearchForm(connection, answerForm, "search." + connection.getServiceName());
        if (data.getRows() != null) {
            System.out.println("not null");

            List<ReportedData.Row> rows=  data.getRows();

         //   arryAllUsers.clear();

            for (ReportedData.Row row:rows){

                //System.out.println("row");

             //   ReportedData.Row row = it.next();

               List<String> jids= row.getValues("jid");
               List<String> names= row.getValues("Name");
int length=jids.size();
                for (int i=0;i<length ;i++) {

String jid=(jids.get(i));
String name=(names.get(i));


                    Log.e("jid=",jid);
                    Log.e("name=",name);

                    // l.add(value);

                }

                // Log.i("Iteartor values......"," "+value);

            }
        }
    }
}
