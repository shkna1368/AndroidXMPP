package com.snacourse.xmpp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.snacourse.xmpp.Adapter.ContactAdapter;
import com.snacourse.xmpp.Adapter.ContactTap;
import com.snacourse.xmpp.model.*;
import com.snacourse.xmpp.model.XmppEvent;



import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;






import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;

import java.util.List;

public class FirstActivity extends AppCompatActivity implements ContactTap  {
Button btnSend;
    AbstractXMPPConnection conn1;

RecyclerView recyclerView;
XmppHelper xmppHelper;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_tow);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        EventBus.getDefault().register(this);
       //  bus = EventBus.getDefault();

       //  EventBus bus = EventBus.getDefault();
  //  bus.register(this);
        xmppHelper= XmppHelper.getInstance();
      //  bus.register(this);



connectToXMPP();
//getAllContact();


    }


   @Override
   public void onStart() {
//       EventBus.getDefault().register(this);

       super.onStart();
  //   EventBus.getDefault().register(this);
   }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
      //  EventBus.getDefault().unregister(this);
    }
    private  void  connectToXMPP(){

        Thread tr=new Thread(new Runnable() {
            @Override
            public void run() {
              xmppHelper.login();
            }
        });
        tr.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private  void  getAllContact(XmppEvent.ContactEvent contactEvent){

        setAdapter( contactEvent.getContactList());
        Thread tr=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    xmppHelper.getAllContacts();
                } catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        tr.start();
    }


        @Subscribe(threadMode = ThreadMode.MAIN)
         public   void  getData(Long v){
        //    Log.e(" event is login=",loginEvent.isLogins()+"");
        Log.e(" event is lonh=",v+"");}


    //private  void  login(XmppEvent.LoginEvent loginEvent){

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public   void  login(XmppEvent.LoginEvent loginEvent){
    //    Log.e(" event is login=",loginEvent.isLogins()+"");
        Log.e(" event is login=",loginEvent.isLogins()+"");

//        Thread tr=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    xmppHelper.getAllContacts();
//                } catch (SmackException.NotConnectedException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        tr.start();
    }








   /* @Override
    public void onSuccessGetContact(final List<Contact> contactList) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAdapter( contactList);
            }
        });

    }*/

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetContact(final XmppEvent.ContactEvent contactEvent) {
        Log.e("noteee",contactEvent.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAdapter( contactEvent.getContactList());
            }
        });

    }


     @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onUserStatusChange(final XmppEvent.UserPresence userPresence ) {

         Log.e("noteee",userPresence.getContact().toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // setAdapter( contactEvent.getContactList());
                int index=   userPresence.getIndex();
                Contact  contact=   userPresence.getContact();
                contactAdapter.addItem(index,contact);

            }
        });

    }




    /*@Override
    public void onMessageReceived(String jid, String message) {

    }*/




    private void setAdapter( List<Contact> contactList){
     contactAdapter=new ContactAdapter(contactList,this);
    recyclerView.setAdapter(contactAdapter);
    contactAdapter.notifyDataSetChanged();
}

    @Override
    public void onItemTap(Contact contact) {


        Intent intent=new Intent(this,SecondActivity.class);
       // intent.putExtra("xmppHelper", xmppHelper);
        intent.putExtra("contact", contact);

        startActivity(intent);



       /* String meId=xmppHelper.getMyAccount();
       // xmppHelper.sendMessage(meId,contact.getName(),"salam bar to");
        xmppHelper.sendMessage(contact.getJid(),"salam bar to");
*/



    }

    @Override
    public void onLongItemClick(Contact ftpFile) {

    }
}
