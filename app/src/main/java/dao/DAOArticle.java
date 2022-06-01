package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbcomplete.DBComplete;

public class DAOArticle implements  DAO<Article>{
    Connection conn;
    public Variable overallVariable = new Variable();

    public DAOArticle(){
        conn = DBComplete.getConn();
    }

    @Override
    public Optional<Article> get(long id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Article> getAll(){
        List<Article> list = new ArrayList<>();
        try{
            Statement stm = conn.createStatement();

            String sql = "select * from articles";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                Article a = new Article(rs.getString("Name"), rs.getString("FilePath"), rs.getString("Author"), rs.getString("Date"));
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
    public void save(Article a) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Article a, String[] param){
        //TODO;
    }

    public void delete(Article a){
        //TODO;
    }

}
