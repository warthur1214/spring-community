package com.warthur.community.common.wechat.receive;

import com.warthur.community.common.wechat.WxBaseMsg;
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
public class WxImageMsg extends WxBaseMsg {
	private static final long serialVersionUID = -1323599799491483734L;

	@XmlElement(name = "MediaId")
	private String mediaId;
	@XmlElement(name = "PicUrl")
	private String picUrl;
	@XmlElement(name = "MsgId")
	private String msgId;
}
