package com.pedro.lista;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String senhaHasheada = "$2a$10$cf.Xue2hKJmbjV.Xlvr8JO.c5DUfNRkVvZGUn5ZAGe5zDnak5c2dC";

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
// Lista 1
    @GetMapping("/multiplicartres")
    @ResponseBody
    @Cacheable("multiplicartres")
    public ResponseEntity<?> multiplicarTres(@RequestParam double a, @RequestParam double b, @RequestParam double c) {

        double result = a * b * c;

        return respostaSucesso(
            "multiplicar-tres", 
            "A", a, 
            "B", b, 
            "C", c, 
            "Resultado", result);
    }

    @GetMapping("/dividir") 
    @ResponseBody
    @Cacheable("dividir")
    public ResponseEntity<?> dividir(@RequestParam double a, @RequestParam double b) {

        if (b == 0 ) {
            return respostaErro("O valor não pode ser dividido por zero.");
        }

        double result = a / b;

        return respostaSucesso(
            "Dividir", 
            "A", a, 
            "B", b, 
            "Resultado", result);
    }

    @GetMapping("/mediaponderada")
    @ResponseBody
    @Cacheable("mediaponderada")
    public ResponseEntity<?> calcularMediaPonderada(@RequestParam double a, @RequestParam double b) {

        double mediaPonderada = (a * 2 + b * 3) / 5;

        return respostaSucesso(
            "Média Ponderada", 
            "A", a, 
            "B", b, 
            "Resultado", mediaPonderada);
    }

    @GetMapping("/descontodez")
    @ResponseBody
    @Cacheable("descontodez")
    public ResponseEntity<?> descontoDez(@RequestParam double a){

        if (a <= 0 ){
            return respostaErro("O valor deve ser maior que zero.");
        }

        double desconto = a * 0.10;

        return respostaSucesso(
            "Desconto de 10%", 
            "Valor sem desconto", "R$" + String.format("%.2f", a), 
            "Valor com desconto", "R$" + String.format("%.2f", a - desconto));
    }

    @GetMapping("/comissao")
    @ResponseBody
    @Cacheable("comissao")
    public ResponseEntity<?> comissao(@RequestParam double a, @RequestParam double b){

        if (a <= 0){
            return respostaErro("O salário deve ser maior que zero.");
        }

        double comissao = b * 0.04;

        return respostaSucesso(
            "Comissão", 
            "Salário", "R$" + String.format("%.2f", a), 
            "Vendas", "R$" + String.format("%.2f", b), 
            "Comissão", comissao == 0 ? "Sem comissão" : "R$" + String.format("%.2f", comissao), 
            "Salário com comissão", "R$" + String.format("%.2f", a + comissao));
    }

    @GetMapping("/peso")
    @ResponseBody
    @Cacheable("peso")
    public ResponseEntity<?> balanca(@RequestParam double a){

        if (a <= 0){
            return respostaErro("O peso deve ser maior que zero.");
        }

        double maisPeso = a * 1.15;
        double menosPeso = a * 0.80;

        return respostaSucesso(
            "Peso", 
            "Peso", String.format("%.2f", a) + "Kg", 
            "Peso + 15%", String.format("%.2f", maisPeso) + "Kg", 
            "Peso - 20%", String.format("%.2f", menosPeso) + "Kg");
    }

    @GetMapping("/quilosparagramas")
    @ResponseBody
    @Cacheable("quilosparagramas")
    public ResponseEntity<?> quilosParaGramas(@RequestParam double a){

        if (a < 0){
            return respostaErro("O peso não pode ser negativo.");
        }

        double gramas = a * 1000;

        return respostaSucesso(
            "Quilos para gramas", 
            "Peso em quilos", String.format("%.2f", a) + "Kg", 
            "Peso em gramas", String.format("%.2f", gramas) + "g");
    }

    @GetMapping("/areatrapezio")
    @ResponseBody
    @Cacheable("areatrapezio")
    public ResponseEntity<?> trapezio(@RequestParam double a, @RequestParam double b, @RequestParam double c){

        if (a <= 0 || b <= 0 || c <= 0){
            return respostaErro("Os valores devem ser maior que zero.");
        }

        double area = (a + b) * c / 2;

        return respostaSucesso(
            "Área do trapézio", 
            "Base maior", a, 
            "Base menor", b, 
            "altura", c, 
            "Área", area);
    }

    @GetMapping("/areaquadrado")
    @ResponseBody
    @Cacheable("areaquadrado")
    public ResponseEntity<?> areaQuadrado(@RequestParam double a, @RequestParam double b){

        if (a <= 0 || b <= 0){
            return respostaErro("Os valores devem ser maior que zero.");
        }

        double area = a * b;

        return respostaSucesso(
            "Área do quadrado", 
            "Lado A", a, 
            "Lado B", b, 
            "Área do quadrado", area);
    }

    @GetMapping("/arealosango")
    @ResponseBody
    @Cacheable("arealosango")
    public ResponseEntity<?> arealosango(@RequestParam double a, @RequestParam double b){

        if (a <= 0 || b <= 0){
            return respostaErro("As diagonais devem ser positivas.");
        }

        double area = (a * b ) / 2;

        return respostaSucesso(
            "Área do losango", 
            "Diagonal maior", a, 
            "Diagonal menor", b, 
            "Área", area);
    }

    @GetMapping("/quantidadesalarios")
    @ResponseBody
    @Cacheable("quantidadesalarios")
    public ResponseEntity<?> quantidadeSalarios(@RequestParam double a){

        if(a <= 0){
            return respostaErro("O salário deve ser maior que zero.");
        }

        double salarioMinimo = 1412.00;
        double quantidadeSalarios = a / salarioMinimo;

        return respostaSucesso(
            "Quantidade de salários", 
            "Salário", "R$" + String.format("%.2f", a), 
            "Salário mínimo", "R$" + String.format("%.2f", salarioMinimo), 
            "Quantidade de salários recebidos", String.format("%.2f", quantidadeSalarios));
    }

    @GetMapping("/tabuada")
    @ResponseBody
    @Cacheable("tabuada")
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
    @Cacheable("idade")
    public ResponseEntity<?> idade(@RequestParam int a){

        if (a <= 0 || a > 2025) {
            return respostaErro("O ano de nascimento deve ser válido.");
        }

        int idade = 2025 - a;
        long meses = idade * 12;
        long dias = idade * 365;
        long horas = dias * 24;
        long minutos = horas * 60;
        long segundos = minutos * 60;

        return respostaSucesso(
            "Idade", 
            "Idade em anos", idade, 
            "Idade em meses", meses, 
            "Idade em dias", dias, 
            "Idade em horas", horas, 
            "Idade em minutos", minutos, 
            "Idade em segundos", segundos);
    }

    @GetMapping("/contasatrasadas")
    @ResponseBody
    @Cacheable("contasatrasadas")
    public ResponseEntity<?> contasAtrasadas(@RequestParam double a, @RequestParam double b, @RequestParam double c){

        if (a <= 0 || b < 0 || c < 0){
            return respostaErro("O salário deve ser positivo e as contas não podem ser negativas.");
        }

        double contaA = b * 1.02;
        double contaB = c * 1.02;
        double salario = a - contaA - contaB;

        return respostaSucesso(
            "Contas atrasadas", 
            "Salário", "R$" + String.format("%.2f", a), 
            "Conta 1 (+2%)", "R$" + String.format("%.2f", contaA), 
            "Conta 2(+2%)", "R$" + String.format("%.2f", contaB), 
            "Restante do salário", "R$" + String.format("%.2f", salario));
    }

    @GetMapping("/circunferencia")
    @ResponseBody
    @Cacheable("circunferencia")
    public ResponseEntity<?> circunferencia(@RequestParam double a){

        if (a <= 0) {
            return respostaErro("O raio deve ser positivo.");
        }

        double pi = 3.14159, comprimento, area, volume;
        comprimento = 2 * pi * a;
        area = pi * (a * a);
        volume = (4.0/3.0) * pi * (a * a * a);

        return respostaSucesso(
            "Circunferência", 
            "Raio", a, 
            "Comprimento", String.format("%.2f", comprimento), 
            "Área", String.format("%.2f", area), 
            "Volume", String.format("%.2f", volume));
    }

    @GetMapping("/celsiusparafahrenheit")
    @ResponseBody
    @Cacheable("celsiusparafahrenheit")
    public ResponseEntity<?> celsiusParaFahrenheit(@RequestParam double a){

        double fahrenheit = (a * 9.0/5.0) + 32;

        return respostaSucesso(
            "Celsius para Fahrenheit", 
            "Celsius", a + "°C", 
            "Fahrenheit", String.format("%.1f", fahrenheit) + "°F");
    }

    @GetMapping("/iluminacao")
    @ResponseBody
    @Cacheable("iluminacao")
    public ResponseEntity<?> iluminacao(@RequestParam double a, @RequestParam double b){

        if (a <= 0 || b <= 0){
            return respostaErro("Largura e comprimento devem ser positivos.");
        }

        double area = a * b;
        double potenciaIluminacao = area * 18.0;

        return respostaSucesso(
            "iluminação", 
            "Largura", a, 
            "Comprimento", b, 
            "Área", String.format("%.2f", area) + "m²", 
            "Potência de iluminação necessária", String.format("%.0f", potenciaIluminacao) + "W");
    }

    @GetMapping("/medidaescada")
    @ResponseBody
    @Cacheable("medidaescada")
    public ResponseEntity<?> medidaEscada(@RequestParam double a, @RequestParam double b){

        if (b <= 0 || a <= 0 || a >= 90){
            return respostaErro("Altura deve ser positiva e o ângulo deve estar entre 0 e 90 graus.");
        }

        double grauEmRadiano = Math.toRadians(a);
        double medidaEscada = b / Math.cos(grauEmRadiano); 

        return respostaSucesso(
            "Medida da escada", 
            "Grau de inclinação", a, 
            "Altura a alcançar", b, 
            "Medida da escada", String.format("%.2f", medidaEscada));
    }

    @GetMapping("/quantovouganhar")
    @ResponseBody
    @Cacheable("quantovouganhar")
    public ResponseEntity<?> quantoVouGanhar(@RequestParam double a, @RequestParam double b, @RequestParam double c){

        if (a <= 0 || b < 0 || c < 0){
            return respostaErro("O salário deve ser positivo e as horas não podem ser negativas.");
        }

        double horaTrabalhada = a / 160.0; 
        double horaExtra = horaTrabalhada * 1.5; 
        double valorBruto = horaTrabalhada * b;
        double valorExtra = horaExtra * c;

        return respostaSucesso(
            "Quanto vou ganhar", 
            "Salário base", "R$" + String.format("%.2f", a), 
            "Horas normais trabalhadas", b, 
            "Horas extras trabalhadas", c, 
            "Valor por hora normal", "R$" + String.format("%.2f", horaTrabalhada), 
            "Valor por hora extra", "R$" + String.format("%.2f", horaExtra), 
            "Valor bruto", "R$" + String.format("%.2f", valorBruto + valorExtra));
    }

    @GetMapping("/poligono")
    @ResponseBody
    @Cacheable("poligono")
    public ResponseEntity<?> polignono(@RequestParam int a){

        if (a < 3) {
            return respostaErro("Polígonos devem ter ao menos 3 lados.");
        }

        double diagonais = (a * (a - 3.0)) / 2.0;

        return respostaSucesso(
            "Polígono", 
            "Número de lados", a, 
            "Diagonais", diagonais);
    }

    @GetMapping("/angulo")
    @ResponseBody
    @Cacheable("angulo")
    public ResponseEntity<?> angulo(@RequestParam int a, @RequestParam int b){

        int angulo = 180 - (a + b);
        if (angulo <= 0 || a <= 0 || b <= 0) {
            return respostaErro("Os angulos fornecidos são inválidos.");
        }

        return respostaSucesso(
            "ângulo", 
            "Ângulo A", a, 
            "Ângulo B", b, 
            "Ângulo C", angulo);
    }

    @GetMapping("/cambio")
    @ResponseBody
    @Cacheable("cambio")
    public ResponseEntity<?> cambio(@RequestParam double a){

        if (a <= 0){
            return respostaErro("O valor deve ser maior que zero.");
        }

        double cotacaoDolar = 5.45, cotacaoEuro = 5.85, cotacaoLibra = 6.90;
        double dolar = a / cotacaoDolar, euro = a / cotacaoEuro, libra = a / cotacaoLibra;

        return respostaSucesso(
            "Câmbio", 
            "Valor em reais", "R$" + String.format("%.2f", a), 
            "Valor em dólares", "$" + String.format("%.2f", dolar), 
            "Valor em euros", "€" + String.format("%.2f", euro), 
            "Valor em libras esterlinas", "£" + String.format("%.2f", libra));
    }

    @GetMapping("/horasparaminutos")
    @ResponseBody
    @Cacheable("horasparaminutos")
    public ResponseEntity<?> horasParaMinutos(@RequestParam double a, @RequestParam double b){

        if (a < 0 || b < 0){
            return respostaErro("Horas e minutos não podem ser negativos.");
        }

        double totalMinutos = (a * 60) + b;
        double totalSegundos = totalMinutos * 60;

        return respostaSucesso(
            "Horas para minutos", 
            "Horas", a, 
            "Minutos", b, 
            "Total em minutos", totalMinutos, 
            "Total em segundos", totalSegundos);
    }

 // Lista 2 
    @PostMapping("/menornumero")
    @ResponseBody
    @Cacheable("menornumero")
    public ResponseEntity<?> menornumero(@RequestBody RequestDoisValores req) {

        return respostaSucesso(
            "Menor número", 
            "Número A", req.getA(), 
            "Número B", req.getB(), 
            "Resultado", req.getA() <= req.getB() ? req.getA() : req.getB());
    }

    @PostMapping("/maiornumero")
    @ResponseBody
    @Cacheable("maiornumero")
    public ResponseEntity<?> maiornumero(@RequestBody RequestTresValores req) {

        if (req.getA() == req.getB() && req.getB() == req.getC()) {
            return respostaSucesso("Números", "A", req.getA(), "B", req.getB(), "C", req.getC(), "Resultado", "Os números são iguais.");
        }

        return respostaSucesso(
            "Maior número", 
            "Número A", req.getA(), 
            "Número B", req.getB(), 
            "Número C", req.getC(), 
            "O maior número é", Math.max(req.getA(), Math.max(req.getB(), req.getC())));
    }

    @PostMapping("/calculadorasimples")
    @ResponseBody
    @Cacheable("calculadorasimples")
    public ResponseEntity<?> calculadorasimples(@RequestBody CalculadoraRequest req) {

        switch (req.getOpcao()) {

            case 1 -> {

                double resultado = (req.getA() + req.getB()) / 2;
                String operacao = "Média entre os números";

                return respostaSucesso(
                    operacao, 
                    "Opção", req.getOpcao(), "A", req.getA(), "B", req.getB(), 
                    "Resultado", resultado);
            }
            case 2 -> {

                double resultado = Math.abs(req.getA() - req.getB());
                String operacao = "Diferença do maior pelo menor";

                return respostaSucesso(
                    operacao, 
                    "Opção", req.getOpcao(), "A", req.getA(), "B", req.getB(), 
                    "Resultado", resultado);
            }
            case 3 -> {

                double resultado = req.getA() * req.getB();
                String operacao = "Produto entre os números";

                return respostaSucesso(
                    operacao, 
                    "Opção", req.getOpcao(), "A", req.getA(), "B", req.getB(), 
                    "Resultado", resultado);
            }
            case 4 -> {

                if (req.getB() == 0) {
                    return respostaErro("A divisão por 0 não é permitida.");
                }

                double resultado = req.getA() / req.getB();
                String operacao = "Divisão do primeiro pelo segundo";

                return respostaSucesso(
                    operacao, 
                    "Opção", req.getOpcao(), "A", req.getA(), "B", req.getB(), 
                    "Resultado", resultado);
            }
            default -> {
                return respostaErro("Opção inválida. Escolha entre 1, 2, 3 ou 4.");
            }
        }
    }

    @PostMapping("/aumentode30")
    @ResponseBody
    @Cacheable("aumentode30")
    public ResponseEntity<?> aumentode30(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0) {
            return respostaErro("O salário deve ser um valor positivo.");
        }

        if (req.getA() <= 500) {

            double aumento = req.getA() * 0.30;

            return respostaSucesso(
                "Aumento de Salário", 
                "Salário original", "R$" + String.format("%.2f", req.getA()), 
                "Aumento concedido", "R$" + String.format("%.2f", aumento), 
                "Salário com aumento", "R$" + String.format("%.2f", req.getA() + aumento));

        } else {
            return respostaErro("O aumento de 30% é limitado a quem ganha até R$ 500,00.");
        }
    }

    @PostMapping("/aumentodesalario")
    @ResponseBody
    @Cacheable("aumentodesalario")
    public ResponseEntity<?> aumentoDeSalario(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
        }

        double aumento;

        if (req.getA() <= 300) {
            aumento = req.getA() * 0.35;

        } else {
            aumento = req.getA() * 0.15;
        }
        return respostaSucesso(
            "Aumento de salário", 
            "Salário original", "R$" + String.format("%.2f", req.getA()), 
            "Aumento de", "R$" + String.format("%.2f", aumento), 
            "Salário com aumento", "R$" + String.format("%.2f", req.getA() + aumento));
    }

    @PostMapping("/creditoespecial")
    @ResponseBody
    @Cacheable("creditoespecial")
    public ResponseEntity<?> creditoEspecial(@RequestBody RequestUmValor req) {

        double creditoEspecial;

        if (req.getA() <= 0) {
            return respostaErro("O saldo médio deve ser maior que zero.");
        }

        if (req.getA() > 400) {
            creditoEspecial = req.getA() * 0.30;

        } else if (req.getA() > 300) {
            creditoEspecial = req.getA() * 0.25;

        } else if (req.getA() > 200) {
            creditoEspecial = req.getA() * 0.20;

        } else {
            creditoEspecial = req.getA() * 0.10;
        }
        return respostaSucesso(
            "Crédito Especial", 
            "Saldo médio", "R$" + String.format("%.2f", req.getA()), 
            "Crédito especial concedido", "R$" + String.format("%.2f", creditoEspecial));
    }

    @PostMapping("/custoaoconsumidor")
    @ResponseBody
    @Cacheable("custoaoconsumidor")
    public ResponseEntity<?> custoAoConsumidor(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0) {
            return respostaErro("O custo de fábrica deve ser maior que zero.");
        }

        double distribuidor;
        double impostos;

        if (req.getA() <= 12000) {
            distribuidor = req.getA() * 0.05;
            impostos = 0;

        } else if (req.getA() <= 25000) {
            distribuidor = req.getA() * 0.10;
            impostos = req.getA() * 0.15;

        } else {
            distribuidor = req.getA() * 0.15;
            impostos = req.getA() * 0.20;
        }
        return respostaSucesso(
            "Custo ao consumidor", 
            "Custo de fábrica", "R$" + String.format("%.2f", req.getA()), 
            "Distribuidor", "R$" + String.format("%.2f", distribuidor), 
            "Impostos", "R$" + String.format("%.2f", impostos), 
            "Custo ao consumidor", "R$" + String.format("%.2f", req.getA() + distribuidor + impostos));
    }

    @PostMapping("/aumentodesalario2")
    @ResponseBody
    @Cacheable("aumentodesalario2")
    public ResponseEntity<?> aumentoDeSalario2(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
        }

        double aumento;

        if (req.getA() <= 300) {
            aumento = req.getA() * 0.15;

        } else if (req.getA() <= 600) {
            aumento = req.getA() * 0.10;

        } else if (req.getA() <= 900) {
            aumento = req.getA() * 0.05;

        } else {
            aumento = 0;
        }
        return respostaSucesso(
            "Aumento de salário", 
            "Salário original", "R$" + String.format("%.2f", req.getA()), 
            "Aumento", "R$" + String.format("%.2f", aumento), 
            "Salário com aumento", "R$" + String.format("%.2f", req.getA() + aumento));
    }

    @PostMapping("/gratificacao")
    @ResponseBody
    @Cacheable("gratificacao")
    public ResponseEntity<?> gratificacao(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
        }

        double desconto = req.getA() * 0.07;
        double gratificacao;

        if (req.getA() <= 350) {
            gratificacao = 100.0;

        } else if (req.getA() <= 600) {
            gratificacao = 75.0;

        } else if (req.getA() <= 900) {
            gratificacao = 50.0;

        } else {
            gratificacao = 35.0;
        }
        return respostaSucesso(
            "Gratificação", 
            "Salário", "R$" + String.format("%.2f", req.getA()), 
            "Desconto de 7%", "R$" + String.format("%.2f", desconto), 
            "Gratificação", "R$" + String.format("%.2f", gratificacao), 
            "Salário final", "R$" + String.format("%.2f", req.getA() - desconto + gratificacao));
    }

    @PostMapping("/tipodeproduto")
    @ResponseBody
    @Cacheable("tipodeproduto")
    public ResponseEntity<?> ativ(@RequestBody RequestUmValor req) {

        double aumento;
        String tipoProduto;

        if (req.getA() <= 0) {
            return respostaErro("O preço do produto deve ser positivo.");

        } else if (req.getA() <= 50) {
            aumento = req.getA() * 0.05;

        } else if (req.getA() <= 100) {
            aumento = req.getA() * 0.10;

        } else {
            aumento = req.getA() * 0.15;
        }

        double precoFinal = req.getA() + aumento;

        if (precoFinal < 80) {
            tipoProduto = "Barato";

        } else if (precoFinal <= 120) {
            tipoProduto = "Normal";

        } else if (precoFinal <= 200) {
            tipoProduto = "Caro";

        } else {
            tipoProduto = "Muito caro";
        }
        return respostaSucesso(
            "Tipo de produto", 
            "Preço do produto", "R$" + String.format("%.2f", req.getA()), 
            "Aumento", "R$" + String.format("%.2f", aumento), 
            "Tipo de produto", tipoProduto, 
            "Preço final", "R$" + String.format("%.2f", precoFinal));
    }

    @PostMapping("/aumentosalario3")
    @ResponseBody
    @Cacheable("aumentosalario3")
    public ResponseEntity<?> aumentosalario3(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0) {
            return respostaErro("O salário deve ser maior que zero.");
        }
        double aumento;

        if (req.getA() <= 300) {
            aumento = req.getA() * 0.50;

        } else if (req.getA() <= 500) {
            aumento = req.getA() * 0.40;

        } else if (req.getA() <= 700) {
            aumento = req.getA() * 0.30;

        } else if (req.getA() <= 800) {
            aumento = req.getA() * 0.20;

        } else if (req.getA() <= 1000) {
            aumento = req.getA() * 0.10;

        } else {
            aumento = req.getA() * 0.05;
        }
        return respostaSucesso(
            "Aumento de salário", 
            "Salário original", "R$" + String.format("%.2f", req.getA()), 
            "Aumento", "R$" + String.format("%.2f", aumento), 
            "Salário com aumento", "R$" + String.format("%.2f", req.getA() + aumento));
    }

    @PostMapping("/investimento")
    @ResponseBody
    @Cacheable("investimento")
    public ResponseEntity<?> investimento(@RequestBody InvestimentoRequest req) {

        if (req.getTipo() != 1 && req.getTipo() != 2) {
            return respostaErro("O tipo de investimento deve ser 1 (Poupança) ou 2 (Renda Fixa).");
        }
        if (req.getValor() <= 0 || req.getMeses() <= 0) {
            return respostaErro("Valor e meses devem ser positivos.");
        }

        double rendimento;

        if (req.getTipo() == 1) {
            rendimento = (req.getValor() * 0.03) * req.getMeses();

        } else {
            rendimento = (req.getValor() * 0.04) * req.getMeses();
        }
        double total = req.getValor() + rendimento;
        return respostaSucesso(
            "Investimento",
            "Tipo de investimento", req.getTipo() == 1 ? "Poupança" : "Fundos de Renda Fixa", 
            "Taxa de rendimento (%)", req.getTipo() == 1 ? "3%" : "4%", 
            "Valor investido", "R$" + String.format("%.2f", req.getValor()), 
            "Período (meses)", req.getMeses(), 
            "Rendimento", "R$" + String.format("%.2f", rendimento), 
            "Total após rendimento", "R$" + String.format("%.2f", total));
    }

    @PostMapping("/descontoproduto")
    @ResponseBody
    @Cacheable("descontoproduto")
    public ResponseEntity<?> descontoProduto(@RequestBody RequestDoisValores req) {

        if (req.getA() <= 0) {
            return respostaErro("O valor do produto deve ser maior que zero.");
        }
        double desconto = 0, porcentagem = 0;

        if (req.getA() > 30 && req.getA() <= 100) {
            desconto = req.getA() * 0.10;
            porcentagem = 10;

        } else if (req.getA() > 100) {
            desconto = req.getA() * 0.15;
            porcentagem = 15;
        }
        return respostaSucesso(
            "Desconto Produto", 
            "Valor do produto", "R$" + String.format("%.2f", req.getA()), 
            "Código do produto", req.getB(), 
            "Valor do desconto", "R$" + String.format("%.2f", desconto), 
            "Desconto de", porcentagem + "%", 
            "Total com desconto", "R$" + String.format("%.2f", req.getA() - desconto));
    }

    @PostMapping("/passwd")
    @ResponseBody
    @Cacheable("passwd")
    public ResponseEntity<?> validPasswd(@RequestBody PasswdRequest req) {

        String senhaRecebida = req.getA();
        boolean acessoPermitido = passwordEncoder.matches(senhaRecebida, senhaHasheada);

        String acesso = acessoPermitido ? "Permitido!" : "Negado!";

        return respostaSucesso(
            "Restrição de acesso",
            "Acesso", acesso);
    }

    @PostMapping("/maioridade")
    @ResponseBody
    @Cacheable("maioridade")
    public ResponseEntity<?> maiorIdade(@RequestBody RequestUmValor req) {

        return respostaSucesso(
            "Maior idade",
            "É maior", req.getA() >= 18 ? "Sim" : "Não", 
            "idade", req.getA());
    }

    @PostMapping("/pesoideal")
    @ResponseBody
    @Cacheable("pesoideal")
    public ResponseEntity<?> pesoIdeal(@RequestBody AlturaSexoRequest req) {

        if (req.getA() <= 0) {
            return respostaErro("A altura deve ser positiva.");
        }

        double pesoideal = 0;

        if (req.getB().equalsIgnoreCase("M")) {
            pesoideal = (72.7 * (req.getA() / 100)) - 58;

        } else if (req.getB().equalsIgnoreCase("F")){
            pesoideal = (62.2 * (req.getA() / 100)) - 44.7;

        } else {
            return  respostaErro("Use M ou F para o sexo.");
        }

        return respostaSucesso(
            "Peso ideal",
            "Seu peso ideal:", "KG" + pesoideal, 
            "Altura", req.getA() + "cm", 
            "Sexo", req.getB());
    }

    @PostMapping("/categoria")
    @ResponseBody
    @Cacheable("categoria")
    public ResponseEntity<?> categoria(@RequestBody RequestUmValor req) {

        if (req.getA() <= 4) {
            return respostaErro("A idade minima é de 5 anos.");
        }

        String categoria;

        if (req.getA() >= 5 && req.getA() <= 7){
            categoria = "Infantil";

        } else if (req.getA() >= 8 && req.getA() <= 10) {
            categoria = "Juvenil";

        } else if (req.getA() >= 11 && req.getA() <= 15) {
            categoria = "Adolescente";

        } else if (req.getA() >= 16 && req.getA() <= 30) {
            categoria = "Adulto";

        } else {
            categoria = "Sênior";  
        }
        
        return respostaSucesso(
            "Categoria",
            "categoria", categoria, 
            "Idade", req.getA());
    }

    @PostMapping("/procedencia")
    @ResponseBody
    @Cacheable("procedencia")
    public ResponseEntity<?> procedencia(@RequestBody RequestUmValor req) {

        if (req.getA() <= 0 || req.getA() > 30) {
            return respostaErro("Use valores de 1 a 30.");
        }

        String origem = "";

        if (req.getA() == 1){
            origem = "Sul";

        } else if (req.getA() == 2){
            origem = "Norte";

        } else if (req.getA() == 3){
            origem = "Leste";

        } else if (req.getA() == 4){
            origem = "Oeste";

        } else if (req.getA() == 5 || req.getA() == 6) {
            origem = "Nordeste";

        } else if (req.getA() >= 7 && req.getA() <= 9) {
            origem = "Sudeste";

        } else if (req.getA() >= 20 && req.getA() <= 20) {
            origem = "Centro-Oeste";

        } else {
            origem = "Noroeste";
        }

        return respostaSucesso(
            "Procedencia", 
            "Origem", origem, 
            "Procedencia", req.getA());
    }
    
    @PostMapping("/faixaderisco")
    @ResponseBody
    @Cacheable("faixaderisco")
    public ResponseEntity<?> faixaderisco(@RequestBody RequestDoisValores req) {

        if (req.getA() <= 0 || req.getB() <= 0) {
            return respostaErro("Use valores positivos.");
        }

        int grupo = 0;

        if (req.getA() >= 50 && req.getB() <= 60) {
            grupo = 3;

        } else if (req.getA() >= 50 && req.getB() <= 90) {
            grupo = 2;

        } else if (req.getA() >= 50 && req.getB() > 90) {
            grupo = 1;

        } else if (req.getA() >= 20 && req.getA() < 50 && req.getB() > 90) {
            grupo = 4;

        } else if (req.getA() >= 20 && req.getA() < 50 && req.getB() >= 60 && req.getB() <= 90) {
            grupo = 5;

        } else if (req.getA() >= 20 && req.getA() < 50 && req.getB() <= 60) {
            grupo = 6;

        } else if (req.getA() <= 20 && req.getB() > 90) {
            grupo = 7;

        } else if (req.getA() <= 20 && req.getB() >= 60 && req.getB() <= 90) {
            grupo = 8;

        } else if (req.getA() <= 20 && req.getB() <= 60) {
            grupo = 9;
        }
        return respostaSucesso(
            "Faixa de risco", 
            "Faixa", grupo, 
            "Idade", req.getA(), 
            "Peso", "KG" + req.getB());
    }

    @PostMapping("/descontoproduto2")
    @ResponseBody
    @Cacheable("descontoproduto2")
    public ResponseEntity<?> descontoproduto2(@RequestBody RequestDoisValores req) {
        if (req.getA() <= 0 || req.getB() <= 0) {
            return respostaErro("Use valores de 1 a 40.");
        }
        double valorUnitario=0, valorTotal=0, desconto=0, porcentagem=0;

        if (req.getA() >= 1 && req.getA() <= 10) {
            valorUnitario = 10;
            valorTotal = valorUnitario * req.getB();

        } else if (req.getA() >= 11 && req.getA() <= 20) {
            valorUnitario = 15;
            valorTotal = valorUnitario * req.getB();

        } else if (req.getA() >= 21 && req.getA() <= 30) {
            valorUnitario = 20;
            valorTotal = valorUnitario * req.getB();

        } else if (req.getA() >= 31) {
            valorUnitario = 30;
            valorTotal = valorUnitario * req.getB();
        }

        if (valorTotal <= 250) {
            desconto = valorTotal * 0.05;
            porcentagem = 5;

        } else if (valorTotal > 250 && valorTotal <= 500) {
            desconto = valorTotal * 0.10;
            porcentagem = 10;

        } else {
            desconto = valorTotal * 0.15;
            porcentagem = 15;
        }

        return respostaSucesso(
            "Desconto em produtos", 
            "Valor unitario", "R$" + valorUnitario, 
            "Valor total", "R$" + valorTotal, 
            "Desconto", "R$" + desconto, 
            "Porcentagem", porcentagem + "%", 
            "Valor final", "R$" + (valorTotal - desconto));
    }

    @PostMapping("/produtos")
    @ResponseBody
    @Cacheable("produtos")
    public ResponseEntity<?> produtos(@RequestBody ProdutosRequest req) {

        if (req.getA() <= 0 || req.getB() <= 0 || !(req.getC().equalsIgnoreCase("R")) && !(req.getC().equalsIgnoreCase("N"))) {
            return respostaErro("Use valores positivos e R para produtos refrigerados ou N para não refrigerados.");
        }

        double aumento=0, imposto=0;
        int porcentagem=0, porcentagemImposto=0;
        String categoria="";

        if (req.getA() <= 25){
            switch (req.getB()) {
                case 1 -> {
                    aumento = req.getA() * 0.05;
                    porcentagem = 5;

                    if (req.getC().equalsIgnoreCase("R")) {
                        imposto = req.getA() * 0.05;
                        porcentagemImposto = 5;

                    } else {
                        imposto = req.getA() * 0.08;
                        porcentagemImposto = 8;
                    }
                    break;}
                    
                case 2 -> {
                    aumento = req.getA() * 0.08;
                    porcentagem = 8;
                    imposto = req.getA() * 0.05;
                    porcentagemImposto = 5;
                    break;
                }
                case 3 -> {

                    aumento = req.getA() * 0.10;
                    porcentagem = 10;

                    if (req.getC().equalsIgnoreCase("R")) {
                        imposto = req.getA() * 0.05;
                        porcentagemImposto = 5;

                    } else {
                        imposto = req.getA() * 0.08;
                        porcentagemImposto = 8;
                    }
                    break;
                } 
                default -> {
                    break;
                }
            }
        } else if (req.getA() > 25) {

            switch (req.getB()) {

                case 1 -> {
                    aumento = req.getA() * 0.12;
                    porcentagem = 12;

                    if (req.getC().equalsIgnoreCase("R")) {
                        imposto = req.getA() * 0.05;
                        porcentagemImposto = 5;

                    } else {
                        imposto = req.getA() * 0.08;
                        porcentagemImposto = 8;
                    }
                    break;
                }
                    
                case 2 -> {
                    aumento = req.getA() * 0.15;
                    porcentagem = 15;
                    imposto = req.getA() * 0.05;
                    porcentagemImposto = 5;
                    break;
                }
                case 3 -> {
                    aumento = req.getA() * 0.18;
                    porcentagem = 18;

                    if (req.getC().equalsIgnoreCase("R")) {
                        imposto = req.getA() * 0.05;
                        porcentagemImposto = 5;

                    } else {
                        imposto = req.getA() * 0.08;
                        porcentagemImposto = 8;
                    }
                    break;
                } 
                default -> {
                    break;
                }
            }
        }

        if (((req.getA() + aumento)- imposto) <= 50) {
            categoria = "Barato";

        } else if (((req.getA() + aumento)- imposto) > 50 && ((req.getA() + aumento)- imposto) <= 120) {
            categoria = "Normal";

        } else {
            categoria = "Caro";
        }

        return respostaSucesso(
            "Produtos", 
            "Valor unitario", "R$" + req.getA(), 
            "Categoria", req.getB(), 
            "Refrigeração", req.getC(), 
            "Auemento", "R$" + aumento, 
            "Porcentagem", porcentagem + "%", 
            "Imposto", "R$" + imposto, 
            "Porcentagem imposto", porcentagemImposto + "%", 
            "Classificação", categoria, 
            "Novo preço", "R$" + ((req.getA()+aumento)- imposto));
    }

    @PostMapping("/gratificacao2")
    @ResponseBody
    @Cacheable("gratificacao2")
    public ResponseEntity<?> gratificacao2(@RequestBody RequestDoisValores req) {

        if (req.getA() == 0 || req.getB() == 0 ) {
            return respostaErro("Use valores positivos.");
        }

        double horas = req.getA() - (2.0 / 3.0 * req.getB()), gratificacao = 0;

        if (horas > 2400) {
            gratificacao = 500;

        } else if (horas >= 1800) {
            gratificacao = 400;

        } else if (horas >= 1200) {
            gratificacao = 300;

        } else if (horas >= 600) {
            gratificacao = 200;

        } else {
            gratificacao = 100;
        }
    return respostaSucesso(
        "Gratificação",
        "Horas extras", req.getA(),
        "Horas faltas", req.getB(),
        "Gratificação", gratificacao);
    }
