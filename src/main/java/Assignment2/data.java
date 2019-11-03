package Assignment2;

public class data {
    private String login;
    private String  t_Repo;
    private String t_Followers;
    private String t_Following;
    private String githubLink;


    public data(String login, String t_Repo, String t_Followers, String t_Following, String githubLink)
    {
        super();
        this.login = login;
        this.t_Repo = t_Repo;
        this.t_Followers = t_Followers;
        this.t_Following = t_Following;
        this.githubLink = githubLink;
    }


    public String getColumn1() {
        return login;
    }

    public void setColumn1(String login) {
        this.login = login;
    }

    public String getColumn2() {
        return t_Repo;
    }

    public void setColumn2(String t_Repo) {
        this.t_Repo = t_Repo;
    }

    public String getColumn3() {
        return t_Followers;
    }

    public void setColumn3(String t_Followers) {
        this.t_Followers = t_Followers;
    }

    public String getColumn4() {
        return t_Following;
    }

    public void setColumn4(String t_Following) {
        this.t_Following = t_Following;
    }

    public String getColumn5() {
        return githubLink;
    }

    public void setColumn5(String githubLink) {
        this.githubLink = githubLink;
    }

}
