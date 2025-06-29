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
        return respostaSucesso("multiplicar-tres", "A", a, "B", b, "C", c, "Resultado", result);
    }

    @GetMapping("/dividir") 
    @ResponseBody
    public ResponseEntity<?> dividir(@RequestParam double a, @RequestParam double b) {
        if (b == 0 ) {
            return respostaErro("O valor não pode ser dividido por zero.");
        }
        double result = a / b;
        return respostaSucesso("Dividir", "A", a, "B", b, "Resultado", result);
    }

    @GetMapping("/mediaponderada")
    @ResponseBody
    public ResponseEntity<?> calcularMediaPonderada(@RequestParam double a, @RequestParam double b) {
        double mediaPonderada = (a * 2 + b * 3) / 5;
        return respostaSucesso("Média Ponderada", "A", a, "B", b, "Resultado", mediaPonderada);
    }

    @GetMapping("/descontodez")
    @ResponseBody
    public ResponseEntity<?> descontoDez(@RequestParam double a){
        if (a <= 0 ){
            return respostaErro("O valor deve ser maior que zero.");
        }
        double desconto = a * 0.10;
        return respostaSucesso("Desconto de 10%", "Valor sem desconto", a, "Valor com desconto", a - desconto);
    }

    @GetMapping("/comissao")
    @ResponseBody
    public ResponseEntity<?> comissao(@RequestParam double a, @RequestParam double b){
        if (a <= 0){
            return respostaErro("O salário deve ser maior que zero.");
        }
        double comissao = b * 0.04;
        return respostaSucesso("Comissão", "Salário", a, "Vendas", b, "Comissão", comissao == 0 ? "Sem comissão" : comissao, "Salário com comissão", a + comissao);
    }

    @GetMapping("/peso")
    @ResponseBody
    public ResponseEntity<?> balanca(@RequestParam double a){
        if (a <= 0){
            return respostaErro("O peso deve ser maior que zero.");
        }
        double maisPeso = a * 0.15;
        double menosPeso = a * 0.20;
        return respostaSucesso("Peso", "Peso", a, "Peso + 15%", a + maisPeso, "Peso - 20%", a - menosPeso);
    }

    @GetMapping("/quilosparagramas")
    @ResponseBody
    public ResponseEntity<?> quilosParaGramas(@RequestParam double a){
        if (a <= 0){
            return respostaErro("O peso deve ser maior que zero.");
        }
        double gramas = a * 1000;
        return respostaSucesso("Quilos para gramas", "Peso em quilos", a, "Peso em gramas", gramas);
    }

    @GetMapping("/areatrapezio")
    @ResponseBody
    public ResponseEntity<?> trapezio(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        if (a <= 0 || b <= 0 || c <= 0){
            return respostaErro("Os valores devem ser maior que zero.");
        }
        double area = (a + b) * c / 2;
        return respostaSucesso("Área do trapézio", "Base maior", a, "Base menor", b, "altura", c, "Área", area);
    }

    @GetMapping("/areaquadrado")
    @ResponseBody
    public ResponseEntity<?> areaQuadrado(@RequestParam double a, @RequestParam double b){
        if (a <= 0 || b <= 0){
            return respostaErro("Os valores devem ser maior que zero.");
        }
        double area = a * b;
        return respostaSucesso("Área do quadrado", "Lado A", a, "Lado B", b, "Área do quadrado", area);
    }

    @GetMapping("/arealosango")
    @ResponseBody
    public ResponseEntity<?> arealosango(@RequestParam double a, @RequestParam double b){
        if (a <= 0 || b <= 0){
            return respostaErro("As diagonais devem ser positivas.");
        }
        double area = (a * b ) / 2;
        return respostaSucesso("Área do losango", "Diagonal maior", a, "Diagonal menor", b, "Área", area);
    }

    @GetMapping("/quantidadesalarios")
    @ResponseBody
    public ResponseEntity<?> quantidadeSalarios(@RequestParam double a){
        if(a <= 0){
            return respostaErro("O salário deve ser maior que zero.");
        }
        double salarioMinimo = 1.518;
        double quantidadeSalarios = a / salarioMinimo;
        return respostaSucesso("Quantidade de salários", "Salário", a, "Salário mínimo", salarioMinimo, "Quantidade de salários recebidos", String.format("%.2f", quantidadeSalarios));
    }

    @GetMapping("/tabuada")
    @ResponseBody
    public ResponseEntity<?> tabuada(@RequestParam int a){
        if (a <= 0){
            return respostaErro("Tabuada de zero??");
        }
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
        if (a <= 0) {
            return respostaErro("O ano de nascimento deve ser maior que zero.");
        }
        double idade = 2025 - a;
        double meses = idade * 12;
        double dias = idade * 365;
        double horas = dias * 24;
        double minutos = horas * 60;
        double segundos = minutos * 60;
        return respostaSucesso("Idade", "Idade em anos", a, "Idade em meses", meses, "Idade em dias", dias, "Idade em horas", horas, "Idade em minutos", minutos, "Idade em segundos", segundos);
    }

    @GetMapping("/contasatrasadas")
    @ResponseBody
    public ResponseEntity<?> contasAtrasadas(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        if (a <= 0){
            return respostaErro("O salário deve ser maior que zero.");
        }
        double contaA = b * 1.02;
        double contaB = c * 1.02;
        double salario = a - contaA - contaB;
        return respostaSucesso("Contas atrasadas", "Salário", a, "Conta 1 (+2%)", contaA, "Conta 2(+2%)", contaB, "Restante do salário", salario);
    }

    @GetMapping("/circunferencia")
    @ResponseBody
    public ResponseEntity<?> circunferencia(@RequestParam double a){
        if (a <= 0) {
            return respostaErro("O raio deve ser positivo.");
        }
        double pi = 3.14159, comprimento, area, volume;
        comprimento = 2 * pi * a;
        area = pi * (a * a); //formula para área de círculo, não de esfera <-
        volume = (4/3) * pi * (a * a * a);
        return respostaSucesso("Circunferência", "Raio", a, "Comprimento", String.format("%.2f", comprimento), "Área", String.format("%.2f", area), "Volume", String.format("%.2f", volume));
        // A formula para o volume de uma esfera: V = (4/3) * PI * R^3
        // Utilizei V = (3/4) * PI * R^3 como solicitado.
    }

    @GetMapping("/celsiusparafahrenheit")
    @ResponseBody
    public ResponseEntity<?> celsiusParaFahrenheit(@RequestParam double a){
        double fahrenheit = (180 * (a + 32)) / 100; // Pelo que vi o certo é (a * 9.0/5.0) + 32, fiz como solicitado na atividade.
        return respostaSucesso("Celsius para Fahrenheit", "Celsius", a, "Fahrenheit", fahrenheit);
    }

    @GetMapping("/iluminacao")
    @ResponseBody
    public ResponseEntity<?> iluminacao(@RequestParam double a, @RequestParam double b){
        if (a <= 0 || b <= 0){
            return respostaErro("Largura e comprimento devem ser positivos.");
        }
        double area = a * b;
        double potenciaIluminacao = area * 18.0;
        return respostaSucesso("iluminação", "Largura", a, "Comprimento", b, "Área", area, "Potência de iluminação necessária (em watts)", potenciaIluminacao);
    }

    @GetMapping("/medidaescada")
    @ResponseBody
    public ResponseEntity<?> medidaEscada(@RequestParam double a, @RequestParam double b){
        if (b <= 0 || a <= 0 || a >= 90){
            return respostaErro("Altura deve ser positiva e o ângulo deve estar entre 0 e 90 graus.");
        }
        double grauEmRadiano = Math.toRadians(a);
        double medidaEscada = b / Math.cos(grauEmRadiano);  
        return respostaSucesso("Medida da escada", "Grau de inclinação", a, "Distância da base", b, "Medida da escada", String.format("%.2f", medidaEscada));
    }

    @GetMapping("/quantovouganhar")
    @ResponseBody
    public ResponseEntity<?> quantoVouGanhar(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        if (a <= 0 && b <= 0){
            return respostaErro("O salário não pode ser negativo e as horas normais devem ser positivas.");
        }
        double horaTrabalhada = a / 8.0; 
        double horaExtra = a / 4.0; 
        double valorBruto = horaTrabalhada * b;
        double valorExtra = horaExtra * c;
        return respostaSucesso("Quanto vou ganhar", "Horas normais trabalhadas", b, "Horas extras trabalhadas", c, "Valor por hora normal", horaTrabalhada, "Valor por hora extra", horaExtra, "Valor bruto", valorBruto + valorExtra);
    }

    @GetMapping("/poligono")
    @ResponseBody
    public ResponseEntity<?> polignono(@RequestParam int a){
        if (a < 3) {
            return respostaErro("Polígonos devem ter ao menos 3 lados.");
        }
        double diagonais = (a * (a - 3)) / 2;
        return respostaSucesso("Polígono", "Número de lados", a, "Diagonais", diagonais);
    }

    @GetMapping("/angulo")
    @ResponseBody
    public ResponseEntity<?> angulo(@RequestParam int a, @RequestParam int b){
        int angulo = 180 - (a + b);
        if (angulo <= 0) {
            return respostaErro("Os angulos fornecidos são inválidos.");
        }
        return respostaSucesso("ângulo", "Ângulo A", a, "Ângulo B", b, "Ângulo C", angulo);
    }

    @GetMapping("/cambio")
    @ResponseBody
    public ResponseEntity<?> cambio(@RequestParam double a){
        if (a <= 0){
            return respostaErro("O valor deve ser maior que zero.");
        }
        double cotacaoDolar = 5.49, cotacaoMarco = 0.3112, cotacaoEuro = 6.31, cotacaoLibra = 7.49;
        double dolar = a / cotacaoDolar, marco = a / cotacaoMarco, euro = a / cotacaoEuro, libra = a / cotacaoLibra;
        return respostaSucesso("Câmbio", "Valor em reais", a, "Valor em dólares", String.format("%.2f", dolar), "Valor em marcos alemães", String.format("%.2f", marco), "Valor em euros", String.format("%.2f", euro), "Valor em libras esterlinas", String.format("%.2f", libra));
    }

    @GetMapping("/horasparaminutos")
    @ResponseBody
    public ResponseEntity<?> horasParaMinutos(@RequestParam double a, @RequestParam double b){
        if (a <= 0){
            return respostaErro("A hora deve ser positiva.");
        }
        double totalMinutos = (a * 60) + b, totalSegundos = totalMinutos * 60;
        return respostaSucesso("Horas para minutos", "Horas", a, "Minutos", b, "Total em minutos", totalMinutos, "Total em segundos", totalSegundos);
    }

    @GetMapping("/menornumero")
    @ResponseBody
    public ResponseEntity<?> menornumero(@RequestParam double a, @RequestParam double b){
        return respostaSucesso("Menor número", "Número A", a, "Número B", b, "Resultado", a <= b ? a : b);
    }

    @GetMapping("/maiornumero")
    @ResponseBody
    public ResponseEntity<?> maiornumero(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        return respostaSucesso("Maior número", "Número A", a, "Número B", b, "Número C", c, "O maior número é", a > b ? (a > c ? a : c) : (b > c ? b : c));
    }

    @GetMapping("/calculadorasimples")
    @ResponseBody
    public ResponseEntity<?> calculadorasimples(@RequestParam double a, @RequestParam double b, @RequestParam int opcao) {
        switch (opcao) {
            case 1 -> {
                double resultado = (a + b) / 2;
                String operacao = "Média entre os números";
                return respostaSucesso(operacao, "Opção", opcao, "A", a, "B", b, "Resultado", resultado);
            }
            case 2 -> {
                double resultado = Math.abs(a - b);
                String operacao = "Diferença do maior pelo menor";
                return respostaSucesso(operacao, "Opção", opcao, "A", a, "B", b, "Resultado", resultado);
            }
            case 3 -> {
                double resultado = a * b;
                String operacao = "Produto entre os números";
                return respostaSucesso(operacao, "Opção", opcao, "A", a, "B", b, "Resultado", resultado);
            }
            case 4 -> {
                if (b == 0) {
                    return respostaErro("A divisão por 0 não é permitida.");
                }
                double resultado = a / b;
                String operacao = "Divisão do primeiro pelo segundo";
                return respostaSucesso(operacao, "Opção", opcao, "A", a, "B", b, "Resultado", resultado);
            }
            default -> {
                return respostaErro("Opção inválida. Escolha entre 1, 2, 3 ou 4.");
            }
        }
}

