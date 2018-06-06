# spring-community

一. 请求的前提：

    1. 除验证用户信息的接口，所有对后端接口的请求必须携带signature接口；
    2. 除验证用户信息的接口, 所有对后端接口的请求必须携带Authorization请求头；
    3. 所有请求必须携带请求头Version=v1/v2...,保证api的版本控制；
    4. 需要携带signature的api，需要添加timestamp参数，格式为毫秒级时间戳；
    5. timestamp参数与服务器时间戳误差超过3000（数值待定），则判断timestamp非法；
    6. 请求体（request body）统一使用JSON数据格式
    7. 请求的url里如果是 {key} 则为资源类型参数，替换为应传参数的值即可；
二. 请求方式：

    GET 用于获取数据；
    POST 用于添加新记录；
    PUT 用户修改记录；
    DELETE 用于删除记录
三. 响应体：
    
    1. 返回数据格式：
        status:     状态码；
        message:    请求或操作信息的结果；
        success:    布尔类型（true/false）
        desc:       用于请求参数的错误信息描述(null/string)；
        data:       获取的数据对象(null/array/object)
    2. status状态码规范：
        a. 200-500错误码取标准http状态码
        b. 系统自定义错误码取<=-1000的整数值
        c. 系统自定义成功码取>=1000的整数值
        d. 每个接口新增的错误码见api接口excel表返回值部分，如非
           必要不在以下状态码表中一一列出
    3. 状态码表：
        status      message
        200         请求成功
        201         创建或修改成功
        204         删除成功
        400         参数错误
        401         Authorization未认证
        403         禁止访问，Authorization异常
        404         未找到
        500         系统错误
        
        -1000       处理失败
        -1001       请求头Version非法
        -1002       请求参数signature非法
        -1003       请求参数timstamp非法
        1000        发送短信成功
        1001        用户绑定手机号成功
    4. 示例：
        a. 错误信息：
          {
            "status": 400,
            "message": "参数错误",
            "success": false,
            "desc": [
               "短信验证码不能为null或空串",
               "手机号格式非法"
            ],
            "data": null
          }
        b. 成功信息：
          {
            "status": 200,
            "message": "请求成功",
            "success": true,
            "data": {
              "userId": "xxxx",
              "userName": "xxxx"
            }
          }
        或者
          {
            "status": 1000,
            "message": "短信发送成功"
            "success": true,
          }
        
四. Authorization的生成与刷新：

    1. 生成通过 登录或注册 接口得到，并获得签名（signature）的生成密钥（secret）和
       刷新Authorization的refreshToken；
    2. 重复请求 登录或注册 接口，会得到第一次请求的接口数据，数据缓存在redis里；
    3. 如果Authorization过期或失效，须拿老Authorization和refreshToken请求刷新
       接口，获取新的Authorization；
    4. 过期时间是2小时（7200s）；
五. signature的生成：

    1. 获取请求的query string和request body（jsonObject）；
    2. 把query string 按&分割成数组requestList；
    3. 把jsonObject的对象按key=value的格式放入requestList；
    4. 若jsonObject里的value是object或array，则value取object或array的length；
    5. 对requestlist数组按值做正序排列，并按&拼接成string生成明文校验体；
    6. 对明文校验体做base64加密，生成密文校验体；
    6. 对 “密钥（secret）+ : + 密文校验体” 做sha1加密生成signature；
六. 签名过程示例：

    密钥：123456
    1. query string: timestamp=1526264228121&pageNum=1&pageSize=10
    2. request body:
        {
          "tel": "18516599223",
          "smsCode": "1267",
          "code": "033PiWQz1GY7Ae0HIAOz1WsYQz1PiWQd"
          "userInfo": {
            "userName": "吴先生",
            "openId": "ozNa35WheGh_Oy3JvUB8OoiotUes"
          },
          "orderList":[
            {"orderNo":11, "orderMoney": 100},
            {"orderNo":22, "orderMoney": 200},
            {"orderNo":33, "orderMoney": 99}
          ]
        }
    3. 生成requestList并排序后为：
        [
          "code=033PiWQz1GY7Ae0HIAOz1WsYQz1PiWQd",
          "orderList=3",
          "pageNum=1",
          "pageSize=10",
          "smsCode=1267",
          "tel=18516599223",
          "timestamp=1526264228121",
          "userInfo=2"
        ]
    3. 生成的明文校验体为：
       code=033PiWQz1GY7Ae0HIAOz1WsYQz1PiWQd&orderList=3&pageNum=1&pageSize=10&smsCode=1267&tel=18516599223&timestamp=1526264228121&userInfo=2
    4. 拼接密钥：
       123456:Y29kZT0wMzNQaVdRejFHWTdBZTBISUFPejFXc1lRejFQaVdRZCZvcmRlckxpc3Q9MyZwYWdlTnVtPTEmcGFnZVNpemU9MTAmc21zQ29kZT0xMjY3JnRlbD0xODUxNjU5OTIyMyZ1c2VySW5mbz0y
    5 sha1加密后生成signature：
       887953ccf5a4244dd38934a2920762da699b02e11faa18eb0aeabf58aaebeea2
        