package com.pedro.lista;

import java.util.LinkedHashMap;
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
        Map<String, Object> response = new LinkedHashMap<>();
        double result = a * b * c;
        response.put("operação", "multiplicar-tres");
        response.put("A", a);
        response.put("B", b);
        response.put("C", c);
        response.put("Resultado", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dividir") 
    @ResponseBody

    public ResponseEntity<?> dividir(@RequestParam double a, @RequestParam double b) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (b == 0) {
            response.put("erro", "Divisão por zero não é permitida.");
            return ResponseEntity.badRequest().body(response);
        }
        double result = a / b;
        response.put("operação", "dividir");
        response.put("A", a);
        response.put("B", b);
        response.put("Resultado", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/media-ponderada")
    @ResponseBody

    public ResponseEntity<?> calcularMediaPonderada(@RequestParam double a, @RequestParam double b) {
        Map<String, Object> response = new LinkedHashMap<>();
        double mediaPonderada = (a * 2 + b * 3) / 5;
        response.put("operação", "media-ponderada");
        response.put("A", a);
        response.put("B", b);
        response.put("Resultado", mediaPonderada);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/desconto-dez")
    @ResponseBody

    public ResponseEntity<?> descontoDez(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();
        double desconto = a * 0.10;

        response.put("operação", "desconto-dez");
        response.put("Valor sem desconto", a);
        response.put("Valor com desconto", a - desconto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/comissao")
    @ResponseBody

    public ResponseEntity<?> comissao(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();
        double comissao = b * 0.04;

        response.put("operação", "comissão");
        response.put("Salário", a);
        response.put("Vendas", b);
        response.put("Comissão", comissao);
        response.put("Salário com comissão", a + comissao);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/peso")
    @ResponseBody

    public ResponseEntity<?> balanca(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        double maisPeso = a * 0.15;
        double menosPeso = a * 0.20;
        
        response.put("operação", "peso");
        response.put("Peso", a);
        response.put("Peso + 15%", a + maisPeso);
        response.put("Peso - 20%", a - menosPeso);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/quilosparagramas")
    @ResponseBody

    public ResponseEntity<?> quilosParaGramas(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        double gramas = a * 1000;

        response.put("operação", "quilos para gramas");
        response.put("Peso em quilos", a);
        response.put("Peso em gramas", gramas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/areatrapezio")
    @ResponseBody

    public ResponseEntity<?> trapezio(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        Map<String, Object> response = new LinkedHashMap<>();

        double area = (a + b) * c / 2;

        response.put("operação", "área do trapézio");
        response.put("Base maior", a);
        response.put("Base menor", b);
        response.put("altura", c);
        response.put("Área", area);
        return ResponseEntity.ok(response);
    }
}

