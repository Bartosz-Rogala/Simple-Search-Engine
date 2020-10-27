import java.util.ArrayList;
import java.util.Map;

public class AllMethod implements SearchingMethod {
    @Override
    public String search(Map<String, ArrayList<Integer>> map, ArrayList<String> database, String value) {
        // create an array in which all results will be stored
        ArrayList<String> resultArray = new ArrayList<>();

        // create a temporary array
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < database.size(); i++) {
            tmp.add(i);
        }

        // get all potential words inputted by the user
        String[] values = value.split(" ");

        // get list of indexes that match all word inputted by the user
        for(String b: values) {
            if (map.containsKey(b.toUpperCase())) {
                tmp.retainAll(map.get(b.toUpperCase()));
            }
        }

        //based on list of indexes get all entries from the file that match
        for (Integer a : tmp) {
            resultArray.add(database.get(a));
        }


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