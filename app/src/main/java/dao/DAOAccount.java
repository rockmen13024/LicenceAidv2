package dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbcomplete.DBComplete;

public class DAOAccount implements DAO<Account>{
    Connection conn;
    public Variable overallVariable = new Variable();

    public DAOAccount(){
        conn = DBComplete.getConn();
    }

    @Override
    public Optional<Account> get(long id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Account> getAll(){
        List<Account> list = new ArrayList<>();
        try{
            Statement stm = conn.createStatement();

            String sql = "select * from accountdetails";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                Account a = new Account(rs.getString("LastName"), rs.getString("FirstName"), rs.getString("Email"), rs.getString("Password"), rs.getString("AccountType"));
                list.add(a);
            }

            rs.close();
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public void save(Account a) throws Exception {
        try{
            PreparedStatement stmt = conn.prepareStatement("insert into accountdetails(LastName, FirstName, Email, Password, AccountType)" +
                    " values(?,?,?,?,?)");
            stmt.setString(1, a.LastName);
            stmt.setString(2, a.FirstName);
            stmt.setString(3, a.Email);
            stmt.setString(4, a.Password);
            stmt.setString(5, a.AccountType);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            this.error();
        }
    }

    @Override
    public void update(Account a, String[] param){
        //TODO;
    }

    public void delete(Account a){
        //TODO;
    }

    public boolean accountExists(String email){
        try{
            PreparedStatement stmt = conn.prepareStatement("select count(*) as s from accountdetails where Email=?");
            stmt.setString(1, email);

            ResultSet res = stmt.executeQuery();
            if (res.next()){
                if (res.getString(1) == "1")
                    return true;
            }
            return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean accountExists(String email, String pass){
        try{
            PreparedStatement stmt = conn.prepareStatement("select count(*) from accountdetails where (Email=? and Password=?)");
            stmt.setString(1, email);
            stmt.setString(2, pass);

            ResultSet res = stmt.executeQuery();
            Log.w("Ceva", "Empty");
            if (res.next()){
                Log.w("Ceva", res.toString());
                if (res.getString(1).equals("1"))
                    return true;
            }
            stmt.close();
            return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private void error() throws Exception{
        throw new Exception();
    }
}
