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
public class WxCouponActiveEvent extends WxEventMsg {
	private static final long serialVersionUID = 1812372384961942172L;

	@XmlElement(name = "CardId")
	private String cardId;
	@XmlElement(name = "UserCardCode")
	private String userCardCode;
}
