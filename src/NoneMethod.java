import java.util.ArrayList;
import java.util.Map;

public class NoneMethod implements SearchingMethod {
    @Override
    public String search(Map<String, ArrayList<Integer>> map, ArrayList<String> database, String value) {

        // create an array in which all results will be stored
        ArrayList<String> resultArray = database;

        // create a temporary array
        ArrayList<String> tmp = new ArrayList<>();

        // get all potential words inputted by the user
        String[] values = value.split(" ");

        // get list of all entries that match words inputted byt the user
        for(String b: values) {
            if (map.containsKey(b.toUpperCase())) {
                for (Integer a : map.get(b.toUpperCase())) {
                    tmp.add(database.get(a));
                }
            }
        }

        resultArray.removeAll(tmp);

        // return outcome
        if(resultArray.isEmpty()) {
            return "No matching people found.";
        } else {
            String result = "Found entries:" + "\n";
            for(String a: resultArray){
                result += a.trim() + "\n";
            }
            return result;
        }

    }
}
