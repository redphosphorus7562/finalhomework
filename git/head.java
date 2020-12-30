package git;

public class head  extends init{
    private String p;


    public head(String p){
        this.p = p;
        //调用save函数存储对象
        save(this,"head");
    }

    public String getP() {
        return p;
    }


}
