package universitycalc;
import fluorite.http_handler;
import fluorite.http_server;
import fluorite.simple_file_handler;
import fluorite.utils;

public class App {
    public static void main(String[] args) {
        http_server server = new http_server(1000,2000);

        server.register("/",new simple_file_handler(utils.get_workpath(),"Web","test"));
        // server.register("/ss", new http_handler());

        server.start();
        System.out.println("Server started at http://127.0.0.1:1000");
    }
}
