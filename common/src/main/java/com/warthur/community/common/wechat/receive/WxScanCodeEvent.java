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
		WxEventKeyMsg.class
})
@Data
public class WxScanCodeEvent extends WxEventKeyMsg {
	private static final long serialVersionUID = 793043526165980782L;

	@XmlElement(name = "ScanCodeInfo")
	private String scanCodeInfo;
	@XmlElement(name = "ScanType")
	private String scanType;
	@XmlElement(name = "ScanResult")
	private String scanResult;
}
