package Assignment2;

public class data {
    private String follower;
    private String  totalRepo;
    private String total;
    private String trying;
    private String URL;

    public data(String follower, String totalRepo, String total, String trying, String URL)
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

    public String getColumn2() {
        return totalRepo;
    }

    public void setColumn2(String totalRepo) {
        this.totalRepo = totalRepo;
    }

    public String getColumn3() {
        return total;
    }

    public void setColumn3(String total) {
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
