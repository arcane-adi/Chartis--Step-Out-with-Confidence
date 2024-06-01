package com.example.maptyxf;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonReader {

    public ArrayList<String> readJsonFileKeys(String filePath) {
        ArrayList<String> keys = new ArrayList<>();
        try {
            // Create a FileReader object to read the JSON file
            FileReader reader = new FileReader(filePath);

            // Create a Gson object
            Gson gson = new Gson();

            // Parse the JSON file into a JsonElement
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);

            // Check if the JsonElement is a JsonObject
            if (jsonElement.isJsonObject()) {
                // Convert the JsonElement to a JsonObject
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                // Iterate over the entries in the JsonObject and add keys to the list
                for (String key : jsonObject.keySet()) {
                    keys.add(key);
                }
            }

            // Close the FileReader
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return keys;
    }

    public ArrayList<String> readJsonFileValues(String filePath) {
        ArrayList<String> values = new ArrayList<>();
        try {
            // Create a FileReader object to read the JSON file
            FileReader reader = new FileReader(filePath);

            // Create a Gson object
            Gson gson = new Gson();

            // Parse the JSON file into a JsonElement
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);

            // Check if the JsonElement is a JsonObject
            if (jsonElement.isJsonObject()) {
                // Convert the JsonElement to a JsonObject
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                // Iterate over the entries in the JsonObject and add values to the list
                for (String key : jsonObject.keySet()) {
                    JsonElement valueElement = jsonObject.get(key);
                    values.add(valueElement.getAsString());
                }
            }

            // Close the FileReader
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

//    public static void main(String[] args) {
//        // Path to the JSON file
//        String filePath = "path_to_your_json_file.json";
//
//        // Create an instance of JsonReader and call readJsonFile method
//        JsonReader jsonReader = new JsonReader();
//        ArrayList<String> keys = jsonReader.readJsonFileKeys(filePath);
//        ArrayList<String> values = jsonReader.readJsonFileValues(filePath);
//
//        // Print the keys and values
//        System.out.println("Keys: " + keys);
//        System.out.println("Values: " + values);
//    }
}
