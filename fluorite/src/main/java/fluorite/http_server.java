package fluorite;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class http_server {
    private HttpServer server;

    /**
     * A shared method
     * Create a Http Server Object
     * @param port The server object's working port
     * @param max_connection If connection count over than this value,
     *                       the overflow request will be discarded.
     */
    private void init_server(int port,int max_connection){
        try{
            this.server = HttpServer.create(new InetSocketAddress(port), max_connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a Http Server Object
     * @apiNote  Default port: 8080 <br>
     *           Default max_connection: 2000
     */
    public http_server(){
        init_server(8080,2000);
    }

    /**
     * Create a Http Server Object
     * @param port The server object's working port
     * @param max_connection If connection count over than this value,
     *                       the overflow request will be discarded.
     */
    public http_server(int port, int max_connection){
        init_server(port,max_connection);
    }

    /**
     * To register an appropriate uri as a processing path.
     * @param path The uri path, such as: "/name/home"
     * @return A {@code register_info} object, which helps you delete your registry.
     * @apiNote  Default handler: null <br>
     *           (You should use {@code set_handler()} to set your own handler.)
     */
    public register_handle register(String path){
        return register(path,null);
    }

    /**
     * To register an appropriate uri as a processing path.
     * @param uri The uri path, such as: "/name/home" <br>
     *            Special usage:<br>
     *            "/home/{}" : You can write regular expressions in {} such as {.*}.
     *                         You can use {@code get_match()} method in {@code http_handler}
     *                         to get matched String.
     * @param handler An instantiation object for process the request.
     * @throws  IllegalArgumentException if path is invalid, or if a context already exists for this path
     * @throws  NullPointerException if path is null
     * @return A {@code register_info} object, which helps you delete your registry.
     */
    public register_handle register(String uri, http_handler handler){
        var info = new register_handle();
        var valid_uri = info.uri_pretreatment(uri);

        try {
            var context = server.createContext(valid_uri);
            info.set_context(context);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        if (handler != null){
            info.set_handler(handler);
        }

        return info;
    }

    /**
     * To register an appropriate uri as a processing path.
     * @param handle The {@code register_info} object which you want to modify.
     * @param handler An instantiation object for process the request.
     */
    public void set_handler(register_handle handle,http_handler handler){
        handle.set_handler(handler);
    }

    /**
     * To start the server.
     */
    public void start(){
        server.start();
    }

    /**
     * To stop the server.
     * @param delay the maximum time in seconds to wait until exchanges have finished.
     * @throws IllegalArgumentException if delay is less than zero.
     */
    public void stop(int delay){
        try {
            server.stop(delay);
        }catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }
    }
}
