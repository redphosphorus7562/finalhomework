package git;

import java.io.File;
import java.util.Map;

public class test2 {
    public static void main(String[] args) throws Exception {

        /**
         *
         * 单元测试2
         * 用来测试recover函数
         */
        Repository.setStorage("D:\\git");
        Repository.init();
        lib.setObjpath(Repository.getStorage()+ File.separator+"object");

        commit.create("leecode","C:\\Users\\lenovo\\Desktop\\leecode",
                Repository.getStorage()+ File.separator+"object");
        tree t = new tree("leecode","C:\\Users\\lenovo\\Desktop\\leecode",
                Repository.getStorage()+ File.separator+"object");
        t.save(t.getKey());
        lib.recover(t,Repository.getStorage()+File.separator+"currbranch");

//
//        tree t = new tree(".idea","C:\\Users\\lenovo\\Desktop\\jf-cjy\\.idea",
//                Repository.getStorage()+ File.separator+"object");
//        t.save(t.getKey());
//        System.out.println(t.getKey());
//        tree t2 = (tree) gitobject.take(t.getKey(),Repository.getStorage()+ File.separator+"object");
//
//        Map<String,String> map = t2.getValue();
//        for(Map.Entry<String,String> a:map.entrySet()){
//
//            System.out.println("键是"+a.getKey());
//
//            System.out.println("值是"+a.getValue());

//        }
    }
}
