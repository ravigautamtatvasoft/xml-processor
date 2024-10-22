package com.task.xml_processor.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "getPages")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(EpaperRequestDto.class)
public class GetPages {

	@XmlAttribute
	private Long editionDefId;

	@XmlAttribute
	private Date publicationDate;
}