package database.Controller;

import com.google.gson.*;
import java.io.*;

public class Read {
    private Usuario user;

    public void getJson(){
        try{

            Gson gson = new Gson();

            BufferedReader buff = new BufferedReader(new FileReader("C:\\Users\\victo\\IdeaProjects\\Portfolio-crypto\\src\\database\\usuarios.json"));
            user = gson.fromJson(buff,Usuario.class);

            System.out.println(user.password + " " + user.username);


        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
