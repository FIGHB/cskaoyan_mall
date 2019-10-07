## 项目名称：

cskaoyan-mall

## 小组成员：

李锐、赵亚云、王小凤、杨磊、周榆淮、国旭、陈武



## 项目分工

### 后台分工

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
|   首页   |          |   李锐   |

### 前台接口

```java
  //周榆淮
  IndexUrl: WxApiRoot + 'home/index', //首页数据接口(周榆淮 done)						
  CatalogList: WxApiRoot + 'catalog/index', //分类目录全部分类数据接口(李锐 done)
  CatalogCurrent: WxApiRoot + 'catalog/current', //分类目录当前分类数据接口 (周榆淮 done)
  
  BrandList: WxApiRoot + 'brand/list', //品牌列表（周榆淮 done）			 							
  BrandDetail: WxApiRoot + 'brand/detail', //品牌详情 （周榆淮 done）

  //杨磊
  GoodsCount: WxApiRoot + 'goods/count', //统计商品总数(李锐 done)					
  GoodsList: WxApiRoot + 'goods/list', //获得商品列表(杨磊 done)
  GoodsCategory: WxApiRoot + 'goods/category', //获得分类数据 (杨磊 done)		 				
  GoodsDetail: WxApiRoot + 'goods/detail', //获得商品的详情(杨磊 done)
  GoodsRelated: WxApiRoot + 'goods/related', //商品详情页的关联商品（大家都在看）(杨磊 done 未测试)

  //王小凤
  CollectList: WxApiRoot + 'collect/list', //收藏列表(doing)			 
  CollectAddOrDelete: WxApiRoot + 'collect/addordelete', //添加或取消收藏(doing)

  CommentList: WxApiRoot + 'comment/list', //评论列表			(doing)		
  CommentCount: WxApiRoot + 'comment/count', //评论总数(doing)
  CommentPost: WxApiRoot + 'comment/post', //发表评论(doing)

  //国旭
  TopicList: WxApiRoot + 'topic/list', //专题列表   （国旭 done）								
  TopicDetail: WxApiRoot + 'topic/detail', //专题详情   （国旭 done）
  TopicRelated: WxApiRoot + 'topic/related', //相关专题	 （国旭 done）	
  
  ExpressQuery: WxApiRoot + 'express/query', //物流查询  （国旭 未找到）

  RegionList: WxApiRoot + 'region/list', //获取区域列表   （国旭 done）

  //李锐
  SearchIndex: WxApiRoot + 'search/index', //搜索关键字 (李锐 done)	
  SearchResult: WxApiRoot + 'search/result', //搜索结果 (李锐 未找到)
  SearchHelper: WxApiRoot + 'search/helper', //搜索帮助 (李锐 done)
  SearchClearHistory: WxApiRoot + 'search/clearhistory', //搜索历史清楚 (李锐 done)

  UserFormIdCreate: WxApiRoot + 'formid/create', //用户FromId，用于发送模版消息 (李锐 未找到)

  //赵亚云
  AddressList: WxApiRoot + 'address/list', //收货地址列表			done	
  AddressDetail: WxApiRoot + 'address/detail', //收货地址详情		done
  AddressSave: WxApiRoot + 'address/save', //保存收货地址 done
  AddressDelete: WxApiRoot + 'address/delete', //保存收货地址 done 

  //陈武
  AuthLoginByWeixin：WxApiRoot +'auth / login_by_weixin'，//微信登錄（陳武done）			
  AuthLoginByAccount：WxApiRoot +'auth / login'，//賬號登錄（陳武done）
  AuthLogout：WxApiRoot +'auth / logout'，//賬號登出（陈武done）
  AuthRegister：WxApiRoot +'auth / register'，//賬號註冊（陈武done）
  AuthReset：WxApiRoot +'auth / reset'，//賬號密碼重置（陈武done）
  AuthRegisterCaptcha: WxApiRoot + 'auth/regCaptcha', //验证码（陈武done）
  AuthBindPhone: WxApiRoot + 'auth/bindPhone', //绑定微信手机号（陈武doing）


  CartList: WxApiRoot + 'cart/index', //获取购物车的数据	（陈武done）						
  CartAdd: WxApiRoot + 'cart/add', // 添加商品到购物车（陈武done）
  CartFastAdd: WxApiRoot + 'cart/fastadd', // 立即购买商品（陈武done）
  CartUpdate: WxApiRoot + 'cart/update', // 更新购物车的商品（陈武done）
  CartDelete: WxApiRoot + 'cart/delete', // 删除购物车的商品（陈武done）
  CartChecked: WxApiRoot + 'cart/checked', // 选择或取消选择商品	（陈武done）		
  CartGoodsCount: WxApiRoot + 'cart/goodscount', // 获取购物车商品件数（陈武done）
  CartCheckout: WxApiRoot + 'cart/checkout', // 下单前信息确认(陈武done)
  
  
  OrderSubmit: WxApiRoot + 'order/submit', // 提交订单（陈武done）				
  OrderPrepay: WxApiRoot + 'order/prepay', // 订单的预支付会话（陈武done）
  OrderList: WxApiRoot + 'order/list', //订单列表（陈武doing）
  OrderDetail: WxApiRoot + 'order/detail', //订单详情
  OrderCancel: WxApiRoot + 'order/cancel', //取消订单
  OrderRefund: WxApiRoot + 'order/refund', //退款取消订单
  OrderDelete: WxApiRoot + 'order/delete', //删除订单
  OrderConfirm: WxApiRoot + 'order/confirm', //确认收货
  OrderGoods: WxApiRoot + 'order/goods', // 代评价商品信息
  OrderComment: WxApiRoot + 'order/comment', // 评价订单商品信息


  FeedbackAdd: WxApiRoot + 'feedback/submit', //添加反馈   （国旭 done）
  FootprintList: WxApiRoot + 'footprint/list', //足迹列表  国旭（done）
  FootprintDelete: WxApiRoot + 'footprint/delete', //删除足迹  （国旭 未找到）


  GroupOnList: WxApiRoot + 'groupon/list', //团购列表 （周榆淮 done）
  GroupOn: WxApiRoot + 'groupon/query', //团购API-查询 （找不到）
  GroupOnMy: WxApiRoot + 'groupon/my', //团购API-我的团购
  GroupOnDetail: WxApiRoot + 'groupon/detail', //团购API-详情
  GroupOnJoin: WxApiRoot + 'groupon/join', //团购API-详情 （找不到）

  CouponList: WxApiRoot + 'coupon/list', //优惠券列表(李锐 doing)
  CouponMyList: WxApiRoot + 'coupon/mylist', //我的优惠券列表(李锐 done)
  CouponSelectList: WxApiRoot + 'coupon/selectlist', //当前订单可用优惠券列表(李锐 doing)
  CouponReceive: WxApiRoot + 'coupon/receive', //优惠券领取(李锐 done)
  CouponExchange: WxApiRoot + 'coupon/exchange', //优惠券兑换(李锐 doing)

  StorageUpload: WxApiRoot + 'storage/upload', //图片上传,（王小凤 done)

  UserIndex: WxApiRoot + 'user/index', //个人页面用户相关信息(李锐 done)
```



