package git;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

public class blob extends gitobject implements Serializable {
    private String name;//文件名
    private String value;


    /**
     *
     * @param name 文件的名字
     * @param from 读取对应文件的地址
     * @param s storage 也就是objpath
     * @throws Exception
     */
    public blob( String name, String from,String s)
            throws Exception {
        super(s);
        this.key = gethash(from);
        this.name = name;
        this.value = readFile(from);
    }


    /**
     * 从指定路径中
     * 一次性读取全部文件数据
     * @param strFile
     */
    public static String readFile(String strFile){
        try{
            InputStream is = new FileInputStream(strFile);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            String s = new String(bytes);
            is.close();
            return s;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}