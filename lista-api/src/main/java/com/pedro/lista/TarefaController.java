package com.pedro.lista;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping
    public String listarTarefas() {
        return "23h e eu aqui vegetando";
    }
}