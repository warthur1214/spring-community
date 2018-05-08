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
public class WxCouponConsumeEvent extends WxEventMsg {
	private static final long serialVersionUID = -7011034432832034997L;

	@XmlElement(name = "CardId")
	private String cardId;
	@XmlElement(name = "UserCardCode")
	private String userCardCode;
	@XmlElement(name = "ConsumeSource")
	private String consumeSource;
	@XmlElement(name = "LocationName")
	private String locationName;
	@XmlElement(name = "StaffOpenId")
	private String staffOpenId;
	@XmlElement(name = "VerifyCode")
	private String verifyCode;
	@XmlElement(name = "RemarkAmount")
	private String remarkAmount;
	@XmlElement(name = "OuterStr")
	private String outerStr;
}
