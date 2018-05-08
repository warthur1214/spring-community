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
public class WxCouponGetEvent extends WxEventMsg {
	private static final long serialVersionUID = -2506262723774483115L;

	@XmlElement(name = "CardId")
	private String cardId;
	@XmlElement(name = "IsGiveByFriend")
	private Integer isGiveByFriend;
	@XmlElement(name = "FriendUserName")
	private String friendUserName;
	@XmlElement(name = "UserCardCode")
	private String userCardCode;
	@XmlElement(name = "OldUserCardCode")
	private String oldUserCardCode;
	@XmlElement(name = "OuterStr")
	private String outerStr;
	@XmlElement(name = "IsRestoreMemberCard")
	private Integer isRestoreMemberCard;
}
