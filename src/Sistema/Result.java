

package Sistema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("id")
@Expose
private String id;
@SerializedName("data_plantio")
@Expose
private String dataPlantio;
@SerializedName("data_colheita")
@Expose
private String dataColheita;
@SerializedName("data_venda")
@Expose
private String dataVenda;
@SerializedName("cod_rastreamento")
@Expose
private String codRastreamento;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getDataPlantio() {
return dataPlantio;
}

public void setDataPlantio(String dataPlantio) {
this.dataPlantio = dataPlantio;
}

public String getDataColheita() {
return dataColheita;
}

public void setDataColheita(String dataColheita) {
this.dataColheita = dataColheita;
}

public String getDataVenda() {
return dataVenda;
}

public void setDataVenda(String dataVenda) {
this.dataVenda = dataVenda;
}

public String getCodRastreamento() {
return codRastreamento;
}

public void setCodRastreamento(String codRastreamento) {
this.codRastreamento = codRastreamento;
}

}