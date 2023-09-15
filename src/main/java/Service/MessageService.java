package Service;
import DAO.MessagesDAO;;

import Util.ConnectionUtil;
import Model.Message;

import static org.mockito.ArgumentMatchers.matches;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageService {
    private MessagesDAO messageDAO;



    public MessageService(){
        messageDAO = new MessagesDAO();
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message){
        return messageDAO.insertMessage(message);
    }

    
}
