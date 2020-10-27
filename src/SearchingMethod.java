
import java.util.ArrayList;
import java.util.Map;

// interface that instructs all searching methods
public interface SearchingMethod {
    String search(Map<String, ArrayList<Integer>> map, ArrayList<String> database, String value);
}