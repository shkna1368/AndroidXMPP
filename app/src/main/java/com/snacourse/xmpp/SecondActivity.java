package com.snacourse.xmpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.snacourse.xmpp.Adapter.ChatAdapter;
import com.snacourse.xmpp.model.Contact;
import com.snacourse.xmpp.model.XmppEvent;
import com.snacourse.xmpp.model.XmppMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jivesoftware.smack.AbstractXMPPConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecondActivity extends AppCompatActivity  {
Button btnSend;
    AbstractXMPPConnection conn1;
    TextView edtHistory;
    EditText edtNow;
   XmppHelper xmppHelper;
    Contact contact;
    List<XmppMessage> xmppMessageList;
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSend= (Button) findViewById(R.id.button);
      //  edtHistory=findViewById(R.id.editTextHistory);
        edtNow= (EditText) findViewById(R.id.editTextNowText);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        EventBus.getDefault().register(this);

       xmppHelper=XmppHelper.getInstance();
     //  contact= (Contact) getIntent().getSerializableExtra("contact");
xmppMessageList=new ArrayList<>();

        contact  = (Contact) getIntent().getParcelableExtra("contact");



        /*String meId=xmppHelper.getMyAccount();
        xmppHelper.sendMessage(contact.getJid(),"salam bar to");*/

        // MyLoginTask task = new MyLoginTask();
       // task.execute("");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String meId=xmppHelper.getMyAccount();
                xmppHelper.sendMessage(contact.getJid(),edtNow.getText().toString());
                XmppMessage xmppMessage=new XmppMessage();
                xmppMessage.setBody(edtNow.getText().toString());
              String  currentDateandTime=  getDate();

                xmppMessage.setDate(currentDateandTime);
           //     xmppMessageList.add(xmppMessage);

                   // String newMsg=edtNow.getText().toString();
                //    String allMsg=edtHistory.getText().toString();
                  //  edtHistory.setText(allMsg+'\n'+newMsg);

                    edtNow.setText("");


                if(xmppMessageList.size()>0){
                    addRow(xmppMessage);
                }
                else{
                    setAdapter(xmppMessage);
                }
             /*   if(xmppMessageList.size()>1){
                    addRow(xmppMessage);}
                else{
                setAdapter(xmppMessage);
                }*/

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //  EventBus.getDefault().unregister(this);
    }

private  String getDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String currentDateandTime = sdf.format(new Date());
    return currentDateandTime;
}



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessagereceived(final XmppEvent.XmppMessageEvent xmppMessageEvent) {
        Log.e("in second ",xmppMessageEvent.toString());
     final XmppMessage message=   xmppMessageEvent.getXmppMessage();
        message.setDate(getDate());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(xmppMessageList.size()>0){
                    addRow(message);
                }
                else{
                    setAdapter(message);
                }

              //  addRow( xmppMessageEvent.getXmppMessage());
            }
        });

    }



    private void setAdapter(XmppMessage xmppMessage){

     xmppMessageList.add(xmppMessage);
         chatAdapter=new ChatAdapter(xmppMessageList);
       recyclerView.setAdapter(chatAdapter);
    }

      private void addRow(XmppMessage xmppMessage){
       // xmppMessageList.add(xmppMessage);

          chatAdapter.addItem(xmppMessage);
          recyclerView.smoothScrollToPosition(xmppMessageList.size()-1);
//chatAdapter.notifyDataSetChanged();
        //  ChatAdapter chatAdapter=new ChatAdapter(xmppMessageList);
     //  recyclerView.setAdapter(chatAdapter);
    }




    private class MyLoginTask extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... params) {
          //  XmppHelper xmppHelper=new XmppHelper(SecondActivity.this,SecondActivity.this);
          //  xmppHelper.login();
            // Create a connection to the jabber.org server.
          /*  XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("mehdi70", "123456")
                    .setHost("192.168.1.55")
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setServiceName("192.168.1.55")
                    .setPort(5222)
                    .setDebuggerEnabled(true) // to view what's happening in detail
                    .build();

             conn1 = new XMPPTCPConnection(config);
            try {
                conn1.connect();
                if(conn1.isConnected())
                {
                    Log.w("app", "conn done");
                    Log.e("app", "conn done");
                }
                conn1.login();
                if(conn1.isAuthenticated())
                {
                    Log.w("app", "Auth done");
                    Log.e("app", "Auth done");
                    ChatManager chatManager = ChatManager.getInstanceFor(conn1);
                    chatManager.addChatListener(
                            new ChatManagerListener() {
                                @Override
                                public void chatCreated(Chat chat, boolean createdLocally)
                                {
                                  SecondActivity.this.chat=  chat;

                                    chat.addMessageListener(new ChatMessageListener()
                                    {
                                        @Override
                                        public void processMessage(Chat chat, final Message message) {
                                    *//*        System.out.println("Received message: "
                                                    + (message != null ? message.getBody() : "NULL"));*//*

try {
    if(null!=message.getBody()) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String str=edtHistory.getText().toString()+ "\n"+message.getBody();

                edtHistory.setText(str);
                //Your code to run in GUI thread here
            }//public void run() {
        });

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
            }
            catch (Exception e) {
                Log.w("app", e.toString());
                Log.e("app", e.toString());
            }*/

            return "";
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
        }

    }
}
