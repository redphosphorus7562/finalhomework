package git;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class getkey {


    //获取文件哈希值
    private String gethashofFile(File file) throws Exception{
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        byte[] buffer= new  byte[1024];
        FileInputStream fis=new FileInputStream(file);
        int numread=0;
        while (numread!=-1){
            numread=fis.read(buffer);
            if(numread>0){
                complete.update(buffer,0,numread);
            }
        }
        fis.close();
        byte[] sha1=complete.digest();
        String result="";
        for (byte b : sha1) {
            result+=Integer.toString(b & 0xFF,16);
        }
        return result;
    }

    //获取文件夹哈希值
    private String gethashofDir(File file) throws Exception{
        MessageDigest complete =MessageDigest.getInstance("SHA-1");
        File[] fs = file.listFiles();

        for (File f : fs) {
            if(f.isDirectory()){
                complete.update("Tree ".getBytes());
                complete.update(f.getName().getBytes());
                gethashofDir(f);
            }
            if (f.isFile()){
                complete.update("Blob ".getBytes());
                complete.update(f.getName().getBytes());
                complete.update(gethashofFile(f).getBytes());
            }

        }
        byte[] sha1=complete.digest();
        String result="";
        for (byte b : sha1) {
            result+=Integer.toString(b & 0xFF,16);
        }
        return result;
    }

    //整合文件和文件夹
    public String gethash(String f) throws Exception{
        File file=new File(f);
        String result;
        if(file.isFile()){
            result=gethashofFile(file);
        }
        else {
            result=gethashofDir(file);

        }

        return result;
    }

    public String gethash(File file) throws Exception{

        String result;
        if(file.isFile()){
            result=gethashofFile(file);
        }
        else {
            result=gethashofDir(file);

        }

        return result;
    }

}
