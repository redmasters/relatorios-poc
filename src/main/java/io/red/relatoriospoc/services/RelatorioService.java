package io.red.relatoriospoc.services;

import io.red.relatoriospoc.relatorios.dtos.DeclaracaoDTO;
import io.red.relatoriospoc.repositories.EmployeeRepository;
import io.red.relatoriospoc.utils.ReportImageUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RelatorioService {
    private final EmployeeRepository employeeRepository;
    private final ReportImageUtil reportImageUtil;
    private static final String DECLARACAO = "declaracao";
    private final EmployeeService employeeService;

    public RelatorioService(EmployeeRepository employeeRepository, ReportImageUtil reportImageUtil, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.reportImageUtil = reportImageUtil;
        this.employeeService = employeeService;
    }

    public void gerarDeclaracao(Long idHero, HttpServletResponse response) throws Exception {
        var hero = employeeRepository.findById(idHero)
                .orElseThrow();

        var admissaoData = new Date();
        var hoje = Calendar.getInstance();
        var demissao = LocalDate.of(2023, 12, 31);
        var dataAdmissao = new SimpleDateFormat("dd/MM/yyyy")
                .format(admissaoData.getTime());
        var dataEmissao = new SimpleDateFormat("dd/MM/yyyy")
                .format(hoje.getTime());
        var dataDemissao = demissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var criadoPor = "ReD";

        var declaracao = new DeclaracaoDTO(
                hero.getId(),
                hero.getName(),
                hero.getRole(),
                hero.getLane(),
                dataAdmissao,
                dataDemissao,
                dataEmissao,
                criadoPor
        );

        List<DeclaracaoDTO> declaracoes = new ArrayList<>();
        declaracoes.add(declaracao);
        var fileName = DECLARACAO.concat(".pdf");
        Map<String, Object> parametros = this.getParametros();
        employeeService.gerarRelatorio(response, fileName, parametros, declaracoes);
  }

    private Map<String, Object> getParametros() throws IOException {
            Map<String, Object> parametros = new HashMap<>();
//            var logo = reportImageUtil.getBufferedImage("relatorios/imagens/logo.png");
//            var footer = reportImageUtil.getBufferedImage("relatorios/imagens/footer.png");

//            parametros.put("logo", logo);
//            parametros.put("footer", footer);
            parametros.put("nomeSupervisor", "ReD");
            parametros.put("cargoSupervisor", "Admin");
            parametros.put("lotacaoSupervisor", "Top");
            parametros.put("matriculaSupervisor", "234987");
            return parametros;
        }
}

