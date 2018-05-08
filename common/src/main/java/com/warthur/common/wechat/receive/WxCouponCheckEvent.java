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
public class WxCouponCheckEvent extends WxEventMsg {

	private static final long serialVersionUID = -5622596095759959776L;
	@XmlElement(name = "CardId")
	private String cardId;
	@XmlElement(name = "RefuseReason")
	private String refuseReason;
}
