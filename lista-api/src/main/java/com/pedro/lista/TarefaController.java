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

    private ResponseEntity<Map<String, Object>> respostaErro(String mensagem) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("erro", mensagem);
        return ResponseEntity.badRequest().body(response);
    }

    private ResponseEntity<Map<String, Object>> respostaSucesso(String operacao, Object... campos) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("operação", operacao);
        
        for (int i = 0; i < campos.length; i += 2) {
            response.put((String) campos[i], campos[i + 1]);
        }
    
        return ResponseEntity.ok(response);
    }


    @GetMapping("/multiplicartres")
    @ResponseBody

    public ResponseEntity<?> multiplicarTres(@RequestParam double a, @RequestParam double b, @RequestParam double c) {
        double result = a * b * c;
       
        return respostaSucesso("multiplicar-tres",
        "A", a,
        "B", b,
        "C", c,
        "Resultado", result);
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
        response.put("Quantidade de salários recebidos", String.format("%.2f", quantidadeSalarios));

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
        response.put("Comprimento", String.format("%.2f", comprimento));
        response.put("Área", String.format("%.2f", area));
        response.put("Volume", String.format("%.2f", volume));

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
        response.put("Medida da escada", String.format("%.2f", medidaEscada));

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
        double dolar = a / cotacaoDolar, marco = a / cotacaoMarco, euro = a / cotacaoEuro, libra = a / cotacaoLibra;

        response.put("operação", "câmbio");
        response.put("Valor em reais", a);
        response.put("Valor em dólares", String.format("%.2f", dolar));
        response.put("Valor em marcos alemães", String.format("%.2f", marco));
        response.put("Valor em euros", String.format("%.2f", euro));
        response.put("Valor em libras esterlinas", String.format("%.2f", libra));

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

    @GetMapping("/calculadorasimples")
@ResponseBody
public ResponseEntity<?> calculadorasimples(@RequestParam double a, @RequestParam double b, @RequestParam int opcao) {
    Map<String, Object> response = new LinkedHashMap<>();
    double resultado = 0;

    switch (opcao) {
        case 1:
            resultado = (a + b) / 2;
            response.put("operação", "Média entre os números");
            break;
        case 2:
            resultado = Math.abs(a - b);
            response.put("operação", "Diferença do maior pelo menor");
            break;
        case 3:
            resultado = a * b;
            response.put("operação", "Produto entre os números");
            break;
        case 4:
            if (b == 0) {
                response.put("erro", "Divisão por zero não é permitida.");
                return ResponseEntity.badRequest().body(response);
            }
            resultado = a / b;
            response.put("operação", "Divisão do primeiro pelo segundo");
            break;
        default:
            response.put("erro", "Opção inválida. Escolha entre 1 (média), 2 (diferença), 3 (produto) ou 4 (divisão).");
            return ResponseEntity.badRequest().body(response);
    }

    response.put("A", a);
    response.put("B", b);
    response.put("Opção", opcao);
    response.put("Resultado", resultado);

    return ResponseEntity.ok(response);
}

    @GetMapping("/aumentode30")
    @ResponseBody

    public ResponseEntity<?> tarefa16(@RequestParam double a){
        Map<String, Object> response = new LinkedHashMap<>();


        response.put("operação", "Aumento de salário");
        response.put("Salário original", a);

        if (a <= 0) {
            response.put("erro", "O salário deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }
        
        if (a <= 500) {

            double aumento = a * 0.30;

            response.put("Salário com aumento", a + aumento);
            response.put("Aumento de", aumento);
        } else {
            response.put("erro", "O aumento é limitado a quem ganha até R$:500.");
        }
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/aumentodesalario")
    @ResponseBody
    public ResponseEntity<?> aumentoDeSalario(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (a <= 0) {
            response.put("erro", "O salário deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }

        double aumento = 0;
        if (a <= 300) {
            aumento = a * 0.35;
        } else if (a > 300) {
            aumento = a * 0.15; 
        } 

        response.put("operação", "Aumento de salário");
        response.put("Salário original", a);
        response.put("Salário com aumento", a + aumento);
        response.put("Aumento de", aumento);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/creditoespecial")
    @ResponseBody
    public ResponseEntity<?> creditoEspecial(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        double creditoEspecial = 0;

        if (a <= 0) {
            response.put("erro", "O valor deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }
        if (a > 400) {
            creditoEspecial = a * 0.30;
        } else if (a >= 300 && a < 400) {
            creditoEspecial = a * 0.25;
        } else if (a >= 200 && a <= 300) {
            creditoEspecial = a * 0.20;
        } else {
            creditoEspecial = a * 0.10;
        }

        response.put("operação", "Crédito Especial");
        response.put("Saldo médio", a);
        response.put("Crédito especial concedido", creditoEspecial);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/custoaoconsumidor")
    @ResponseBody
    public ResponseEntity<?> custoAoConsumidor(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (a <= 0) {
            response.put("erro", "O valor deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }

        double distribuidor = 0;
        double impostos = 0;

        if (a <= 12000) {
            distribuidor = a * 0.05;
        } else if (a <= 25000) {
            distribuidor = a * 0.10;
            impostos = a * 0.15;
        } else {
            distribuidor = a * 0.15;
            impostos = a * 0.20;
        }

        response.put("operação", "Custo ao consumidor");
        response.put("Custo de fábrica", a);
        response.put("Distribuidor", distribuidor);
        response.put("Impostos", impostos);
        response.put("Custo ao consumidor", a + distribuidor + impostos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/aumentodesalario2")
    @ResponseBody
    public ResponseEntity<?> aumentoDeSalario2(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (a <= 0) {
            response.put("erro", "O valor deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }

        double aumento = 0;

        if (a <= 300) {
            aumento = a * 0.15;
        } else if (a <= 600) {
            aumento = a * 0.10;
        } else if (a <= 900) {
            aumento = a * 0.05;
        }else {
            aumento = 0;
        }

        response.put("operação", "Aumento de salário");
        response.put("Salário original", a);
        response.put("Aumento", aumento);
        response.put("Salário com aumento", a + aumento);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/gratificacao")
    @ResponseBody
    public ResponseEntity<?> gratificacao(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (a <= 0) {
            response.put("erro", "O valor deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }

        double desconto = a * 0.07;
        double gratificacao = 0;
        
        if (a <= 350) {
            gratificacao = 100.0;
        } else if (a <= 600) {
            gratificacao = 75.0;
        } else if (a <= 900) {
            gratificacao = 50.0;
        } else {
            gratificacao = 35.0;
        }

        response.put("operação", "Gratificação");
        response.put("Salário", a);
        response.put("Desconto de 7%", String.format("%.2f", desconto));
        response.put("Gratificação", gratificacao);
        response.put("Salário final", a - desconto + gratificacao);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipodeproduto")
    @ResponseBody
    public ResponseEntity<?> ativ(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        double aumento = 0;
        String tipoProduto = "";

        if (a <= 0) {
            tipoProduto = "Não existe almoço grátis.";
        } else if (a <= 50) {
            aumento = a * 0.05;
        } else if (a <= 100) {
            aumento = a * 0.10;
        } else {
            aumento = a * 0.15;
        }

        double precoFinal = a + aumento;

        if (precoFinal < 80) {
            tipoProduto = "Barato";
        } else if (precoFinal <= 120) {
            tipoProduto = "Normal";
        } else if (precoFinal <= 200) {
            tipoProduto = "Caro";
        } else {
            tipoProduto = "Muito caro";
        }

        response.put("operação", "Tipo de produto");
        response.put("Preço do produto", a);
        response.put("Aumento", String.format("%.2f", aumento));
        response.put("Tipo de produto", tipoProduto);
        response.put("Preço final", precoFinal);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/aumentosalario3")
    @ResponseBody
    public ResponseEntity<?> aumentosalario3(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (a <= 0) {
            response.put("erro", "O valor deve ser maior que zero.");
            return ResponseEntity.badRequest().body(response);
        }

        double aumento = 0;

        if (a <= 300) {
            aumento = a * 0.50;
        } else if (a <= 500) {
            aumento = a * 0.40;
        } else if (a <= 700) {
            aumento = a * 0.30;
        } else if (a <= 800) {
            aumento = a * 0.20;
        } else if (a <= 1000) {
            aumento = a * 0.10;
        }else {
            aumento = a * 0.05;
        }

        response.put("operação", "Aumento de salário");
        response.put("Salário original", a);
        response.put("Aumento", aumento);
        response.put("Salário com aumento", a + aumento);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/investimento")
    @ResponseBody
    public ResponseEntity<?> investimento(@RequestParam double a, @RequestParam double b, @RequestParam double c) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (a != 1 && a != 2) {
            response.put("erro", "Os valores devem ser válidos: 1 para Poupança ou 2 para Fundos de Renda Fixa.");
            return ResponseEntity.badRequest().body(response);
        }

        double rendimento = 0;

        if (a == 1) {
            rendimento = (b * 0.03) * c;
        } else if (a == 2) {
            rendimento = (b * 0.04) * c;
        }

        double total = b + rendimento;

        response.put("operação", "Investimento");
        response.put("Tipo de investimento", a == 1 ? "Poupança" : "Fundos de Renda Fixa");
        response.put("Taxa de rendimento (%)", a == 1 ? "3%" : "4%");
        response.put("Valor investido", b);
        response.put("Período (meses)", c);
        response.put("Rendimento", rendimento);
        response.put("Total após rendimento", total);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/descontoproduto")
    @ResponseBody
    public ResponseEntity<?> descontoProduto(@RequestParam double a, @RequestParam double b) {
        Map<String, Object> response = new LinkedHashMap<>();

        double desconto = 0, porcentagem = 0;
        
        if ( a > 30 && a <= 100){
            desconto = a * 0.10;
            porcentagem = 10;
        } else if(a > 100){
            desconto = a * 0.15;
            porcentagem = 15;
        }

        response.put("operação", "Desconto Produto");
        response.put("Valor do produto", a);
        response.put("Código d)o produto", b);
        response.put("Valor do desconto", desconto);
        response.put("Desconto de", porcentagem + "%");
        response.put("Total com desconto", a - desconto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/passwd")
    @ResponseBody
    public ResponseEntity<?> validPasswd(@RequestParam double a) {
        Map<String, Object> response = new LinkedHashMap<>();
        
        int passwd = 4531;
        String acesso = "Negado!";

        if (a == passwd){
            acesso = "Permitido!";
        }

        response.put("operação", "Restrição de acesso");
        response.put("Acesso", acesso);
        

        return ResponseEntity.ok(response);
    }
}

