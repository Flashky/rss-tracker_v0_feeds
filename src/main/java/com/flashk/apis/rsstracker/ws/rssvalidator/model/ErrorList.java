package com.flashk.apis.rsstracker.ws.rssvalidator.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="errorlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorList {

	@XmlElement(name = "error")
	private List<Error> errorList;
}
