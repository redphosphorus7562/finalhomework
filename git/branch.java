package git;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class branch extends gitobject{
    public String currcommit;
    public String branchname;

    /**
     *
     * @param name  该分支的名字（不允许同名）
     * @param s     objpath
     * @param curr  当前所对应的commit
     */
    public branch(String name,String s,String curr){
        this.branchname = name;
        this.storage = s;
        this.currcommit = curr;
    }



    public String getCurrcommit() {
        return currcommit;
    }

    public void setCurrcommit(String currcommit) {
        this.currcommit = currcommit;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }


}
