package com.warthur.community.common.wechat.reply;

import com.warthur.community.common.wechat.WxBaseMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

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
public class WxReplyVoice extends WxBaseMsg {
	private static final long serialVersionUID = 8908604197905632533L;

	@XmlElement(name = "Title")
	private String title;
	@XmlElement(name = "Description")
	private String description;
	@XmlElement(name = "MusicURL")
	private String musicURL;
	@XmlElement(name = "HQMusicUrl")
	private String hQMusicUrl;
	@XmlElement(name = "ThumbMediaId")
	private String thumbMediaId;

	public WxReplyVoice(WxBaseMsg baseMsg, String title, String description, String musicURL, String hQMusicUrl, String thumbMediaId) {
		super(baseMsg, "voice");
		this.title = title;
		this.description = description;
		this.musicURL = musicURL;
		this.hQMusicUrl = hQMusicUrl;
		this.thumbMediaId = thumbMediaId;
	}
}
