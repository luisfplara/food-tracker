<?php

class DBOperations
{
    
    private $host = 'mysql.hostinger.com.br';
    private $user = 'u589963068_luis';
    private $db = 'u589963068_btt';
    private $pass = 'bp3j204k';
    private $conn;
    
   public function __construct() {

	$this -> conn = new PDO("mysql:host=".$this -> host.";dbname=".$this -> db, $this -> user, $this -> pass, array('charset'=>'utf8'));

}
    public function insertData($data_plantio, $data_colheita, $data_venda, $cod_rastreamento){
        
        
        
        $sql = 'INSERT INTO VendaBatata SET data_plantio =:data_plantio,data_colheita =:data_colheita,
    data_venda =:data_venda, cod_rastreamento =:cod_rastreamento';
        
        $query = $this->conn->prepare($sql);
        $query->execute(array(
            ':data_plantio' => $data_plantio,
            ':data_colheita' => $data_colheita,
            ':data_venda' => $data_venda,
			':cod_rastreamento' => $cod_rastreamento
        ));
        
        if ($query) {
            
            return true;
            
        } else {
            
            return false;
            
        }
    }
    public function getIdAluno($sno){
        $sql   = 'SELECT * FROM aluno WHERE sno = :sno';
        $query = $this->conn->prepare($sql);
        $query->execute(array(
            ':sno' => $sno
        ));
        
        if ($query) {
            $data = $query->fetchObject();
            
            return $data->idAluno;
            
        } else {
            
            return false;
        }
        
    }  
	public function checkCodeRastreio($cod_rastreamento){

    $sql = 'select count(*) from VendaBatata where cod_rastreamento=:cod_rastreamento;';

    $query = $this -> conn -> prepare($sql);
    $query -> execute(array(':cod_rastreamento' => $cod_rastreamento));

    if($query){

        $row_count = $query -> fetchColumn();

        if ($row_count == 0){

            return false;

        } else {

            return true;

        }
    } else {

        return false;
    }
 }
} ?>
