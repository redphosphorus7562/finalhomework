package git;

import java.io.*;

public class gitobject extends getkey implements Serializable{
    /**
     * 抽象出两个变量
     * value由于各类型存储结构不同
     * 所以不放在这里
     */
    public String storage;
    public String key;

    public gitobject(){}
    public gitobject(String s){this.storage =s;}

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 将调用save（）函数的对象以序列化的方式
     * 存储到其objpath下，并命名为name
     * @param name   存储后文件的名字
     */
    public void save(String name){
        try {
            //创建序列化流
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.storage+File.separator+name));
            //写入对象
            out.writeObject(this);
            //释放资源
            out.close();
//            System.out.println("Serialized data is saved");
        } catch(IOException i)   {
            i.printStackTrace();
        }
    }

//    public void save(String key,String storage){
//        try {
//            //创建序列化流
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(storage+File.separator+key));
//            //写入对象
//            out.writeObject(this);
//            //释放资源
//            out.close();
////            System.out.println("Serialized data is saved");
//        } catch(IOException i)   {
//            i.printStackTrace();
//        }
//    }

    /**
     * 用反序列化的方式，从指定文件中提取出对象
     * @param name      要提取的文件的名字
     * @param storage   objpath
     * @return
     */
    public static Object take(String name,String storage) {
        try {
            // 创建反序列化流
            FileInputStream fileIn = new FileInputStream(storage + File.separator + name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // 读取一个对象
            Object obj = in.readObject();
            // 释放资源
            in.close();
            fileIn.close();
            return obj;
        } catch (IOException i) {
            // 捕获其他异常
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            // 捕获类找不到异常
            System.out.println("class not found");
            c.printStackTrace();
            return null;
        }
    }
}