@GetMapping("/aumentode30")
@ResponseBody
public ResponseEntity<?> aumentode30(@RequestParam double a) { 
    if (a <= 0) {
        return respostaErro("O salário deve ser um valor positivo.");
    }
    if (a <= 500) {
        double aumento = a * 0.30;
        return respostaSucesso("Aumento de Salário", "Salário original", a, "Aumento concedido", String.format("%.2f", aumento), "Salário com aumento", String.format("%.2f", a + aumento));
    } else {
        return respostaErro("O aumento de 30% é limitado a quem ganha até R$ 500,00.");
    }
}
    
    @GetMapping("/aumentodesalario")
    @ResponseBody
    public ResponseEntity<?> aumentoDeSalario(@RequestParam double a) {
        if (a <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
        }

        double aumento = 0;

        if (a <= 300) {
            aumento = a * 0.35;
        } else if (a > 300) {
            aumento = a * 0.15; 
        }
        return respostaSucesso("Aumento de salário", "Salário original", a, "Salário com aumento", a + aumento, "Aumento de", aumento);
    }

    @GetMapping("/creditoespecial")
    @ResponseBody
    public ResponseEntity<?> creditoEspecial(@RequestParam double a) {
        double creditoEspecial = 0;

        if (a <= 0) {
            return respostaErro("O saldo médio deve ser maior que zero.");
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
        return respostaSucesso("Crédito Especial", "Saldo médio", a, "Crédito especial concedido", creditoEspecial);
    }

    @GetMapping("/custoaoconsumidor")
    @ResponseBody
    public ResponseEntity<?> custoAoConsumidor(@RequestParam double a) {
        if (a <= 0) {
            return respostaErro("O custo de fábrica deve ser maior que zero.");
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
        return respostaSucesso("Custo ao consumidor", "Custo de fábrica", a, "Distribuidor", distribuidor, "Impostos", impostos, "Custo ao consumidor", a + distribuidor + impostos);
    }

    @GetMapping("/aumentodesalario2")
    @ResponseBody
    public ResponseEntity<?> aumentoDeSalario2(@RequestParam double a) {
        if (a <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
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
        return respostaSucesso("Aumento de salário", "Salário original", a, "Aumento", aumento, "Salário com aumento", a + aumento);
    }

    @GetMapping("/gratificacao")
    @ResponseBody
    public ResponseEntity<?> gratificacao(@RequestParam double a) {
        if (a <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
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
        return respostaSucesso("Gratificação", "Salário", a, "Desconto de 7%", String.format("%.2f", desconto), "Gratificação", gratificacao, "Salário final", a - desconto + gratificacao);
    }

    @GetMapping("/tipodeproduto")
    @ResponseBody
    public ResponseEntity<?> ativ(@RequestParam double a) {
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
        return respostaSucesso("Tipo de produto", "Preço do produto", a, "Aumento", String.format("%.2f", aumento), "Tipo de produto", tipoProduto, "Preço final", precoFinal);
    }

    @GetMapping("/aumentosalario3")
    @ResponseBody
    public ResponseEntity<?> aumentosalario3(@RequestParam double a) {
        if (a <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
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
        return respostaSucesso("Aumento de salário", "Salário original", a, "Aumento", aumento, "Salário com aumento", a + aumento);
    }

    @GetMapping("/investimento")
    @ResponseBody
    public ResponseEntity<?> investimento(@RequestParam double a, @RequestParam double b, @RequestParam double c) {
        if (a != 1 && a != 2) {
            return respostaErro("Os valores devem ser válidos: 1 para Poupança ou 2 para Fundos de Renda Fixa.");
        }

        double rendimento = 0;

        if (a == 1) {
            rendimento = (b * 0.03) * c;
        } else if (a == 2) {
            rendimento = (b * 0.04) * c;
        }

        double total = b + rendimento;
        return respostaSucesso("Investimento","Tipo de investimento", a == 1 ? "Poupança" : "Fundos de Renda Fixa", "Taxa de rendimento (%)", a == 1 ? "3%" : "4%", "Valor investido", b, "Período (meses)", c, "Rendimento", rendimento, "Total após rendimento", total);
    }

    @GetMapping("/descontoproduto")
    @ResponseBody
    public ResponseEntity<?> descontoProduto(@RequestParam double a, @RequestParam double b) {
        if (a <= 0) {
            return respostaErro("O valor deve ser maior que zero.");
        }

        double desconto = 0, porcentagem = 0;
        
        if ( a > 30 && a <= 100){
            desconto = a * 0.10;
            porcentagem = 10;
        } else if(a > 100){
            desconto = a * 0.15;
            porcentagem = 15;
        }
        return respostaSucesso("Desconto Produto", "Valor do produto", a, "Código d)o produto", b, "Valor do desconto", desconto, "Desconto de", porcentagem + "%", "Total com desconto", a - desconto);
    }

    @GetMapping("/passwd")
    @ResponseBody
    public ResponseEntity<?> validPasswd(@RequestParam double a) {
        int passwd = 4531;
        String acesso = ((int)a == passwd) ? "Permitido!" : "Negado!";
        return respostaSucesso("Restrição de acesso","Acesso", acesso);
    }
}

