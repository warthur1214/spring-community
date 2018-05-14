# spring-community

一. 请求的前提：

	1. 除被@AuthExclude标记过的api，所有必须携带signature接口；
	2. 除被@AuthExclude标记过的api, 所有必须携带Authorization请求头；
	3. 所有请求必须携带请求头Version=v1/v2...,保证api的版本控制；
	4. 需要携带signature的api，需要添加timestamp参数，格式为毫秒级时间戳；
	5. timestamp参数与服务器时间戳误差超过3000（数值待定），则判断timestamp非法；
	5. 请求体（request body）统一使用JSON数据格式
二. 请求方式：

	GET 用于获取数据；
	POST 用于添加新记录；
	PUT 用户修改记录；
	DELETE 用于删除记录
一. Authorization的生成与刷新：

	1. 生成通过 登录或注册 接口得到，并获得签名（signature）的生成密钥（secret）和
		刷新Authorization的refreshToken；
	2. 重复请求 登录或注册 接口，会得到第一次请求的接口数据，数据缓存在redis里；
	3. 如果Authorization过期或失效，须拿老Authorization和refreshToken请求刷新
		接口，获取新的Authorization；
	4. 过期时间是2小时（7200s）；
二. signature的生成：

	1. 获取请求的query string和request body（jsonObject）；
	2. 把query string 按&分割成数组requestList；
	3. 把jsonObject的对象按key=value的格式放入requestList；
	4. 若jsonObject里的value是object或array，则value取object或array的lentgh；
	5. 对requestlist数组按值做正序排列，并按&拼接成string生成明文校验体；
	6. 对明文校验体做base64加密，生成密文校验体；
	6. 对 “密钥（secret）+ : + 密文校验体” 做sha1加密生成signature；
三. 签名过程示例：

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
			"userInfo=2"
		]
	3. 生成的明文校验体为：
	code=033PiWQz1GY7Ae0HIAOz1WsYQz1PiWQd&orderList=3&pageNum=1&pageSize=10&smsCode=1267&tel=18516599223&userInfo=2
	4. 拼接密钥：
		123456:Y29kZT0wMzNQaVdRejFHWTdBZTBISUFPejFXc1lRejFQaVdRZCZvcmRlckxpc3Q9MyZwYWdlTnVtPTEmcGFnZVNpemU9MTAmc21zQ29kZT0xMjY3JnRlbD0xODUxNjU5OTIyMyZ1c2VySW5mbz0y
		
	5 sha1加密后生成signature：
		887953ccf5a4244dd38934a2920762da699b02e11faa18eb0aeabf58aaebeea2
		