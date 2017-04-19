package models;

/**
 * Created by Djordje on 4/19/2017.
 */
public class MyForm {

    private String id;
    private String name;
    private String type;

    public MyForm(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public MyForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