// Lista 3
@GetMapping("/calcularpassagem/{tipo}/{valor}")
@ResponseBody
@Cacheable("passagem")

public ResponseEntity<?> CalcularPassagem(@PathVariable int tipo, @PathVariable double valor) {

    if (valor <= 0){
        return respostaErro("O valor da passagem deve ser positivo.");
    }

    if (tipo < 1 || tipo > 3) {
        return respostaErro("Tipo inválido. Use 1 para comum, 2 para estudante ou 3 para idoso.");
    }

    double valorFinal = 0;
    double percentualDesconto = 0;
    String tipoPassagem = "";

    switch (tipo) {
        case 1 -> {
            valorFinal = valor;
            percentualDesconto = 0;
            tipoPassagem = "Comum";
        }
        case 2 -> {
            valorFinal = valor * 0.75;
            percentualDesconto = 25;
            tipoPassagem = "Estudante";
        }
        case 3 -> {
            valorFinal = valor * 0.65;
            percentualDesconto = 35;
            tipoPassagem = "Idoso";
        }
    }

    return respostaSucesso(
        "Cálculo de passagem",
        "Valor original",
        "R$"+String.format("%.2f", valor),
        "Tipo de passagem", tipoPassagem,
        "Desconto aplicado", percentualDesconto + "%",
        "Valor a pagar", "R$" + String.format("%.2f", valorFinal));
}

