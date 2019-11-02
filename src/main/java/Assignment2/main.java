package Assignment2;


import java.io.IOException;

public class main extends scrapeGitHub{
    public static void main(String[] args) throws IOException {
        scrapeGitHub.findAll();
        saveToExcel.main();
        //app c_app = new app();
        //scrapeGitHub c_scrapeGitHub = new scrapeGitHub();

        //c_app.application();
        //c_scrapeGitHub.fin();
    }
}