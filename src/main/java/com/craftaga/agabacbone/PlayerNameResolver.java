package com.craftaga.agabacbone;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 13/04/14
 */
public class PlayerNameResolver implements IPlayerNameResolver
{
    private static final String URL = "http://api.goender.net/api/profiles/";
    private static final String MESSAGE_FORMAT_JSON = "json";

    @Override
    public UUID getUniqueId(String playerName)
    {
        UUID uuid = null;
        JSONParser parser = new JSONParser();
        HttpURLConnection httpConnection = null;
        BufferedReader responseBuffer = null;
        StringBuilder json = new StringBuilder();
        try
        {
            URL playerResolver = new URL(URL + playerName + "/" + MESSAGE_FORMAT_JSON + "/");
            httpConnection = (HttpURLConnection)playerResolver.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/" + MESSAGE_FORMAT_JSON);

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP Get Request Failed with Error Code: " +
                        httpConnection.getResponseCode());
            }

            responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                json.append(output + "\n");
            }
            responseBuffer.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //Should never get here as reading from a string
            e.printStackTrace();
        } finally {
            httpConnection.disconnect();
        }

        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) parser.parse(json.toString());
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String uuidString = (String)jsonObj.get(playerName);
        return UUID.fromString(uuidString.replaceAll(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5"));
    }
}
