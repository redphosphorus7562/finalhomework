package git;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class getkey {


    /**
     * 将sha1字节数组转换为哈希值
     * @param sha1  sha1字节数组
     * @return
     */
    public String getsha1(byte[] sha1){
        String result="";

        for (byte b : sha1) {
            String append = Integer.toString(b & 0xFF,16);
            if(append.length()<2){
                result = result +"0"+append;
            }
            else {
                result +=append;
            }
        }
        return result;
    }

    /**
     * 计算文件哈希值
     * @param file  求哈希值的文件
     * @return      返回文件的哈希值
     * @throws Exception
     */
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
        return getsha1(sha1);
    }

    /**
     * 计算文件夹的哈希值
     * @param file
     * @return
     * @throws Exception
     */
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

        return getsha1(sha1);
    }


    /**
     * 计算文件或文件夹的哈希值（整合）
     * @param file  对应文件或文件夹
     * @return      返回哈希值
     * @throws Exception
     */
    public String gethash(File file) throws Exception{
        if(file.isFile()){
            return gethashofFile(file);
        }
        return gethashofDir(file);
    }

    //重写上面的函数
    public String gethash(String f) throws Exception{
        File file=new File(f);
        return gethash(file);
    }



}
