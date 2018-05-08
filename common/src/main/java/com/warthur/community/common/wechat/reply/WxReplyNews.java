package com.warthur.community.common.wechat.reply;

import com.warthur.community.common.wechat.WxBaseMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by warth on 2018/4/4.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
		WxBaseMsg.class
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxReplyNews extends WxBaseMsg {
	private static final long serialVersionUID = 7692106956408980642L;

	@XmlElement(name = "ArticleCount")
	private Integer articleCount;

	@XmlElementWrapper(name="Articles")
	@XmlElement(name = "item")
	private List<WxNewsArticle> articles;

	public WxReplyNews(WxBaseMsg baseMsg, List<WxNewsArticle> articles) {
		super(baseMsg, "news");
		this.articleCount = articles.size();
		this.articles = articles;
	}


}
