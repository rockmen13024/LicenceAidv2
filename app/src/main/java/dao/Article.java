package dao;

public class Article {
    public int ArticleID;
    public String Name;
    public String FilePath;
    public String Author;
    public String Date;

    public Article(String Name, String FilePath, String Author, String Date){
        this.Name = Name;
        this.FilePath = FilePath;
        this.Author = Author;
        this.Date = Date;
    }
}
