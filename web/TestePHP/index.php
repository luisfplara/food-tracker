<?php

require_once 'Functions.php';

$fun = new Functions();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $data = json_decode(file_get_contents("php://input"));
	
    header('Content-type:application/json');
    if ($_POST['request'] != null) {
	
        $data = json_decode($_POST['request']);
    }
	
    if (isset($data->operation)) {
    
        $operation = $data->operation;
        
        if (!empty($operation)) {
            
            if ($operation == 'registraVenda') {
              
                if (isset($data->data_plantio) && isset($data->data_colheita) && isset($data->data_venda) && isset($data->cod_rastreamento)) {
                    
                    $data_plantio  = $data->data_plantio;
                    $data_colheita = $data->data_colheita;
                    $data_venda    = $data->data_venda;
                    $cod_rastreamento    = $data->cod_rastreamento;
                    echo $fun->registraVenda($data_plantio, $data_colheita, $data_venda, $cod_rastreamento);
                } else {
                    
                    echo $fun->getMsgInvalidParam();
                    
                }
                
            }
        }
        
    } else {
        
        
        echo $fun->getMsgParamNotEmpty();
        
    }
}

else if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    
    
    echo "Rastreador de Batata";
    
}
