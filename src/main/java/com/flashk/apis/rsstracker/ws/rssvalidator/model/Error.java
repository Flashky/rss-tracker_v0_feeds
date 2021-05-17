package com.flashk.apis.rsstracker.ws.rssvalidator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error {

	@XmlElement
	private String level;
	
	private String type;
	
	private String text;
	
	private String status;
}
