package com.flashk.apis.rsstracker.ws.rssvalidator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="Envelope", namespace="http://www.w3.org/2003/05/soap-envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class Envelope {

	@XmlElement(name = "Body", namespace="http://www.w3.org/2003/05/soap-envelope")
	private Body body;
}
