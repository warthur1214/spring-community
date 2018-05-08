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
		WxEventKeyMsg.class
})
@Data
public class WxScanTicketEvent extends WxEventKeyMsg {
	private static final long serialVersionUID = 914136554872952012L;

	@XmlElement(name = "Ticket")
	private String ticket;
}
