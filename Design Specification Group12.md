## Java第十二小组课程项目设计文档

- #### 实现的交互

  - showbranch：调用branchmanager查看现有分支
  
  - checkout [branchname]：切换相应分支（将HEAD指针指向该分支）
  
  - newbranch [branchname]：新建分支（创建新分支后HEAD会自动指向他)
  
  - recover：展现当前HEAD指针所指向的分支的内容
  
  - reset：将当前分支所指的commit对象，改为上一个commit对象
  
  - commit [对应的文件根目录]: 提交指定文件/文件夹到当前所在分支
  
  - gitquit: 退出git

- #### getkey类

   - ##### 构造方法
   
     - getsha1(sha1)
     
       - 将sha1字节数组转换为哈希值
       - 生成哈希值
       
     - gethashofFile(File file)
       
       - 获取文件并生成哈希值
       
     - gethashofDir(File file)
     
       - 获取文件夹并生成哈希值
       
     - gethash(File file) 和 gethash(String f)
     
       - 整合文件和文件夹

- #### gitobject类（继承自getkey类）

   - ##### 数据域：String Storage, String key
   
   - ##### 构造方法
   
     - save(String key)
       
       - 以序列化的方式，将对象存储到其objpath下，并命名为name
       
     - take(String name, String storage)
      
       - 用反序列化的方式，从指定文件中提取出对象
       
- #### blob类(继承自getobject类）

   - ##### 数据域：String value, String name(文件名）
   
   - ##### 构造方法
   
     - blob(String name, String from, String s)
     
       - 文件名/读取对应文件的地址/目标地址(objpath)
       - 从读取文件的地址中计算文件的哈希值
     
     - readFile(String strFile)
     
       - 从指定路径中一次性读取全部文件数据

- #### tree类(继承自getobject类）

   - ##### 数据域：Map<String, String> value, String name
   
   - ##### 构造方法
   
     - tree(String name, String path,String s)
     
       - 继承父类gitobject的方法
       - s为存储地址
       - key为路径文件的哈希值
     
     - getValue(String path)
     
       - 定义一个hashmap的属性结构value<哈希值,类型( "Blob"/"Tree")>
       - 读取文件并遍历文件夹
       - 生成相应的Blob和Tree
 
 - #### lib类
 
   - ##### 数据域：String objpath gitobject（存储的路径）
   
   - ##### 构造方法
   
     - recover(tree t, String currbranch)
     
       - 根据根目录对应的tree对象，从object文件中提取信息
       - 生成到currbranch目录下
     
     - writefile(blob b,File file)
     
       - 获取文件并写入blob对象
     
     - delete(String path)
     
       - 递归删除路径下的所有文件

- #### repository类
   
  - ##### 数据域：String Storage
   
  - ##### 构造方法
   
    - init()
     
      - 初始化仓库
      - 创建object文件夹 存放分支列表
      - 创建log文件夹 存放版本的历史
      - 创建currbranch文件夹 存放当前分支的文件
      
- #### commit类(继承自getobject类）

  - ##### 数据域：Map<String,String> value
   
  - ##### 构造方法     
    
    - commit(String path,String forecommithash,String remarks,String s)
   
      - 定义一个hashmap的属性结构value
      - 存放roothash所对应的Tree对象的哈希值
      - forecommithash上一个commit的哈希值
      - remarks其他评论（作者、提交者等信息）
      - 取出map中的键值对，并获取哈希值
      
    - create(String name, String from, String to)
      
      - 将对应根目录下的所有文件/文件夹递归创建对应的对象
      - commit目标文件/文件夹
      - name 对应根目录的名字
      - from  对应根目录的路径
      - to 目标路径objpath

- #### branch类(继承自getobject类）


  - ##### 数据域：String currcommit，String branchname
   
  - ##### 构造方法
  
    - branch(String name,String s,String curr)
    
      - branchname为该分支的名字（不允许同名）
      - storage为目标路径objpath
      - currcommit为当前所对应的commit
      
- #### branchmanage类(继承自getobject类）

  - ##### 数据域：String HEAD，ArrayList<String> branches
   
  - ##### 构造方法
   
    - branchmanager(String HEAD, ArrayList<String> branches,String s)
      
      - HEAD为记录HEAD指向的分支名称
      - branches为各个分支的链表
      - storage为目标路径object
 
- #### command类

  - showbranch：调用branchmanager查看现有分支
  
  - checkout [branchname]：切换相应分支（将HEAD指针指向该分支）
  
  - newbranch [branchname]：新建分支（创建新分支后HEAD会自动指向他)
  
  - recover：展现当前HEAD指针所指向的分支的内容
  
  - reset：将当前分支所指的commit对象，改为上一个commit对象
  
  - commit [对应的文件根目录]: 提交指定文件/文件夹到当前所在分支
  
  - gitquit: 退出git
