package Sistema;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
  
  
public class SendData {

	private final String USER_AGENT = "Mozilla/5.0";


	// HTTP POST request
	public void sendPost(String DataC, String DataP,String DataV,int qtdSelos, int qtdPeso) throws Exception {

		String url = "http://rastreadordebatata.pe.hu/TestePHP/";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    
		//add reuqest header
		con.setRequestMethod("POST");
		String CodRestreamento = GeradorCod();
		// Send post request
                GenerateBarcodes GB = new GenerateBarcodes();
                GB.createPDF(DataV, CodRestreamento,qtdSelos);
		con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	
                Venda u = new Venda();
                u.setData_colheita(DataC);
                u.setData_plantio(DataP);
                u.setData_venda(DataV);
                u.setOperation("registraVenda");
                u.setCod_rastreamento(CodRestreamento);
                u.setQtdPeso(qtdPeso);
                Gson g = new Gson();
                System.out.println(g.toJson(u));
                ps.print(g.toJson(u));
                 

		int responseCode = con.getResponseCode();

		System.out.println("Response Code : " + responseCode);
                try {
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));               
                System.out.println(in);           
                //esponse r = g.fromJson(in,response.class);
                    //System.out.println(r.getResult());
                              
                  
                   in.close();
                  
                }catch (IOException e) {
			e.printStackTrace();
		}
                wr.close();
                

		//print result
              
            
                
	}
        
        private String GeradorCod(){


    String[] carct ={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z", "0","1","2","3","4","5","6","7","8","9"};

    String senha="";


    for (int x=0; x<18; x++){
        int j = (int) (Math.random()*carct.length);
        senha += carct[j];
    }
            System.out.println(senha);
            return senha;
                }
       }