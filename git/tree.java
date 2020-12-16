package git;


import java.io.*;

public class tree extends init{
    private  String key;
    private  String[] value;
    private  String name;

    //constructor
    public tree( String name, String path) throws Exception {
        this.key = gethash(path);
        this.name = name;
        this.value=getValue(path);
        save(this,this.key);
    }

    //getter and setter
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //tree的value
    public String[] getValue(String path) throws Exception {
        File file=new File(path);
        File[] files=file.listFiles();
        int i=0;
        for (File f : files) {
            i++;
        }
        String[] s =new String[i];
        i=0;
        assert files != null;
        for (File f : files) {
            if(f.isFile()){
                s[i]="Blob "+gethash(f)+" "+f.getName();
            }
            else {
                s[i]="Tree "+gethash(f)+" "+f.getName();
            }
            i++;
        }
        return s;
    }

}
