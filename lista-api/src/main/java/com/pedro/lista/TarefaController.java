package com.pedro.lista;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping
    public List<String> listarTarefas() {
        return Arrays.asList("Tarefa 1", "Tarefa 2", "Tarefa 3");
    }
}
