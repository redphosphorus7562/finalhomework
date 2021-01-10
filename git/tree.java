package git;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tree extends gitobject implements Serializable{
    private Map<String,String> value;
    private  String name;
//    private  String key;

    /**
     *
     * @param name  文件夹的名
     * @param path  对应文件夹的路径
     * @param s     storage 也就是objpath
     * @throws Exception
     */
    public tree( String name, String path,String s) throws Exception {
        super(s);
        this.key = gethash(path);
        this.name = name;
        this.value= getValue(path);
    }

    //getter and setter
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Map<String,String> getValue() {
        return value;
    }

    public void setValue(Map<String,String> value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 从对应文件夹中读取文件夹下的信息
     * @param path 对应文件夹的路径
     * @return     返回一个哈希表 存放：哈希值 和 类型
     * @throws Exception
     */
    public Map<String, String> getValue(String path) throws Exception {
        File file=new File(path);
        File[] files=file.listFiles();
//        int i=0;
//        for (File f : files) {
//            i++;
//        }

        Map<String,String> map = new HashMap<>();
//        String[] s =new String[i];
//        i=0;
        assert files != null;
        for (File f : files) {
            if(f.isFile()){
//                s[i]="Blob "+gethash(f)+" "+f.getName();
                map.put(gethash(f),"Blob");
            }
            else {
//                s[i]="Tree "+gethash(f)+" "+f.getName();
                map.put(gethash(f),"Tree");

            }
//            i++;
        }
        return map;
    }

}
