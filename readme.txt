初始化仓库可以直接从远程仓库直接clone
不会初始化仓库在和远程仓库建立连接（太繁琐了）

模糊查询同样适用#{}来实现模糊查询
比如user中要查询的username=张三
需要在调用mapper层之前，将username封装成  %张三%
然后sql语句条件部分 where username like #{username}

内容记录的不错，继续加油