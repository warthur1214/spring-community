package com.warthur.community.common.wechat.receive;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by warth on 2018/3/13.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class WxRecvDecrypt implements Serializable {

	private static final long serialVersionUID = -5731067219158818068L;

	@XmlElement(name = "ToUserName")
	private String toUserName;
	@XmlElement(name = "FromUserName")
	private String fromUserName;
	@XmlElement(name = "CreateTime")
	private String createTime;
	@XmlElement(name = "MsgType")
	private String msgType;
	@XmlElement(name = "Content")
	private String content;
	@XmlElement(name = "MsgId")
	private String msgId;
}
