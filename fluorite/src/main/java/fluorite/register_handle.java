package fluorite;
import com.sun.net.httpserver.HttpContext;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class register_handle {
    private HttpContext context;
    private String match_expr;

    public void set_context(HttpContext context){
        this.context = context;
    }

    public void set_handler(http_handler handler){
        context.setHandler(handler);
    }

    public String uri_pretreatment(String uri) throws UnsupportedURI {
        char[] match_stack = {'}','{'};
        int[] matched = {-1,-1};
        int match_index = 0;
        int check_count = 0;

        // try to match {}
        for(int i = uri.length()-1; i >= 0; i--){
            if (match_index == match_stack.length){
                break;
            }
            if (uri.charAt(i) == match_stack[match_index]){
                matched[match_index] = i;
                ++match_index;
            }
        }

        // count for matched items
        for(int i: matched){
            if (i == -1){
               check_count++;
            }
        }

        // throw exceptions if not exact match
        if (check_count != 2){
            throw new UnsupportedURI("Can't get regular expressions in your given uri, " +
                                          "maybe you should check your curly brackets");
        }

        String valid_uri = uri.substring(0,matched[1]);
        String match_expr = uri.substring(matched[1]+1,matched[0]);

        try {
            Pattern.compile(match_expr);
        } catch (PatternSyntaxException e){
            System.out.println("The regex in uri is invalid" + e.getMessage());
        }

        return valid_uri;
    }
}
