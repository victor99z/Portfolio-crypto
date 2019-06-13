package core;

public class UserTypeObject {

    private String username=null,email=null,password=null;

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public UserTypeObject(String username, String email, String password){
        setUsername(username);setEmail(email);setPassword(password);
    }
    public UserTypeObject(){};

    @Override
    public String toString() {
        return "UserName: "+getUsername()+" | Email: "+getEmail();
    }

    //"{'username':'"+UserName+"','email':'"+email+"','password':'"+Password+"'}
}
