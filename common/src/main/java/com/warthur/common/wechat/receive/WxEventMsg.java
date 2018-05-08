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
public class WxEventMsg extends WxBaseMsg {

	private static final long serialVersionUID = -7172229442142707278L;
	@XmlElement(name = "Event")
	private String event;
}
