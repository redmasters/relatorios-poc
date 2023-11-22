package io.red.relatoriospoc.relatorios.services;

import io.red.relatoriospoc.relatorios.dtos.DeclaracaoDTO;
import io.red.relatoriospoc.relatorios.repositories.EmployeeRepository;
import io.red.relatoriospoc.utils.ReportImageUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CriarRelatorioService {

    private static final String JASPER_DECLARACAO = "relatorios/declaracao.jrxml";
    private static final String JASPER_RELATORIO = "relatorios/relatorio.jasper";
    private static final String DECLARACAO = "declaracao";
    private final EmployeeRepository repository;
    private final RelatorioService relatorioService;

    public CriarRelatorioService(EmployeeRepository repository, RelatorioService relatorioService) {
        this.repository = repository;
        this.relatorioService = relatorioService;
    }

    public void gerarRelatorio(Long id, HttpServletResponse response) throws Exception {

        List<DeclaracaoDTO> declaracaoDTOList = new ArrayList<>();
        final var employee = repository.findById(id)
                .orElseThrow();

        var empID = employee.getId();

        var declaracao = new DeclaracaoDTO();
        declaracao.setId(empID);

        declaracaoDTOList.add(declaracao);
        var fileName = DECLARACAO.concat(".pdf");

        var parametros = parametros();

        relatorioService.printPDF(JASPER_DECLARACAO, response, fileName, parametros, declaracaoDTOList);
    }

    public Map<String, Object> parametros() throws IOException {
        Map<String, Object> parametros = new HashMap<>();
        var logo = ReportImageUtil.getBufferedImage("relatorios/imagens/logo.png");
        var footer = ReportImageUtil.getBufferedImage("relatorios/imagens/footer.png");
        
        parametros.put("logo", logo);
        parametros.put("footer", footer);
       
        return parametros;
    }
}
