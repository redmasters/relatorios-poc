package io.red.relatoriospoc.services;

import io.red.relatoriospoc.model.Employee;
import io.red.relatoriospoc.relatorios.repositories.EmployeeRepository;
import io.red.relatoriospoc.utils.ReportImageUtil;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private static final String RELATORIO = "relatorios/relatorio.jasper";
    private static final String DECLARACAO = "relatorios/declaracao.jasper";
    private static final String IMAGEM = "relatorios/imagens/logo.png";
    private final EmployeeRepository employeeRepository;
    private final ReportImageUtil reportImageUtil;

    public EmployeeService(EmployeeRepository employeeRepository, ReportImageUtil reportImageUtil) {
        this.employeeRepository = employeeRepository;
        this.reportImageUtil = reportImageUtil;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void gerarRelatorio(HttpServletResponse response, String fileName,
                               Map<String, Object> parametros, List<?> declaracoes) throws Exception {

        var relativePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource(DECLARACAO)).getPath();
        logger.info("relativePath: {}", relativePath); var file = new File(relativePath);
        logger.info("file: {}", file.getPath());

        var dataSource = new JRBeanCollectionDataSource(declaracoes, false);

        var jasperPrint = JasperFillManager.fillReport(file.getPath(), parametros, dataSource);
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
        var stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        response.flushBuffer();
    }
}
