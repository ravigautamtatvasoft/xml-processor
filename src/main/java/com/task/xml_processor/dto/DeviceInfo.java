package com.task.xml_processor.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "deviceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(EpaperRequestDto.class)
public class DeviceInfo {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String id;

	@XmlElementRef(name = "screenInfo")
	private ScreenInfo screenInfo;

	@XmlElementRef(name = "osInfo")
	private OsInfo osInfo;

	@XmlElementRef(name = "appInfo")
	private AppInfo appInfo;
}