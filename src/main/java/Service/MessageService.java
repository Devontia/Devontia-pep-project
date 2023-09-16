package Service;
import DAO.MessagesDAO;

import Util.ConnectionUtil;
import Model.Message;

import static org.mockito.ArgumentMatchers.matches;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageService {
    public MessagesDAO messageDAO;

   
    

    public MessageService(){
        messageDAO = new MessagesDAO();
    }

    public MessageService(MessagesDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message){
        return messageDAO.insertMessage(message);

    }
  public Message deleteMessage(int id){
    if( messageDAO.deleteMessage(id)){
        return messageDAO.getMessageByID(id);
    }else{
        return null;
    }
  }

  public Message getMessageViaID(int ID){
    return messageDAO.getMessageByID(ID);
  }

  public Message updateMessage(String mess, int id){
    boolean plsgod = messageDAO.getMessageByID(id)!=null;
    if(!mess.isEmpty() && mess.length() < 255 &&  messageDAO.updateMessage(mess, id)  && plsgod ){
      return messageDAO.getMessageByID(id);
    }else{
      return null;
    }
    
  }

  public List<Message> getUseraMessage(int postby){
    return messageDAO.getUseraMessage(postby);
  }
    
}
