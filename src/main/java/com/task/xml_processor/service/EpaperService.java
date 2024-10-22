package com.task.xml_processor.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import java.io.IOException;

/**
 * EpaperService
 *
 * @author Ravi.Gautam
 * @since 22-October-2024
 * @version 1.0
 *
 */

public interface EpaperService {

    ResponseEntity<?> getAllEpaperList(HttpServletRequest request, String search, String sortBy, Boolean order, Long fromDate, Long toDate, Integer pageNumber, Integer pageSize) throws Exception;

    ResponseEntity<?> processXml(HttpServletRequest request, MultipartFile xmlFile) throws IOException, SAXException, JAXBException;
}
