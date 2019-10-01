## 项目名称：

cskaoyan-mall

## 小组成员：

李锐、赵亚云、王小凤、杨磊、周榆淮、国旭、陈武



## 项目分工

| 功能模块 | 模块数量 | 成员姓名 |
| :------: | :------: | :------: |
| 用户管理 |    6     |   国旭   |
| 商场管理 |    6     |  周榆淮  |
| 商品管理 |    3     |   杨磊   |
| 推广管理 |    5     |  王小凤  |
| 系统管理 |    4     |   李锐   |
| 配置管理 |    4     |   陈武   |
| 统计报表 |    3     |  赵亚云  |
|   外链   |    2     |   杨磊   |

## 注意事项

1. 每次提交格式

    ```
    git commit -m "author:name message"
    ```


2. 两个包 com.cskaoyan.mall.bean 和 com.cskaoyan.mall.mapper 以及文件夹 resources\com\cskaoyan\mapper 下的内容不允许做任何修改。

    自己的 bean 放到 vo 包下(), 自己的和数据库相关的操作可以在mapper下自建一个包，然后在这个包内写自己的mapper接口，并将对应的 xxxMapper.xml放到 resources 下的对应目录

3. 所有的操作都是在 dev 上进行的，请不要去 master 主干上做任何操作。

4. 每个类上自己写明作者，不要修改其他作者的文件，如果希望别人的类提供对应的方法，可以去找对应的人添加。

## 遇到的问题

1. 从 github 拉取 分支内容

    ```
    链接：https://blog.csdn.net/carfge/article/details/79691360
    ```

    我的步骤

    ```
    git init
    git remote add origin https://github.com/FIGHB/cskaoyan_test.git
    git fetch origin dev
    git checkout -b dev origin/dev
    git pull origin dev
    ```

2. 在 xxxMapper.xml文件中用到 like 模糊查询的时候

    ```
    链接：https://blog.csdn.net/qq_20565303/article/details/75571018
    这个时候不能使用 #{参数} 来引用会报如下错误：
    java.sql.SQLException: Parameter index out of range (5 > number of parameters, which is 4).
    只能改为使用 $ 符号	${参数}	但这会带来注入风险，怎么解决暂时未知...
    ```

3. 在 xxxMapper.xml 中调用查询语句时，如果不写 resultType或者 resultMap 就会出现如下错误：

    ```
    org.apache.ibatis.executor.ExecutorException: A query was run and no Result Maps were found for the Mapped Statement
    ```

    所以所有的查询语句必须写返回类型，不然就会在结果为空的时候报如上错误

4.通过逆向工程获得的 Mapper 中如果有的包含 mysql 的关键字，则需要手动添加反引号