package io.red.relatoriospoc.services;

import io.red.relatoriospoc.relatorios.repositories.EmployeeRepository;
import io.red.relatoriospoc.utils.ReportImageUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    ReportImageUtil reportImageUtil;
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;
    @Mock
    HttpServletResponse response;
    @Mock
    OutputStream outputStream;

    //@Test
    void testGerarRelatorioWhenCalledThenReportGenerated() throws Exception {
        when(response.getOutputStream()).thenReturn((ServletOutputStream) outputStream);

        employeeService.gerarRelatorio(response, fileName, parametros, declaracoes);

        verify(response).setContentType("application/pdf");
        verify(response).addHeader("Content-disposition", "attachment;filename=relatorio.pdf");
        verify(response).getOutputStream();
        verify(response).flushBuffer();
        verifyNoMoreInteractions(response);
    }

    @Test
    void testGerarRelatorioWhenResponseNullThenNoInteractions() throws Exception {
        employeeService.gerarRelatorio(null, fileName, parametros, declaracoes);
        verifyNoInteractions(response);
    }

    @Test
    void testGerarRelatorioWhenGetOutputStreamThrowsExceptionThenNoReportGenerated() throws Exception {
        when(response.getOutputStream()).thenThrow(new IOException());

        assertThrows(Exception.class, () -> employeeService.gerarRelatorio(response, fileName, parametros, declaracoes));

        verify(response).getOutputStream();
        verifyNoMoreInteractions(response);
    }

    @Test
    void deveGerarRelatorio() throws Exception {
        HttpServletResponse response = null;
        employeeService.gerarRelatorio(response, fileName, parametros, declaracoes);
    }
}
