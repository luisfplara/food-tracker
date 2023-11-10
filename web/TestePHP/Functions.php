<?php

require_once 'DBOperations.php';

class Functions{
    
    private $db;
    
    public function __construct()
    {
        
        $this->db = new DBOperations();
        
    }
    public function registraVenda($data_plantio, $data_colheita, $data_venda, $cod_rastreamento){
        
        $db = $this->db;
        
        if (!empty($data_plantio) && !empty($data_colheita) && !empty($data_venda)&& !empty($cod_rastreamento)) {
            if ($db -> checkCodeRastreio($cod_rastreamento)) {

  			$response["result"] = "Falha";
  			$response["message"] = "J? existe um codigo identico!!";
			$response["erroCode"] = "e1";
  			return json_encode($response);

  		} else{
			
            $result = $db->insertData($data_plantio, $data_colheita, $data_venda, $cod_rastreamento);
            
            if ($result) {
                
                $response["result"]  = "success";
                $response["message"] = "User Registered Successfully !" . $tipoUsuario;
                return json_encode($response);
                
            } else {
                
                $response["result"]  = "failure";
                $response["message"] = "Registration Failure ";
                return json_encode($response);
                
            }
		}
            
        } else {
            
            return $this->getMsgParamNotEmpty();
            
        }
    }
	public function getMsgParamNotEmpty(){


  $response["result"] = "failure";
  $response["message"] = "Parameters should not be empty !";
  return json_encode($response);

}
	public function getMsgInvalidParam(){

  $response["result"] = "failure";
  $response["message"] = "Invalid Parameters";
  return json_encode($response);

}	
}
?>