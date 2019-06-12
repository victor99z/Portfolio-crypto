package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiReader {

    private HttpURLConnection con;

    public ApiReader(String url) {
        setConnection(url);
    }

    protected void setConnection(String url) {
        try {

            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

        } catch (MalformedURLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (ProtocolException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    public String getText() {
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

    public String toString(){
        String frase = getText();
        return frase;
    }

}
