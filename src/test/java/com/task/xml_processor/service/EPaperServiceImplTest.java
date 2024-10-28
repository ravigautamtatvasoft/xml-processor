package com.task.xml_processor.service;

import com.task.xml_processor.Utils.XmlUtils;
import com.task.xml_processor.dto.*;
import com.task.xml_processor.entity.Epaper;
import com.task.xml_processor.exception.InvalidFileFormatException;
import com.task.xml_processor.exception.InvalidXMLException;
import com.task.xml_processor.repository.EpaperRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EPaperServiceImplTest {

    @InjectMocks
    private EpaperServiceImpl service;

    @Mock
    private XmlUtils xmlUtils;

    @Mock
    private EpaperRepository repo;

    @Test
    public void test_getAllEpaperList() throws Exception {

        List<Epaper> list = List.of(Epaper.builder().dpi(100l).filename("file.xml").height(100l).id(1l)
                        .newspaperName("name").uploadedAt(new Date()).width(100l).build(),
                Epaper.builder().dpi(100l).filename("file1.xml").height(100l).id(2l)
                        .newspaperName("name1").uploadedAt(new Date()).width(100l).build());

        when(repo.getAllEpaperList(anyString(), any(), any(), any()))
                .thenReturn(list);

        ResponseEntity<?> entity = service.getAllEpaperList(mock(HttpServletRequest.class), "name", "uploadedAt", true, System.currentTimeMillis(), System.currentTimeMillis() + 10000l, 0, 5);

        List<EpaperDto> actualList = (List<EpaperDto>) entity.getBody();

        assertEquals(list.size(), actualList.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getDpi(), actualList.get(i).getDpi());
            assertEquals(list.get(i).getFilename(), actualList.get(i).getFilename());
            assertEquals(list.get(i).getHeight(), actualList.get(i).getHeight());
            assertEquals(list.get(i).getNewspaperName(), actualList.get(i).getNewspaperName());
            assertEquals(list.get(i).getUploadedAt(), actualList.get(i).getUploadedAt());
            assertEquals(list.get(i).getWidth(), actualList.get(i).getWidth());

        }


    }

    @Test
    public void test_processXml() throws InvalidXMLException, InvalidFileFormatException, SAXException, JAXBException, IOException {
        Epaper epaper = Epaper.builder().dpi(100l).filename("file1.xml").height(100l).id(2l)
                .newspaperName("name1").uploadedAt(new Date()).width(100l).build();

        EpaperRequestDto request = new EpaperRequestDto();
        request.setDeviceInfo(new DeviceInfo(epaper.getNewspaperName(), epaper.getId() + "", new ScreenInfo(epaper.getWidth(), epaper.getHeight(), epaper.getDpi()), new OsInfo(), new AppInfo()));
        request.setGetPages(new GetPages());

        when(xmlUtils.validateXml(any(MultipartFile.class))).thenReturn(Boolean.valueOf(true));
        when(xmlUtils.parseXMLDocument(any(InputStream.class))).thenReturn(request);
        when(repo.save(any(Epaper.class))).thenReturn(epaper);

        Path xmlFilePath = new ClassPathResource("correct-epaper-request.xml").getFile().toPath();
        String xmlContent = new String(Files.readAllBytes(xmlFilePath));
        MockMultipartFile mockFile = new MockMultipartFile("xmlFile", "correct-epaper-request.xml",
                MediaType.APPLICATION_XML_VALUE, xmlContent.getBytes());

        ResponseEntity<?> entity = service.processXml(mock(HttpServletRequest.class), mockFile);
        EpaperDto requestDto = (EpaperDto) entity.getBody();
        assertEquals(requestDto.getNewspaperName(), epaper.getNewspaperName());
        assertEquals(requestDto.getWidth(), epaper.getWidth());
        assertEquals(requestDto.getHeight(), epaper.getHeight());
        assertEquals(requestDto.getDpi(), epaper.getDpi());

    }

}
