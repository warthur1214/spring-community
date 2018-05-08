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
public class WxTextMsg extends WxBaseMsg {
	private static final long serialVersionUID = -4838512994235827898L;

	@XmlElement(name = "MsgId")
	private String msgId;
	@XmlElement(name = "Content")
	private String content;
}
