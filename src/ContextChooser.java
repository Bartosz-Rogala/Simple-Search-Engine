
import java.util.ArrayList;
import java.util.Map;

public class ContextChooser {
//context chooser chooses which searching method will be called
    private SearchingMethod method;

    public void setMethod(SearchingMethod method) {
        this.method = method;
    }

    public String search(Map<String, ArrayList<Integer>> map, ArrayList<String> database, String value) {
        return this.method.search(map, database, value);
    }
}