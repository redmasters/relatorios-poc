package io.red.relatoriospoc.relatorios.controllers;

import io.red.relatoriospoc.relatorios.services.CriarRelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/relatorio")
public class RelatorioController {
    private final CriarRelatorioService criarRelatorioService;

    public RelatorioController(CriarRelatorioService criarRelatorioService) {
        this.criarRelatorioService = criarRelatorioService;
    }

    @GetMapping
    public void gerarRelatorio(Long id, HttpServletResponse response) throws Exception {
         criarRelatorioService.gerarRelatorio(id, response);
    }
}
