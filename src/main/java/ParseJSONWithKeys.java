/*
This code fetch the values of a given key from a nested JSON file recursively.
Input: JSON file and a Key
Output: Values for the Key
*/
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.JSONObject;
import java.io.FileReader;
import java.util.Iterator;

public class ParseJSONWithKeys
{
    public static void getValues(JSONObject json, String key)
    {
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;

        if(!exists)
        {
            keys = json.keys();
            while(keys.hasNext())
            {
                nextKeys = (String) keys.next();
                try
                {
                    if(json.get(nextKeys) instanceof JSONObject)
                    {
                        getValues(json.getJSONObject(nextKeys), key);
                    }
                    else if(json.get(nextKeys) instanceof JSONArray)
                    {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            String jsonArrayString= jsonArray.get(i).toString();
                            JSONObject innerJSON = new JSONObject(jsonArrayString);

                            getValues(innerJSON, key);
                        }
                    }
                }
                catch (Exception e)
                {

                }
            }
        }
        else
        {
            System.out.println(json.get(key));
        }
    }

    public static void main(String [] args)
    {
        String jsonFilePath = "data/example.json";
        JSONParser jsonParser = new JSONParser();
        JSONObject inputJSONObject;

        try (FileReader reader = new FileReader(jsonFilePath))
        {
            //Read JSON file
            String obj = jsonParser.parse(reader).toString();
            inputJSONObject = new JSONObject(obj);

            getValues(inputJSONObject, "text");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}