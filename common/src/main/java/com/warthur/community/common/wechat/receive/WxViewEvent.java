package com.warthur.community.common.wechat.receive;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

/**
 * Created by warth on 2018/4/4.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
		WxEventKeyMsg.class
})
@Data
public class WxViewEvent extends WxEventKeyMsg {
	private static final long serialVersionUID = 1547175720583385534L;

	@XmlElement(name = "MenuID")
	private String menuID;
}
