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


2. 两个包 com.cskaoyan.bean 和 com.cskaoyan.mapper 以及文件夹 resources\com\cskaoyan\mapper 下的内容不允许做任何修改。

    自己的 bean 放到 vo 包下(), 自己的和数据库相关的操作放到 selfmapper 下 以及对应的 xml 文件放到 com/cskaoyan/mall/selfmapper 文件夹下

3. 

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

2. 
