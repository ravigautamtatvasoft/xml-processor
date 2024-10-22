package com.task.xml_processor.Utils;

import com.task.xml_processor.dto.EpaperRequestDto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

/**
 * XML Utils class for validating and parsing XML.
 *
 * @author Ravi.Gautam
 * @since 22-Oct-2024
 * @version 1.0
 *
 */

@Component
public class XmlUtils {

    private Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    /** This util method validate the XML
     * @param xml
     * @return boolean
     * @throws SAXException
     */
    public boolean validateXml(MultipartFile xml) throws SAXException {
        if (xml.getContentType().contains("text/xml") || xml.getContentType().contains("application/xml")) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Source schemaFile = new StreamSource(getFileAsStream("schema.xsd"));
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            try {
                InputStream xmlInputStream = xml.getInputStream();
                validator.validate(new StreamSource(xmlInputStream));
            } catch (IOException | SAXException e) {
                LOGGER.error("XML is not valid", e);
                return false;
            }
        }
        LOGGER.info("XML validation passed");
        return true;
    }

    /** This util method parse the data to EpaperRequestDTO
     * @param xmlInputStream
     * @return boolean
     * @throws JAXBException
     */
    public EpaperRequestDto parseXMLDocument(InputStream xmlInputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(EpaperRequestDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        EpaperRequestDto epaperRequestDto = (EpaperRequestDto) unmarshaller.unmarshal(xmlInputStream);
        LOGGER.info("XML parsed successfully");
        return epaperRequestDto;
    }

    /** This util method get the file as InputStream.
     * @param fileName
     * @return InputStream
     */
    private InputStream getFileAsStream(String fileName) {
        ClassLoader classLoader = XmlUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
