package git;

import java.io.*;

public class commit extends init{
    private String key;
    private String value;

    //constructor  path路径中的文件存储commit的value
    public commit(String path) throws Exception {
        this.key=gethash(path);
        this.value=getvalue(path);
        super.head=this.key;
        save(this,this.key);
    }



    //commit的value
    public String getvalue(String path) throws Exception {
        String val="";
        File file=new File(path);
        FileInputStream fis=new FileInputStream(file);
        int len;
        byte[] b=new byte[2];
        while ((len=fis.read(b))!=-1){
            String s=new String(b);
            val+=s;
        }
        return val;
    }
}
