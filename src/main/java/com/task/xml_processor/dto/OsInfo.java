package com.task.xml_processor.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "osInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DeviceInfo.class)
public class OsInfo {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private Float version;
}