## 注意事项

1. 每次提交格式

    ```
    git commit -m "author:name message"
    ```


2. 两个包 com.cskaoyan.mall.bean 和 com.cskaoyan.mall.mapper 以及文件夹 resources\com\cskaoyan\mapper 下的内容不允许做任何修改。

    自己的 bean 放到 vo 包下(), 自己的和数据库相关的操作可以在mapper下自建一个包，然后在这个包内写自己的mapper接口，并将对应的 xxxMapper.xml放到 resources 下的对应目录

3. 所有的操作都是在 dev 上进行的，请不要去 master 主干上做任何操作。

4. 每个类上自己写明作者，不要修改其他作者的文件，如果希望别人的类提供对应的方法，可以去找对应的人添加。

5. 前台登录账号密码：

    ```
    账号 wx
    密码 admin123
    ```

    

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

3. 在 xxxMapper.xml 中调用查询语句时，如果不写 resultType或者 resultMap 就会出现如下错误：

    ```
    org.apache.ibatis.executor.ExecutorException: A query was run and no Result Maps were found for the Mapped Statement
    ```

    所以所有的查询语句必须写返回类型，不然就会在结果为空的时候报如上错误

3. 通过逆向工程获得的 Mapper 中如果有的包含 mysql 的关键字，则需要手动添加反引号
