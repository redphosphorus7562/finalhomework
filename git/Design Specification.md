#### task2.将一个文件夹转化为key，value
- 给定一个文件夹目录，将其转化成若干tree和blob
  - 深度优先遍历此目录
    - 遇到自文件就转化为blob并保存
    - 遇到子文件夹就递归调用文件夹内部的子文件/文件夹最后构造tree并保存
- 使用task1提供的接口
- 单元测试
#### task3. 实现Commit
   - 给定一个工作区目录，生成对应的blob和tree(上周已完成)以及commit
   - 写代码之前先理清思路，写设计文档
- 提示：
   - 需要存储指向当前最新commit的HEAD指针
   - 每次新生成一个commit前，需要把根目录的tree key与已有的最新commit的tree key进行比较，发现不相同时（即文件发生了变动）才添加这个commit

#### 类：
#### getkey
- 功能（gethashofFile，gethashofDir，gethash ）
   - 获取文件/文件夹哈希值 转为16进制的字符串
   - 整合文件/文件夹
#### init
- 功能（getStorage，save，take）：
  - 获取储存空间
  - 创建序列化流，写入对象并且释放资源
  - 创建反序列化流，读取对象并且释放资源
#### blob
- 功能:
  - 生成key 类型设为blob
#### tree
- 功能：
   - 遍历文件夹，更新value的内容，据此生成key
#### commit
-功能：
 - 获取新路径 
- 返回更新内容的commit
- 生成HEAD指针，指向当前commit对象  
