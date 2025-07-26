package com.pedro.lista;
public class InvestimentoRequest {

    private int tipo;
    private double valor;
    private int meses;

    public int getTipo() { return tipo; }
    public void setTipo(int tipo) { this.tipo = tipo; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    
    public int getMeses() { return meses; }
    public void setMeses(int meses) { this.meses = meses; }
}