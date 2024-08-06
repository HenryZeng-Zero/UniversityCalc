package fluorite;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class http_handler implements HttpHandler {
     public void handle(HttpExchange t) throws IOException {
          InputStream is = t.getRequestBody();

          String response = "This is the response";
          t.sendResponseHeaders(200, response.length());
          OutputStream os = t.getResponseBody();
          os.write(response.getBytes());
          os.close();
     }
}
