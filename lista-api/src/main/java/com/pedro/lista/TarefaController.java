package com.pedro.lista;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping
    public String listarTarefas() {
        return "forward:/tarefas.html"; 
    }


    @GetMapping("/multiplicar-tres")
    @ResponseBody
    
    public ResponseEntity<?> multiplicarTres(@RequestParam double a, @RequestParam double b, @RequestParam double c) {
        Map<String, Object> response = new HashMap<>();
        double result = a * b * c;
        response.put("operação", "multiplicar-tres");
        response.put("a", a);
        response.put("b", b);
        response.put("c", c);
        response.put("resultado", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dividir") 
    @ResponseBody

    public ResponseEntity<?> dividir(@RequestParam double a, @RequestParam double b) {
        Map<String, Object> response = new HashMap<>();
        if (b == 0) {
            response.put("erro", "Divisão por zero não é permitida.");
            return ResponseEntity.badRequest().body(response);
        }
        double result = a / b;
        response.put("operação", "dividir");
        response.put("a", a);
        response.put("b", b);
        response.put("resultado", result);
        return ResponseEntity.ok(response);
    }
}

