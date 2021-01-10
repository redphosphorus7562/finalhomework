package git;

import java.io.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class commit extends gitobject {

    private Map<String,String> value = new HashMap<>();
//    private String key;

    /**
     *
     * @param path              对应根目录的路径
     * @param forecommithash    上一个commit的哈希值
     * @param remarks           其他评论（作者、提交者等信息）
     * @param s                 objpath
     * @throws Exception
     */
    public commit(String path,String forecommithash,String remarks,String s) throws Exception {
        String roothash = gethash(path);
        this.value.put("roothash",roothash);//所对应的Tree对象的哈希值
        this.value.put("forecommithash",forecommithash);
        this.value.put("remarks",remarks);
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        for (Map.Entry<String,String> a:value.entrySet()) {
            complete.update(a.getValue().getBytes());
        }
        byte[] sha1 = complete.digest();
        this.key = getsha1(sha1);
        this.storage = s;
    }

    /**
     *getter and setter
     */
    public String getKey(){
        return key;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }


    /**
     *
     * 将对应根目录下的所有文件/文件夹递归创建对应的对象
     * 并保存至objpath下
     * @param name  对应根目录的名字
     * @param from  对应根目录的路径
     * @param to    objpath
     * @throws Exception
     */
    public static void create(String name, String from, String to) throws Exception {
        File f = new File(from);
        File[] fs = f.listFiles();
        for (File fl :
                fs) {
            if(fl.isFile()){
                blob b = new blob(fl.getName(),from+File.separator+fl.getName(),to);
                b.save(b.getKey());
            }
            if(fl.isDirectory()){
                tree t = new tree(fl.getName(),from+File.separator+fl.getName(),to);
                t.save(t.getKey());
                create(t.getName(),from+File.separator+fl.getName(),to);
            }
        }

    }




}
