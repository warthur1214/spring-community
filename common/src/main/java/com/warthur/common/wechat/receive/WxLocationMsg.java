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
public class WxLocationMsg extends WxBaseMsg {
	private static final long serialVersionUID = 6329996160601903530L;

	@XmlElement(name = "Location_X")
	private String locationX;
	@XmlElement(name = "Location_Y")
	private String locationY;
	@XmlElement(name = "Scale")
	private String scale;
	@XmlElement(name = "Label")
	private String label;
	@XmlElement(name = "MsgId")
	private String msgId;
}
