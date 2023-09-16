package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    AccountService accountService;
    MessageService messageService;

     public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
       
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages",this::getMessagesHandler);
        app.get( "/messages/{message_id}", this::getMessageID);
        app.delete( "/messages/{message_id}", this::deleteMessageID);
        app.patch( "/messages/{message_id}", this::patchMessageID);
        app.get("/accounts/{account_id}/messages",this::getaccountdHandler);



        return app;
    }

    /**
     * this is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    private void postRegisterHandler(Context ctx) throws JsonProcessingException { 
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedaccount = accountService.insertAllAccounts(account);
        if(addedaccount!=null){
            ctx.json(addedaccount);
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }


    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(),  Account.class);
        Account tester = accountService.verilogin(account.username, account.password);
        if(tester!=null){
            ctx.json(tester);
            ctx.status(200);
        }else{
            ctx.status(401);
        }
    }

    private void postMessagesHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(),  Message.class);
        Message tester = messageService.addMessage(message);
        if(tester!=null){
            ctx.json(tester);
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    } 
    
    private void getMessagesHandler(Context ctx) throws JsonProcessingException {
        List<Message> tester = messageService.getAllMessages();
            ctx.json(tester);
            ctx.status(200);
    


    } 
    //Message addedaccount = messageService.getMessageViaID(message.message_id);
    
    private void getMessageID(Context ctx) {
        int yoMama = Integer.parseInt(ctx.pathParam("message_id"));
        Message tester = messageService.getMessageViaID(yoMama);
        System.out.println(tester + "k");
            if(tester != null){
            ctx.json(tester);
            }
            ctx.status(200);
       
    } 
    private void deleteMessageID(Context ctx) {
        int intId = Integer.parseInt(ctx.pathParam("message_id"));
        Message tester = messageService.deleteMessage(intId);
        System.out.println(tester);
        if(tester != null){
            ctx.json(tester);
            }            
            ctx.status(200);
       
    } 

    private void patchMessageID(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(),Message.class);
        int id  = Integer.parseInt(ctx.pathParam("message_id"));
        Message tester = messageService.updateMessage(message.message_text,id);
        System.out.println(tester);
        if(tester!=null){
            ctx.json(tester);
            ctx.status(200);
        }else{
            ctx.status(400);
        }
        
    } 

    

    private void getaccountdHandler(Context ctx) throws JsonProcessingException {
        int id  = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> tester = messageService.getUseraMessage(id);
            ctx.json(tester);   
            ctx.status(200);
    } 



}