@GetMapping("/calcularir/{cpf}/{salarioMinimo}/{dependentes}/{renda}")
@ResponseBody
@Cacheable("calcularir")
public ResponseEntity<?> CalcularIR(@PathVariable String cpf, @PathVariable double salarioMinimo, @PathVariable int dependentes, @PathVariable double renda) {

    if (salarioMinimo <= 0 || renda <= 0 || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
        return respostaErro("Dados incorretos: Por favor use valores acima de 0 e um cpf valido.");
    }

    double desconto = dependentes * (salarioMinimo * 0.05);
    double rendaLiquida = renda - desconto;
    double imposto = 0.0;
    String percentualImposto = "";

    if (rendaLiquida <= (salarioMinimo * 2)) {
        percentualImposto = "Isento";

    }else if (rendaLiquida > (salarioMinimo * 2) && rendaLiquida <= (salarioMinimo * 3)) {
        percentualImposto = "5%";
        imposto = rendaLiquida * 0.05;

    }else if (rendaLiquida > (salarioMinimo * 3) && rendaLiquida <= (salarioMinimo * 5)) {
        percentualImposto = "10%";
        imposto = rendaLiquida * 0.10;

    }else if (rendaLiquida > (salarioMinimo * 5) && rendaLiquida <= (salarioMinimo * 7)) {
        percentualImposto = "15%";
        imposto = rendaLiquida * 0.15;

    }else if (rendaLiquida > (salarioMinimo * 7)){
        percentualImposto = "20%";
        imposto = rendaLiquida * 0.20;
    }

    return respostaSucesso(
        "Calculo de IR",
        "CPF", cpf,
        "Salario minimo", "R$:" + salarioMinimo,
        "Renda", "R$:" + renda,
        "Dependentes", dependentes,
        "Desconto", "R$:" + desconto,
        "Renda Liquida", "R$:" + rendaLiquida,
        "Percentual de imposto", percentualImposto,
        "Valor cobrado", "R$:" + imposto);
    }
}