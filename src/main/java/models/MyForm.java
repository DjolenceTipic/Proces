package models;

/**
 * Created by Djordje on 4/19/2017.
 */
public class MyForm {

    private String id;
    private String name;
    private String type;
    private String isWritable;
    private String value;

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

    public String getIsWritable() {
        return isWritable;
    }

    public void setIsWritable(String isWritable) {
        this.isWritable = isWritable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MyForm(String id, String name, String type, String isWritable, String value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isWritable = isWritable;
        this.value = value;
    }

    public MyForm() {
    }

    @Override
    public String toString() {
        return "MyForm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isWritable='" + isWritable + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
