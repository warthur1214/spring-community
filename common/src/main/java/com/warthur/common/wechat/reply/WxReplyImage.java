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
public class WxReplyImage extends WxBaseMsg {
	private static final long serialVersionUID = 5327151270657859419L;

	@XmlElement(name = "MediaId")
	private String mediaId;

	public WxReplyImage(WxBaseMsg baseMsg, String mediaId) {
		super(baseMsg, "image");
		this.mediaId = mediaId;
	}
}
