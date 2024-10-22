package com.task.xml_processor;

import com.task.xml_processor.controller.EpaperRequestController;
import com.task.xml_processor.service.EpaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EpaperRequestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private EpaperService epaperService;

    @InjectMocks
    private EpaperRequestController epaperController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(epaperController).build();
    }

    @Test
    void testProcessXml_ValidXmlFile() throws Exception {
        Path xmlFilePath = new ClassPathResource("correct-epaper-request.xml").getFile().toPath();
        String xmlContent = new String(Files.readAllBytes(xmlFilePath));

        MockMultipartFile mockFile = new MockMultipartFile("paperXml", "correct-epaper-request.xml",
                MediaType.APPLICATION_XML_VALUE, xmlContent.getBytes());

        Mockito.lenient().when(epaperService.processXml(any(), any())).thenReturn(ResponseEntity.ok().build());


        mockMvc.perform(multipart("/api/processXml")
                        .file(mockFile))
                .andExpect(status().isOk());
    }

    @Test
    void testProcessXml_InvalidXmlFile() throws Exception {
        Path xmlFilePath = new ClassPathResource("incorrect-epaper-request.xml").getFile().toPath();
        String xmlContent = new String(Files.readAllBytes(xmlFilePath));

        MockMultipartFile mockFile = new MockMultipartFile("paperXml", "incorrect-epaper-request.xml",
                MediaType.APPLICATION_XML_VALUE, xmlContent.getBytes());

        Mockito.lenient().when(epaperService.processXml(any(), any())).thenReturn(ResponseEntity.badRequest().build());

        mockMvc.perform(multipart("/api/processXml")
                        .file(mockFile))
                .andExpect(status().isBadRequest());
    }
}
