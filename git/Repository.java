package git;

import java.io.File;

public class Repository {
    private static String Storage;

    public Repository() {
    }

    public static String getStorage() {
        return Storage;
    }

    public static void setStorage(String storage) {
        Storage = storage;
    }

    /**
     * 初始化仓库
     */
    public static void init(){
        File storage = new File(Storage);
        if(!storage.exists()){
            storage.mkdirs();
        }
        File object = new File(Storage+File.separator+"object");
        object.mkdir();
        File log = new File(Storage+File.separator+"log");
        log.mkdir();
        File currbranch = new File(Storage+File.separator+"currbranch");
        currbranch.mkdir();
    }



}
