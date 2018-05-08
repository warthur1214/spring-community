package com.warthur.community.common.wechat.receive;

import com.warthur.community.common.wechat.WxBaseMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * Created by warth on 2018/4/3.
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
public class WxVideoMsg extends WxBaseMsg {
	private static final long serialVersionUID = -8483016309769276328L;

	@XmlElement(name = "MediaId")
	private String mediaId;
	@XmlElement(name = "ThumbMediaId")
	private String thumbMediaId;
	@XmlElement(name = "MsgId")
	private String msgId;
}
