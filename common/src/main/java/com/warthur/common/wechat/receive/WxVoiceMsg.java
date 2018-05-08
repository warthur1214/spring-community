package com.warthur.common.wechat.receive;

import com.warthur.common.wechat.WxBaseMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class WxVoiceMsg extends WxBaseMsg {
	private static final long serialVersionUID = -1039340308871837135L;

	@XmlElement(name = "MediaId")
	private String mediaId;
	@XmlElement(name = "Format")
	private String format;
	@XmlElement(name = "MsgId")
	private String msgId;
}
