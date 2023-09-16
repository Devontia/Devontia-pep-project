package Service;
import DAO.AccountDAO;

import Util.ConnectionUtil;
import Model.Account;

import static org.mockito.ArgumentMatchers.matches;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts(){
        return accountDAO.getAllAccounts();
    }
    
    public Account insertAllAccounts(Account account){
        return accountDAO.insertAllAccounts(account);
    }

    public Account verilogin(String userName, String password){
        return accountDAO.verilogin(userName, password);
    }


    
}
