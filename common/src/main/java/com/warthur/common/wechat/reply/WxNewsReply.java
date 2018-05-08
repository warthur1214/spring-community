package com.warthur.common.wechat.reply;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Component
public class WxNewsReply {

	private static String IMAGE_DOMAIN;
	private static String COMMON_DOMAIN;
	private static String WX_COMPONENT_APPID;

	@Value("${softvan.common.image.domain}")
	public void setImageDomain(String imageDomain) {
		IMAGE_DOMAIN = imageDomain;
	}

	@Value("${softvan.common.domain.url}")
	public void setCommonDomain(String commonDomain) {
		COMMON_DOMAIN = commonDomain;
	}

	@Value("${softvan.wx.component.appid}")
	public void setWxComponentAppid(String wxComponentAppid) {
		WX_COMPONENT_APPID = wxComponentAppid;
	}

	public static List<WxNewsArticle> getDefaultReplyV3(String appId) {

		return Arrays.asList(
				WxNewsArticle.builder().title("感谢您的关注，我们将竭诚为您服务！")
						.description("").picUrl(IMAGE_DOMAIN + "img/news/news_main.jpg")
						.url(getWechatUrl(appId, "mycenter_index.html?appid=" + appId)).build(),
				WxNewsArticle.builder().title("维保预约")
						.description("").picUrl(IMAGE_DOMAIN + "img/news/weibaoyuyue.jpg")
						.url(getWechatUrl(appId, "menu_init.html?type=2&appid=" + appId)).build(),
				WxNewsArticle.builder().title("保险询价")
						.description("").picUrl(IMAGE_DOMAIN + "img/news/baoxianxunjia.jpg")
						.url(getWechatUrl(appId, "menu_init.html?type=4&appid=" + appId)).build(),
				WxNewsArticle.builder().title("活动促销")
						.description("").picUrl(IMAGE_DOMAIN + "img/news/youhuicuxiao.jpg")
						.url(getWechatUrl(appId, "card-center.html?appid=" + appId)).build(),
				WxNewsArticle.builder().title("试驾预约")
						.description("").picUrl(IMAGE_DOMAIN + "img/news/xincheshijia.jpg")
						.url(getWechatUrl(appId, "menu_init.html?type=1&appid=" + appId)).build()
		);
	}

	public static String getWechatUrl(String appId, String uri) {

		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}" +
				"&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1&component_appid={2}" +
				"#wechat_redirect";
		return MessageFormat.format(url, appId, URLEncoder.encode(COMMON_DOMAIN + uri), WX_COMPONENT_APPID);
	}
}
