package core;

import java.util.HashMap;
import java.util.Map;

public class UserTypeObject {

    private Map<String ,String> m;

    public UserTypeObject(String username, String email, String password){
        m = new HashMap();
        m.put("username",username);
        m.put("email",email);
        m.put("password",password);
        m.put("theme","lightMode");
    }

    public UserTypeObject(){
        m = new HashMap();
        m.put("username",null);
        m.put("email",null);
        m.put("password",null);
        m.put("theme","lightMode");
    }

    @Override
    public String toString() {
        return "UserName: "+m.get("username")+" | Email: "+m.get("email");
    }

    public void updateField(String field, String value) {
        m.replace(field,value);
    }

    public void setUsername(String username) { m.replace("username",username); }
    public void setPassword(String password) { m.replace("password",password); }
    public void setEmail(String email) { m.replace("email",email); }
    public void setTheme(String theme) { m.replace("theme",theme); }



    public String getEmail() { return m.get("email"); }
    public String getUsername() { return m.get("username"); }
    public String getPassword() { return m.get("password"); }
    public String getTheme() { return m.get("theme"); }

    //"{'username':'"+UserName+"','email':'"+email+"','password':'"+Password+"'}
}


/*
private String username=null,email=null,password=null,theme=null;

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setTheme(String theme) { this.theme = theme; }

    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getTheme() { return theme; }

    public UserTypeObject(String username, String email, String password){
        setUsername(username);setEmail(email);setPassword(password);
    }
    public UserTypeObject(){}
 */