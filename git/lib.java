package git;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class lib {
    //gitobject存储的路径
    private static String objpath;

    public static void setObjpath(String objpath) {
        lib.objpath = objpath;
    }

    /**
     * 根据根目录对应的tree对象，从object文件中提取信息
     * 生成到currbranch目录下
     * @param t             传入需要还原的根目录对应的tree对象
     * @param currbranch    git文件夹下展现现有branch的路径
     * @throws IOException
     */
    public static void recover(tree t, String currbranch) throws IOException {
        Map<String,String> map = new HashMap<>();
        map = t.getValue();
        currbranch+=File.separator+t.getName();
        File f = new File(currbranch);
        f.mkdir();



        Iterator it=map.keySet().iterator();

        while(it.hasNext()){

            //取出key
            String key=it.next().toString();

            //通过key拿到value
            String v=(String) map.get(key);

            if(v.equals("Blob")){
                blob b = (blob) gitobject.take(key, objpath);
                File file = new File(currbranch + File.separator + b.getName());
                file.createNewFile();
                lib.writefile(b, file);
            }
            if (v.equals("Tree")) {
                tree t2 = (tree) gitobject.take(key,objpath);
                File file = new File(currbranch + File.separator + t2.getName());
                recover(t2, currbranch);
            }

          }
    }

    /**
     * 在file文件中写入blob对象的value
     * @param b     要写入的blob对象
     * @param file  被写入的文件
     */
    public static void writefile(blob b,File file){

        try {
            String s = b.getValue();
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(s);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 递归删除路径下的所有文件
     * @param path  要删除的文件的路径
     * @return
     */
    public static boolean delete(String path) {
        File file= new File(path);

        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        } else {
            for (File f : file.listFiles()) {
                delete(path + File.separator + f.getName());
            }
        }
        return file.delete();
    }

}

