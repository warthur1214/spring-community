package com.warthur.common.util;

import com.warthur.common.wechat.BaseXml;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

/**
 * Created by warth on 2018/3/12.
 */
@Slf4j
public final class JaxbUtil {

	//xml转换成bean
	@SafeVarargs
	public static <T> T parseToBean(String xmlstr, Class<? extends BaseXml>... beans) {
		T bean = null;
		try {
			bean = (T) beans[0].newInstance();
			JAXBContext jaxbContext = JAXBContext.newInstance(beans);
			Unmarshaller um = jaxbContext.createUnmarshaller();
			bean = (T) um.unmarshal(new ByteArrayInputStream(xmlstr.getBytes()));
		} catch (JAXBException e) {
			log.error("解析xml失败！");
		} catch (IllegalAccessException | InstantiationException e) {
			log.error("对象初始化失败！");
		}

		return bean;
	}

	//bean 转换成 xml
	public static String parseToXml(BaseXml bean) {
		JAXBContext context;
		StringWriter sw = new StringWriter();
		try {
			context = JAXBContext.newInstance(bean.getClass());
			Marshaller m = context.createMarshaller();

			// remove xml header
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			m.marshal(bean, sw);

		} catch (JAXBException e) {
			log.error("bean转xml失败！", e);
		}
		return sw.toString();
	}

}
