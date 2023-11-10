/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

/**
 *
 * @author Luis Fernando Pinto
 */
public class Venda {
    private String operation;
    private String data_plantio;
    private String data_colheita;
    private String data_venda;
    private String cod_rastreamento;
    private int qtdPeso;
    private int id;

    public int getQtdPeso() {
        return qtdPeso;
    }

    public void setQtdPeso(int qtdPeso) {
        this.qtdPeso = qtdPeso;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCod_rastreamento() {
        return cod_rastreamento;
    }

    public void setCod_rastreamento(String cod_rastreamento) {
        this.cod_rastreamento = cod_rastreamento;
    }
        
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getData_plantio() {
        return data_plantio;
    }

    public void setData_plantio(String data_plantio) {
        this.data_plantio = data_plantio;
    }

    public String getData_colheita() {
        return data_colheita;
    }

    public void setData_colheita(String data_colheita) {
        this.data_colheita = data_colheita;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }
}
