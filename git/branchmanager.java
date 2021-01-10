package git;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class branchmanager extends gitobject{
   public String HEAD;
   public ArrayList<String> branches;


    /**
     *
     * @param HEAD      记录HEAD指向的分支名称
     * @param branches  各个分支的链表
     * @param s         objpath
     */
    public branchmanager(String HEAD, ArrayList<String> branches,String s) {
        this.HEAD = HEAD;
        this.branches = branches;
        this.storage=s;
    }

    public String getHEAD() {
        return HEAD;
    }

    /**
     * 传入某一branch对象的名字作为HEAD
     * @param HEAD
     */
    public void setHEAD(String HEAD) {
        this.HEAD = HEAD;
    }

    /**
     * 获取map
     * @return
     */
    public ArrayList<String> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<String> branches) {
        this.branches = branches;
    }

    /**
     * 传入一个branch对象，将其名称与对应的commitid存入
     * hashmap中
     * @param b
     */
    public void addBranch(branch b){
        branches.add(b.getBranchname());
    }



}
