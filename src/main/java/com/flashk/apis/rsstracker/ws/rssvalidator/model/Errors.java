package com.flashk.apis.rsstracker.ws.rssvalidator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="errors", namespace="http://www.w3.org/2005/10/feed-validator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Errors {

	@XmlElement(name = "errorcount", namespace = "http://www.w3.org/2005/10/feed-validator")
	private Integer errorCount;
	
	@XmlElement(name = "errorlist", namespace = "http://www.w3.org/2005/10/feed-validator")
	private ErrorList errorList;
}
