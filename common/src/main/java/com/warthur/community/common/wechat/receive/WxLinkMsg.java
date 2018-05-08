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
public class WxLinkMsg extends WxBaseMsg {
	private static final long serialVersionUID = -833184469631052074L;

	@XmlElement(name = "Title")
	private String title;
	@XmlElement(name = "Description")
	private String description;
	@XmlElement(name = "Url")
	private String url;
	@XmlElement(name = "MsgId")
	private String msgId;
}
