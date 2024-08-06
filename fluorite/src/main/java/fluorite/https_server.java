package fluorite;

import com.sun.net.httpserver.HttpsServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class https_server extends http_server{
    private HttpsServer server;

    /**
     * A shared method
     * Create a Https Server Object
     * @param port The server object's working port
     * @param max_connection If connection count over than this value,
     *                       the overflow request will be discarded.
     */
    private void init_server(int port,int max_connection){
        try{
            server = HttpsServer.create(new InetSocketAddress(port), max_connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a Https Server Object
     * - Default port: 8080
     * - Default max_connection: 2000
     */
    public https_server(){
        init_server(8080,2000);
    }

    /**
     * Create a Https Server Object
     * @param port The server object's working port
     * @param max_connection If connection count over than this value,
     *                       the overflow request will be discarded.
     */
    public https_server(int port, int max_connection){
        init_server(port,max_connection);
    }


}
