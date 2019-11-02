package Assignment2;

public class data {
    private String follower;
    private int totalRepo;
    private int total;
    private String trying;
    private String URL;

    public data(String follower, int totalRepo, int total, String trying, String URL)
    {
        super();
        this.follower = follower;
        this.totalRepo = totalRepo;
        this.total = total;
        this.trying = trying;
        this.URL = URL;
    }


    public String getColumn1() {
        return follower;
    }

    public void setColumn1(String follower) {
        this.follower = follower;
    }

    public int getColumn2() {
        return totalRepo;
    }

    public void setColumn2(int totalRepo) {
        this.totalRepo = totalRepo;
    }

    public int getColumn3() {
        return total;
    }

    public void setColumn3(int total) {
        this.total = total;
    }

    public String getColumn4() {
        return trying;
    }

    public void setColumn4(String trying) {
        this.trying = trying;
    }

    public String getColumn5() {
        return URL;
    }

    public void setColumn5(String URL) {
        this.URL = URL;
    }

}
