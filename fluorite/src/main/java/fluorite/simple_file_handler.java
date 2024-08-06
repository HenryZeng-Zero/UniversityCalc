package fluorite;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class simple_file_handler extends http_handler {
     private final HttpHandler handler;

     /**
      * @param path A path where storage your static files.
      * @param paths Sub folders. Such as: (path)/home/name -> (path),"home","name"
      * @throws InvalidPathException Path value is not correct.
      */
     public simple_file_handler(String path,String ...paths) {
          Path path_object = Paths.get(path,paths);
          handler = SimpleFileServer.createFileHandler(path_object);
     }

     @Override
     public void handle(HttpExchange t) {
          try {
               handler.handle(t);
          } catch (IOException e){
               throw new RuntimeException(e);
          }
     }
}
