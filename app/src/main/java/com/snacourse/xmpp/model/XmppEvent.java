package com.snacourse.xmpp.model;

import java.util.List;

/**
 * Created by a on 8/28/2017.
 */

public class XmppEvent {
    private static XmppEvent instance;

    private XmppEvent(){}

    public static XmppEvent getInstance(){
        if(instance == null){
            instance = new XmppEvent();
        }
        return instance;
    }



    public LoginEvent getLoginInstance(boolean isLogin){
        return new LoginEvent(isLogin);
    }



    public RegisterEvent getRegisterInstance(boolean isRegister){
        return new RegisterEvent(isRegister);
    }

      public ContactEvent getContactsInstance(List<Contact> contactList){
        return new ContactEvent(contactList);
    }


  public XmppMessageEvent getXmppMessageInstance(XmppMessage xmppMessage){
        return new XmppMessageEvent(xmppMessage);
    }


  public UserPresence getPrecense(Contact contact,int index){
      return new UserPresence(contact,index);
    }








    public   class RegisterEvent  {
        boolean register;

        public boolean isRegister() {
            return register;
        }

        public void setRegister(boolean register) {
            this.register = register;
        }

        public RegisterEvent(boolean register) {
            this.register = register;
        }
    }




    public   class UserPresence  {
Contact contact;
        int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public UserPresence(Contact contact,int index) {
            this.contact = contact;
          this.  index=index;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;

        }
    }






    public  class LoginEvent{
           boolean logins;

        public boolean isLogins() {
            return logins;
        }

        public void setLogins(boolean logins) {
            this.logins = logins;
        }

        public LoginEvent(boolean logins) {
            this.logins = logins;
        }
    }


    public  class ContactEvent{

        public List<Contact> getContactList() {
            return contactList;
        }

        public void setContactList(List<Contact> contactList) {
            this.contactList = contactList;
        }

        List<Contact> contactList;

        public ContactEvent(List<Contact> contactList) {
            this.contactList = contactList;
        }
    }

    public  class XmppMessageEvent{
        private XmppMessage xmppMessage;

        public XmppMessage getXmppMessage() {
            return xmppMessage;
        }

        public void setXmppMessage(XmppMessage xmppMessage) {
            this.xmppMessage = xmppMessage;
        }

        public XmppMessageEvent(XmppMessage xmppMessage) {
            this.xmppMessage = xmppMessage;
        }
    }




}
