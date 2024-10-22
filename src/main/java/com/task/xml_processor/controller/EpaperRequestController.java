package com.task.xml_processor.controller;

import com.task.xml_processor.service.EpaperService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * EpaperRequestController
 *
 * @author Ravi.Gautam
 * @see EpaperService
 * @since 22-October-2024
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/api")
public class EpaperRequestController {

    @Autowired
    private EpaperService epaperService;

    /** API used to upload and save the XML data.
     * @param request
     * @param xmlFile
     * @return ResponseEntity
     * @throws IOException
     * @throws SAXException
     * @throws JAXBException
    */
    @PostMapping("/processXml")
    public ResponseEntity<?> processPaper(HttpServletRequest request, @RequestParam MultipartFile xmlFile)
            throws IOException, SAXException, JAXBException {
        return epaperService.processXml(request, xmlFile);
    }

    /** API used to upload and save the XML data.
     * @param request
     * @param fromDate
     * @param toDate
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param order
     * @param search
     * @return ResponseEntity
     * @throws Exception
     */
    @GetMapping("/getEpapers")
    public ResponseEntity<?> getPapers(HttpServletRequest request,
                                       @RequestParam(value = "fromDate", required = false) Long fromDate,
                                       @RequestParam(value = "toDate", required = false) Long toDate,
                                       @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                       @RequestParam(value = "order", defaultValue = "false", required = false) Boolean order,
                                       @RequestParam(value = "search", defaultValue = "", required = false) String search) throws Exception {
        return epaperService.getAllEpaperList(request, search, sortBy, order, fromDate, toDate, pageNo, pageSize);
    }
}
