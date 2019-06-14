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

    public ApiReader(String url) throws Exception {
        setConnection(url);
    }

    protected void setConnection(String url) throws Exception {
        try {

            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

        } catch (MalformedURLException e) {

            // TODO Auto-generated catch block
            //e.printStackTrace();
            throw new Exception("Erro na URL");

        } catch (ProtocolException e) {

            // TODO Auto-generated catch block
            //e.printStackTrace();
            throw new Exception("Erro no protocolo");

        } catch (IOException e) {

            // TODO Auto-generated catch block
            //e.printStackTrace();
            throw new Exception("Erro nas entradas da conexao");

        }
    }

    public String getText() throws Exception {
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
            //e.printStackTrace();
            return "Erro de encoding";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return "Erro de IO";

        }catch (Exception e){
            return "Erro na leitura do buffer";
        }
    }

    public String toString(){
        String frase = null;
        try {
            frase = getText();
        } catch (Exception e) {
            //e.printStackTrace();
            frase=null;
        }
        return frase;
    }

}
