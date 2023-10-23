package io.red.relatoriospoc.controller;

import io.red.relatoriospoc.services.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/relatorio")
public class RelatorioController {
  private final Logger logger = LoggerFactory.getLogger(RelatorioController.class);
  private final RelatorioService relatorioService;

  public RelatorioController(RelatorioService relatorioService) {
    this.relatorioService = relatorioService;
  }


  @GetMapping
  public void gerarRelatorio(Long idHero, HttpServletResponse response) throws Exception {
    relatorioService.gerarDeclaracao(idHero, response);
  }

  
}

