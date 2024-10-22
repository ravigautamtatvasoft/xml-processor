package com.task.xml_processor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpaperDto {
    private String newspaperName;
    private Long width;
    private Long height;
    private Long dpi;
    private Date uploadedAt;
    private String filename;
}
