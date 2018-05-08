package com.warthur.common.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by warth on 2018/4/3.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class WxBaseMsg extends BaseXml {

	private static final long serialVersionUID = 781466756824405488L;

	@XmlElement(name = "ToUserName")
	private String toUserName;
	@XmlElement(name = "FromUserName")
	private String fromUserName;
	@XmlElement(name = "CreateTime")
	private Long createTime;
	@XmlElement(name = "MsgType")
	private String msgType;

	// 构造回复消息
	public WxBaseMsg(WxBaseMsg baseMsg, String msgType) {
		this.toUserName = baseMsg.getFromUserName();
		this.fromUserName = baseMsg.getToUserName();
		this.createTime = baseMsg.getCreateTime();
		this.msgType = msgType;
	}
}
