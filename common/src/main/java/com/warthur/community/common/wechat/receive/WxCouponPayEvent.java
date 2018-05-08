package com.warthur.community.common.wechat.receive;

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
public class WxCouponPayEvent extends WxEventMsg {
	private static final long serialVersionUID = 5047457024845129774L;

	@XmlElement(name = "CardId")
	private String cardId;
	@XmlElement(name = "UserCardCode")
	private String userCardCode;
	@XmlElement(name = "TransId")
	private String transId;
	@XmlElement(name = "LocationId")
	private String locationId;
	@XmlElement(name = "Fee")
	private String fee;
	@XmlElement(name = "OriginalFee")
	private String originalFee;
}
