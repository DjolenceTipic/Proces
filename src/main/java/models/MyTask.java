package models;

import java.util.List;

/**
 * Created by Djordje on 4/19/2017.
 */
public class MyTask {

    private String id;
    private String name;
    private List<MyForm> myFormList;

    public MyTask(String id, String name, List<MyForm> myFormList) {
        this.id = id;
        this.name = name;
        this.myFormList = myFormList;
    }

    public List<MyForm> getMyFormList() {
        return myFormList;
    }

    public void setMyFormList(List<MyForm> myFormList) {
        this.myFormList = myFormList;
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

    public MyTask() {
    }

    public MyTask(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", myFormList=" + myFormList +
                '}';
    }
}
