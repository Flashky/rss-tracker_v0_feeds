package com.flashk.apis.rsstracker.ws.rssvalidator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "feedvalidationresponse", namespace = "http://www.w3.org/2005/10/feed-validator")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeedValidationResponse {
	
	@XmlElement(namespace = "http://www.w3.org/2005/10/feed-validator")
	private Boolean validity;
	
	@XmlElement(namespace = "http://www.w3.org/2005/10/feed-validator")
	private Errors errors;
}
