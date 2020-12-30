package git;

import java.io.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class commit extends init{
    private String key;
    private Map<String,String> value = new HashMap<>();

    //constructor  path路径中的文件存储commit的value
    public commit(String path,String forecommithash,String remarks) throws Exception {
        String roothash = gethash(path);
        this.value.put("roothash",roothash);
        this.value.put("forecommithash",forecommithash);
        this.value.put("remarks",remarks);
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        for (Map.Entry<String,String> a:value.entrySet()) {
            complete.update(a.getValue().getBytes());
        }
        byte[] sha1 = complete.digest();
        this.key = getsha1(sha1);
        //创建head指针
        head p = new head(this.key);
        //调用save函数存储对象
        save(this,this.key);
    }




}
