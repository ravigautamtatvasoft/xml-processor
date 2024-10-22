package com.task.xml_processor.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "screenInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DeviceInfo.class)
public class ScreenInfo {

	@XmlAttribute
	private Long width;

	@XmlAttribute
	private Long height;

	@XmlAttribute
	private Long dpi;
}