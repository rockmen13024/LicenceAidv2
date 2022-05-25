package dao;

public class Account {
    public int AccountID;
    public String LastName;
    public String FirstName;
    public String Email;
    public String Password;
    public String AccountType;


    public Account(String LastName, String FirstName, String Email, String Password, String AccountType){
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Email = Email;
        this.Password = Password;
        this.AccountType = AccountType;
    }
}
