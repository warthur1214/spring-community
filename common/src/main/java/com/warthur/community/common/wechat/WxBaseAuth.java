package com.warthur.community.common.wechat;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by warth on 2018/3/12.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxBaseAuth extends BaseXml {

	private static final long serialVersionUID = -4563988350140076509L;

	@XmlElement(name = "AppId")
	private String appId;
	@XmlElement(name = "CreateTime")
	private String createTime;
	@XmlElement(name = "InfoType")
	private String infoType;
}
