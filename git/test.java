package git;

import java.util.ArrayList;
import java.util.Map;

public class test {
    public static void main(String[] args) throws Exception {
        /**
         * create函数的单元测试
         * 会在d盘下生成jf-cjy根目录对应的一系列对象文件
         */
        tree t = new tree("jf-cjy","C:\\Users\\lenovo\\Desktop\\jf-cjy","D:");
        t.save(t.getKey());
        System.out.println(t.getKey());
        tree t2 = (tree) gitobject.take(t.getKey(),"D:");

        commit.create("jf-cjy","C:\\Users\\lenovo\\Desktop\\jf-cjy","D:");

    }
}
