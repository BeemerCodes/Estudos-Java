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


    @GetMapping("/multiplicartres")
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
        if (b == 0 ) {
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

    @GetMapping("/mediaponderada")
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

    @GetMapping("/descontodez")
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

    @GetMapping("/areaquadrado")
    @ResponseBody

    public ResponseEntity<?> areaQuadrado(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();

        double area = a * b;

        response.put("operação", "área do quadrado");
        response.put(("Lado A"), a);
        response.put("Lado B", b);
        response.put("Área do quadrado", area);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/arealosango")
    @ResponseBody

    public ResponseEntity<?> arealosango(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();

        double area = (a * b ) / 2;

        response.put("operação", "área do losango");
        response.put("Diagonal maior", a);
        response.put("Diagonal menor", b);
        response.put("Área", area);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/quantidadesalarios")
    @ResponseBody

    public ResponseEntity<?> quantidadeSalarios(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        if(a <= 0){
            response.put("erro", "O valor deve ser maior que 0");
            return ResponseEntity.badRequest().body(response);
        }

        double salarioMinimo = 1.518;
        double quantidadeSalarios = a / salarioMinimo;

        response.put("operação", "Quantidade de salários");
        response.put("Salário", a);
        response.put("Salário mínimo", salarioMinimo);
        response.put("Quantidade de salários recebidos", quantidadeSalarios);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tabuada")
    @ResponseBody

    public ResponseEntity<?> tabuada(@RequestParam int a){
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("operação", "tabuada");
        response.put("Número", a);  
        response.put("Resultados", "veja abaixo");
        
        for(int i = 1; i <= 12; i++){
            response.put(a + " x " + i, a * i);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/idade")
    @ResponseBody

    public ResponseEntity<?> idade(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        double idade = 2025 - a;
        double meses = idade * 12;
        double dias = idade * 365;
        double horas = dias * 24;
        double minutos = horas * 60;
        double segundos = minutos * 60;

        response.put("operação", "idade");
        response.put("Idade em anos", a);
        response.put("Idade em meses", meses);
        response.put("Idade em dias", dias);
        response.put("Idade em horas", horas);
        response.put("Idade em minutos", minutos);
        response.put("Idade em segundos", segundos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contasatrasadas")
    @ResponseBody

    public ResponseEntity<?> contasAtrasadas(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        Map<String, Object> response = new LinkedHashMap<>();

        double contaA = b * 1.02;
        double contaB = c * 1.02;
        double salario = a - contaA - contaB;

        response.put("operação", "Contas Atrasadas");
        response.put("Salário", a);
        response.put("Conta 1 (+2%)", contaA);
        response.put("Conta 2(+2%)", contaB);
        response.put("Restante do salário", salario);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/circunferencia")
    @ResponseBody

    public ResponseEntity<?> circunferencia(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        double pi = 3.14159, comprimento, area, volume;

        comprimento = 2 * pi * a;
        area = pi * (a * a); //formula para área de círculo, não de esfera <-
        volume = (4/3) * pi * (a * a * a);

        response.put("operação", "circunferência");
        response.put("Raio", a);
        response.put("Comprimento", comprimento);
        response.put("Área", area);
        response.put("Volume", volume);

        return ResponseEntity.ok(response);

        // A formula para o volume de uma esfera: V = (4/3) * PI * R^3
        // Utilizei V = (3/4) * PI * R^3 como solicitado.
    }

    @GetMapping("/celsiusparafahrenheit")
    @ResponseBody

    public ResponseEntity<?> celsiusParaFahrenheit(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        double fahrenheit = (180 * (a + 32)) / 100;

        response.put("operação", "Celsius para Fahrenheit");
        response.put("Celsius", a);
        response.put("Fahrenheit", fahrenheit);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/iluminacao")
    @ResponseBody

    public ResponseEntity<?> iluminacao(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();

        double area = a * b;
        double potenciaIluminacao = area * 18.0;

        response.put("operação", "iluminação");
        response.put("Largura", a);
        response.put("Comprimento", b);
        response.put("Área", area);
        response.put("Potência de iluminação necessária (em watts)", potenciaIluminacao);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/medidaescada")
    @ResponseBody

    public ResponseEntity<?> medidaEscada(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();

        double grauEmRadiano = Math.toRadians(a);
        double medidaEscada = b / Math.cos(grauEmRadiano);  

        response.put("operação", "medida da escada");
        response.put("Grau de inclinação", a);
        response.put("Distância da base", b);
        response.put("Medida da escada", medidaEscada);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/quantovouganhar")
    @ResponseBody

    public ResponseEntity<?> quantoVouGanhar(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        Map<String, Object> response = new LinkedHashMap<>();

        double horaTrabalhada = a / 8.0; 
        double horaExtra = a / 4.0; 
        double valorBruto = horaTrabalhada * b;
        double valorExtra = horaExtra * c;

        response.put("operação", "Quanto vou ganhar");
        response.put("Salário", a);
        response.put("Horas normais trabalhadas", b);
        response.put("Horas extras trabalhadas", c);
        response.put("Valor por hora normal", horaTrabalhada);
        response.put("Valor por hora extra", horaExtra);
        response.put("Valor bruto", valorBruto + valorExtra);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/poligono")
    @ResponseBody

    public ResponseEntity<?> polignono(@RequestParam int a){
        Map<String, Object> response = new LinkedHashMap<>();
    
        if (a < 3) {
            response.put("erro", "Polígonos devem ter ao menos 3 lados.");
            return ResponseEntity.badRequest().body(response);
        }

        double diagonais = (a * (a - 3)) / 2;

        response.put("operação", "Polígono");
        response.put("Número de lados", a);
        response.put("Diagonais", diagonais);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/angulo")
    @ResponseBody

    public ResponseEntity<?> angulo(@RequestParam int a, @RequestParam int b){
        Map<String, Object> response = new LinkedHashMap<>();

        int angulo = 180 - (a + b);

        if (angulo <= 0) {
            response.put("erro", "Os angulos fornecidos são inválidos.");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("operação", "ângulo");
        response.put("Ângulo A", a);
        response.put("Ângulo B", b);
        response.put("Ângulo C", angulo);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/cambio")
    @ResponseBody

    public ResponseEntity<?> cambio(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();

        double cotacaoDolar = 5.49, cotacaoMarco = 0.3112, cotacaoEuro = 6.31, cotacaoLibra = 7.49;

        response.put("operação", "câmbio");
        response.put("Valor em reais", a);
        response.put("Valor em dólares", a / cotacaoDolar);
        response.put("Valor em marcos alemães", a / cotacaoMarco);
        response.put("Valor em euros", a / cotacaoEuro);
        response.put("Valor em libras esterlinas", a / cotacaoLibra);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/horasparaminutos")
    @ResponseBody

    public ResponseEntity<?> horasParaMinutos(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();

        double totalMinutos = (a * 60) + b, totalSegundos = totalMinutos * 60;

        response.put("operação", "Horas para minutos");
        response.put("Horas", a);
        response.put("Minutos", b);
        response.put("Total em minutos", totalMinutos);
        response.put("Total em segundos", totalSegundos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/menornumero")
    @ResponseBody

    public ResponseEntity<?> menornumero(@RequestParam double a, @RequestParam double b){
        Map<String, Object> response = new LinkedHashMap<>();

            response.put("operação", "Menor número");
            response.put("Número A", a);
            response.put("Número B", b);

                if( a < b) {
                    response.put("Resultado", "O menor número é A: " + a);
                } else if (b < a) {
                    response.put("Resultado", "O menor número é B: " + b);
                } else {
                    response.put("Resultado", "Os números são iguais: " + a);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/maiornumero")
    @ResponseBody

    public ResponseEntity<?> maiornumero(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("operação", "Maior número");
        response.put("Número A", a);
        response.put("Número B", b);
        response.put("Número C", c);

        if (a > b && a > c) {
            response.put("O maior número é", a);
        } else if (b > a && b > c) {
            response.put("O maior número é", b);
        } else if (c > a && c > b) {
            response.put("O maior número é", c);
        } else {
            response.put("erro", "Os números são iguais.");
        }

        return ResponseEntity.ok(response);
    }


    
}

