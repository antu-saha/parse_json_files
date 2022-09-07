/*
This code fetch the values of specified keys from a nested JSON file recursively.
Input: JSON file
Output: Values
*/
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ParseJSONWithoutKeys {
    public static void getValues(JSONObject object)
    {
        // Provide the key for which you want the values.
        String text;
        if(object.has("text"))
        {
            text = (String) object.get("text");
            System.out.println(text);
        }
        // Provide the key for which you want to get the values recursively.
        if(object.has("children"))
        {
            JSONArray jsonarray = object.getJSONArray("children");
            for(int i = 0; i < jsonarray.length(); i++)
            {
                String jsonArrayString = jsonarray.get(i).toString();
                JSONObject innerJSOn = new JSONObject(jsonArrayString);
                getValues(innerJSOn);
            }
        }
    }

    public static void main(String [] args)
    {
        String jsonFilePath = "data/example.json";
        JSONParser jsonParser = new JSONParser();
        JSONObject inputJSONObject;

        try (FileReader reader = new FileReader(jsonFilePath))
        {
            String obj = jsonParser.parse(reader).toString();
            inputJSONObject = new JSONObject(obj);
            JSONObject activity = inputJSONObject.getJSONObject("activity");
            JSONObject root = activity.getJSONObject("root");

            getValues(root);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}