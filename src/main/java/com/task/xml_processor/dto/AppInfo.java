package com.task.xml_processor.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "appInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DeviceInfo.class)
public class AppInfo {

	@XmlElement
	private String newspaperName;

	@XmlElement
	private Float version;
}