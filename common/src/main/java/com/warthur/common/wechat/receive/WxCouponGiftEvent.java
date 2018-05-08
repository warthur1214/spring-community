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
public class WxCouponGiftEvent extends WxEventMsg {
	private static final long serialVersionUID = 1058580382147707260L;

	@XmlElement(name = "CardId")
	private String CardId;
	@XmlElement(name = "FriendUserName")
	private String FriendUserName;
	@XmlElement(name = "UserCardCode")
	private String UserCardCode;
	@XmlElement(name = "IsReturnBack")
	private Integer IsReturnBack;
	@XmlElement(name = "IsChatRoom")
	private Integer IsChatRoom;
}
