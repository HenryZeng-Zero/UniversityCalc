package fluorite;

public class UnsupportedURI extends RuntimeException {
    public UnsupportedURI(String msg){
        super(msg);
        System.out.println(msg);
    }
}
