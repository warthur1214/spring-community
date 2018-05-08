package com.warthur.common.wechat.receive;

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
		WxEventMsg.class
})
@Data
public class WxTemplateResultEvent extends WxEventMsg {
	private static final long serialVersionUID = -8980500239785342637L;

	@XmlElement(name = "MsgID")
	private Long msgId;
	@XmlElement(name = "Status")
	private String status;
}
