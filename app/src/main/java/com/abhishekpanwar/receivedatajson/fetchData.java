package com.abhishekpanwar.receivedatajson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abhishek Panwar on 7/14/2017.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/rvbsu");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "QuikPay Instance: " + JO.get("server_name") + "\n"+
                                "Name: " + JO.get("name") + "\n"+
                                "Main Street Address: " + JO.get("main_street_address") + "\n"+
                                "Phone: " + JO.get("phone") + "\n"+
                                "City: " + JO.get("city") + "\n"+
                                "State: " + JO.get("state") + "\n"+
                                "Zip: " + JO.get("zip") + "\n"+
                                "Country: " + JO.get("country") + "\n"+
                                "Account Manager: " + JO.get("account_manager") + "\n"+
                                "Ext: " + JO.get("account_manager_ext") + "\n"+
                                "SAM: " + JO.get("mp_account_manager") + "\n"+
                                "SAM EXT: " + JO.get("mp_account_manager_ext") + "\n"+
                                "RVP: " + JO.get("regional_vice_president") + "\n"+
                                "CRM: " + JO.get("customer_relationship_manager") + "\n";


                dataParsed = dataParsed + singleParsed +"\n" ;


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);

    }
}
