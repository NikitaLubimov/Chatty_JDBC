package server;

public class StartServer {

    public static void main(String[] args) {
        DataBaseAuthService dataBaseAuthService = new DataBaseAuthService();
        try {
            dataBaseAuthService.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Server();
    }
}
