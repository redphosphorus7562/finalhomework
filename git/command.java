package git;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class command {

    public static void main(String[] args) throws Exception {
        /**
         * 初始化仓库
         * 指定objpath
         * 初始化branchmanager并存储
         */
        Repository.setStorage("D:\\git");
        Repository.init();
        String objpath=Repository.getStorage()+ File.separator+"object";
        ArrayList<String> a1 = new ArrayList<>();
        branchmanager brm = new branchmanager("",a1,objpath);
        brm.save("branchmanager");
        lib.setObjpath(objpath);

        System.out.println("请输入git指令：");
        while (true) {

            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            String[] cl = command.split(" ");

            switch(cl[0])
            {
                /**
                 * 调用branchmanager查看现有分支
                 */
                case "showbranch" :
                    branchmanager bm1 = (branchmanager)gitobject.take("branchmanager", objpath);
                    ArrayList<String> arr = bm1.getBranches();
                    for (String s :
                            arr) {
                        System.out.println(s);
                    }
                    break;
                /**
                 * 切换分支到checkout后输入的分支
                 * （将HEAD指针指向该分支）
                 */
                case "checkout" :
                    branchmanager bm2 = (branchmanager)gitobject.take("branchmanager", objpath);
                    bm2.setHEAD(cl[1]);
                    lib.delete(objpath+File.separator+"branchmanager");
                    bm2.save("branchmanager");
                    break;
                /**
                 * 创建一个新分支
                 * （创建新分支后HEAD会自动指向他)
                 */
                case "newbranch" :
                    branch br = new branch(cl[1],objpath,"空");
                    br.save(cl[1]);
                    branchmanager bm3 = (branchmanager)gitobject.take("branchmanager", objpath);
                    bm3.addBranch(br);
                    bm3.setHEAD(br.getBranchname());
                    lib.delete(objpath+File.separator+"branchmanager");
                    bm3.save("branchmanager");

                    break;

                /**
                 * 要展现当前HEAD指针所指向的分支的内容
                 * 都需要输入recover指令
                 */
                case "recover":
                    branchmanager bm4 = (branchmanager)gitobject.take("branchmanager", objpath);
                    String currbranch = bm4.getHEAD();
                    branch br2 = (branch)gitobject.take(currbranch,objpath);
                    commit c = (commit) gitobject.take(br2.getCurrcommit(),objpath);
                    Map<String,String> map2 = c.getValue();
                    String roothash = map2.get("roothash");
                    tree t = (tree)gitobject.take(roothash,objpath);
                    lib.delete(Repository.getStorage()+File.separator+"currbranch");
                    File file2 = new File(Repository.getStorage()+File.separator+"currbranch");
                    file2.mkdir();
                    lib.recover(t,Repository.getStorage()+File.separator+"currbranch");
                    break;

                /**
                 * 将当前分支所指的commit对象，改为上一个commit对象
                 * reset后使用recover命令可以在currbranch文件夹下看到
                 */
                case "reset":
                    branchmanager bm5 = (branchmanager)gitobject.take("branchmanager", objpath);
                    String currbranch2 = bm5.getHEAD();
                    branch br3 = (branch)gitobject.take(currbranch2,objpath);
                    commit c2 = (commit) gitobject.take(br3.getCurrcommit(),objpath);
                    Map<String,String> map3 = c2.getValue();
                    String forecommithash = map3.get("forecommithash");
                    commit c3 = (commit) gitobject.take(forecommithash,objpath);
                    br3.setCurrcommit(forecommithash);
                    lib.delete(objpath+File.separator+br3.getBranchname());
                    br3.save(br3.getBranchname());
                    break;

                /**
                 * commit后要输入一个地址，代表本次commit对应的文件根目录
                 * commit会直接commit到当前所在分支
                 * 同样使用recover查看结果
                 */
                case "commit":
                    branchmanager bm6 = (branchmanager)gitobject.take("branchmanager", objpath);
                    String currbranch3 = bm6.getHEAD();
                    branch br4 = (branch)gitobject.take(currbranch3,objpath);

                    File file = new File(cl[1]);
                    tree t2 = new tree(file.getName(),cl[1],objpath);
                    t2.save(t2.getKey());
                    commit c4 = new commit(cl[1],br4.getCurrcommit(),"无",objpath);
                    c4.save(c4.getKey());
                    commit.create(t2.getName(),cl[1],objpath);

                    br4.setCurrcommit(c4.getKey());
                    lib.delete(objpath+File.separator+br4.getBranchname());
                    br4.save(br4.getBranchname());
                    break;

                //退出
                case "gitquit" :
                    System.out.println("退出");
                    break;

                default :
                    System.out.println("invaild command");

            }
        }
    }
}