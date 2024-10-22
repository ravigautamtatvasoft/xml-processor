package com.task.xml_processor.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "epaperRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "epaperRequest")
public class EpaperRequestDto {

	@XmlElementRef(name = "deviceInfo")
	private DeviceInfo deviceInfo;

	@XmlElementRef(name = "getPages")
	private GetPages getPages;
}