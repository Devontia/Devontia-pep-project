package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessagesDAO {


    /**
     * TODO: retrieve all authors from the Author table.
     * You only need to change the sql String.
     * @return all Authors.
     */
    public List<Message> getAllMessages(){


        Connection connection = ConnectionUtil.getConnection();
        List<Message> message = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "select * from Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message mess = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
                message.add(mess);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return message;
    }

    /**
     * TODO: insert an author into the Author table.
     * The author_id should be automatically generated by the sql database if it is not provided because it was
     * set to auto_increment. Therefore, you only need to insert a record with a single column (name).
     * You only need to change the sql String and leverage PreparedStatements' setString methods.
     */
    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {

//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "insert into Message  (posted_by,message_text, time_posted_epoch) values (?,?,?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setInt(1,message.getPosted_by());
            preparedStatement.setString(2,message.getMessage_text());
            preparedStatement.setLong(3,message.getTime_posted_epoch());
            
            

            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(message.getMessage_text().length() < 255 && message.getMessage_text() != "" ){
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());
            }
        }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
 /**
     * TODO: retrieve all authors from the Author table.
     * You only need to change the sql String.
     * @return all Authors.
     */
    public Message getMessageByID(int ID){


        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "select * from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return new Message(
                rs.getInt("message_id"),
                rs.getInt("posted_by"), 
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: retrieve all authors from the Author table.
     * You only need to change the sql String.
     * @return all Authors.
     */
    public boolean deleteMessage(int ID){


        Connection connection = ConnectionUtil.getConnection();
        /*try {
            //Write SQL logic here
            String sql = "delete * from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            int update = preparedStatement.executeUpdate();
           if(update != 0){

            return true;

           }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
        */
        try {
            //Write SQL logic here
            String sql = "delete message set message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setInt(1,ID);

            int row = preparedStatement.executeUpdate();
            if(row != 0){
                return true;
            }
           
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateMessage(String mess, int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "update Message set message_text=? where message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setString(1,mess);
            preparedStatement.setInt(2,id);

            int row = preparedStatement.executeUpdate();
            if(row != 0){
                return true;
            }
           
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Message> getUseraMessage(int postby){


        Connection connection = ConnectionUtil.getConnection();
        List<Message> message = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "select * from message where posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,postby);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message mess = new Message(rs.getInt("message_id"),
                postby,
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
                message.add(mess);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return message;
    }

}
