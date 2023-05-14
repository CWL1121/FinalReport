package ethci.lab.r3feditorback.Entity.projectsModel;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    public String _id;
    public String name ;
    public String password ;
    public String role;
    public Key key;
    public User() {
    }
    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
