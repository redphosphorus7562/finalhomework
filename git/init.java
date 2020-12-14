package git;

import java.io.*;

public class init extends getkey{
    private String storage;

    public init(String path) {
        this.storage = path;
    }

    public init(){}

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void save(Object obj,String name){
        try {
            //创建序列化流
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(storage+"\\"+name));
            //写入对象
            out.writeObject(obj);
            //释放资源
            out.close();
            System.out.println("Serialized data is saved");
        } catch(IOException i)   {
            i.printStackTrace();
        }
    }

    public void take(String name){
        Object o = null;
        try {
            // 创建反序列化流
            FileInputStream fileIn = new FileInputStream(storage+"\\"+name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // 读取一个对象
            o = in.readObject();
            // 释放资源
            in.close();
            fileIn.close();
        }catch(IOException i) {
            // 捕获其他异常
            i.printStackTrace();
        }catch(ClassNotFoundException c)  {
            // 捕获类找不到异常
            System.out.println("class not found");
            c.printStackTrace();
        }
    }
}
