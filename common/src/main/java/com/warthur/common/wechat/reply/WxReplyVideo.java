package com.warthur.common.wechat.reply;

import com.warthur.common.wechat.WxBaseMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class WxReplyVideo extends WxBaseMsg {
	private static final long serialVersionUID = 3278054558819583104L;

	@XmlElement(name = "MediaId")
	private String mediaId;
	@XmlElement(name = "Title")
	private String title;
	@XmlElement(name = "Description")
	private String description;

	public WxReplyVideo(WxBaseMsg baseMsg, String mediaId, String title, String description) {
		super(baseMsg, "video");
		this.mediaId = mediaId;
		this.title = title;
		this.description = description;
	}
}
