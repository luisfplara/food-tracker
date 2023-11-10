/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;

import java.io.PrintStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

import java.util.List;



import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;

import org.json.JSONObject;


/**
 *
 * @author Luis Fernando Pinto
 */
public class GetData {
    	public List<Venda> getPost() throws Exception {

		String url = "http://rastreadordebatata.pe.hu/TestePHP/";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    
		//add reuqest header
		con.setRequestMethod("POST");
		
		// Send post request
		con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                
                Gson g = new Gson();
                operation op = new operation();
                op.setOperation("getData");
                ps.print(g.toJson(op));
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
                
		StringBuffer response = new StringBuffer();                
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);   
		}                 
                 
                 ObjectMapper mapper = new ObjectMapper();
                 System.out.println(response.toString());
                 Venda []v = mapper.readValue(response.toString(), Venda[].class);
                 System.out.println(v[2].getId());
                 System.out.println(v.length);
                 List<Venda> listVenda = new ArrayList<Venda>();
                 for(int i = 0;i<v.length;i++){
                     listVenda.add(i, v[i]);
                     
                 }
                 System.out.println(listVenda.get(1).getCod_rastreamento());
                 in.close();
                 return listVenda;
     
	}
        
       
      
    
}
