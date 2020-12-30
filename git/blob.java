package git;

public class blob extends init{
    private String key;
    private String name;

    //constructor
    public blob( String name, String path) throws Exception {
        super();
        this.key = gethash(path);
        this.name = name;
        //调用save函数存储对象
        save(this,this.key);
    }


    //getter and setter
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }



}