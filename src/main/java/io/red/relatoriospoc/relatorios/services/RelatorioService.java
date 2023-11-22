package io.red.relatoriospoc.relatorios.services;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RelatorioService {
    private static final Logger logger = LoggerFactory.getLogger(RelatorioService.class);

    public void printPDF(String jasperPath, HttpServletResponse response, String fileName,
                         Map<String, Object> parametros, List<?> source) throws Exception {

        var relativePath = "src/main/resources/" + jasperPath;
        logger.info("relativePath: {}", relativePath);

//        var file = new File(relativePath);
//        logger.info("file: {}", file.getAbsolutePath());

        var report = JasperCompileManager.compileReport(relativePath);
        var dataSource = new JRBeanCollectionDataSource(source, false);

        var jasperPrint = JasperFillManager.fillReport(report, parametros, dataSource);
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
        var stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        response.flushBuffer();
    }
}
