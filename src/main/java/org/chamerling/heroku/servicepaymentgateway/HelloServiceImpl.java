/**
 * 
 */
package org.chamerling.heroku.servicepaymentgateway;

import com.firebase.client.Firebase;
import com.serverpaymentgateway.model.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author chamerling
 * 
 */
public class HelloServiceImpl implements HelloService {
        private final String rootURL = "https://ta-paymentgateway.firebaseio.com/";

	@WebMethod(operationName = "addTransaksi")
        public Boolean addTransaksi(@WebParam(name="noCc") String noCc, @WebParam(name = "nominal") int nominal, @WebParam(name = "dariBank") String dariBank, @WebParam(name = "keBank") String keBank){
            Firebase ref = new Firebase(rootURL);
            
            //nulis riwayat transaksi
            String transaksiURL = noCc + "/" + System.currentTimeMillis(); // timestamp
            Firebase transaksiRef = ref.child(transaksiURL);
            Map<String, Object> transaction = new HashMap<String, Object>();
            
            transaction.put("nominal", nominal);
            transaction.put("dari_bank", dariBank);
            transaction.put("ke_bank", keBank);
            transaksiRef.updateChildren(transaction);
            
            return true;
        }
        
        @WebMethod(operationName = "getListTransaksi")
        public ArrayTransaksi getListTransaksi(){
            try {
                URL url = new URL(rootURL + ".json");
                URLConnection con = url.openConnection();
                JSONTokener json = new JSONTokener(con.getInputStream());
                JSONObject obj = new JSONObject(json);
                Iterator<String> data = obj.keys();
                ArrayList<Transaksi> t = new ArrayList<Transaksi>();
                                
                while(data.hasNext()){
                    String waktu = data.next();
                    
                    JSONObject getTrans = obj.getJSONObject(waktu);

                    Transaksi transaksi = new Transaksi();
                    transaksi.setDariBank(getTrans.getString("dari_bank"));
                    transaksi.setKeBank(getTrans.getString("ke_bank"));
                    transaksi.setNominal(getTrans.getInt("nominal"));
                    transaksi.setWaktu(new Date(Long.parseLong(waktu)));
                    t.add(transaksi);
                }
                ArrayTransaksi at = new ArrayTransaksi();
                at.setTransaksis(t);
                
                return at;
            } catch (IOException ex) {
                System.out.println(ex);
            }
            return null;
        }
        
        @WebMethod(operationName = "getTransaksi")
        public Transaksi getTransaksi(@WebParam(name="noCc") String noCc, @WebParam(name = "waktu") Date waktu){
            try {
                URL url = new URL(rootURL + noCc + "/" + waktu.getTime() + ".json");
                //System.out.println(rootURL + noCc + "/" + waktu + ".json");
                URLConnection con = url.openConnection();
                JSONTokener json = new JSONTokener(con.getInputStream());
                JSONObject obj = new JSONObject(json);
                Transaksi t = new Transaksi();

                t.setDariBank(obj.getString("dari_bank"));
                t.setKeBank(obj.getString("ke_bank"));
                t.setNominal(obj.getInt("nominal"));
                t.setWaktu(waktu);
                return t;
            } catch (IOException ex) {
                System.out.println(ex);
            }
            return null;
        }
        
        public ArrayNoCc getNoCc(){
            try {
                URL url = new URL(rootURL + ".json");
                URLConnection con = url.openConnection();
                JSONTokener json = new JSONTokener(con.getInputStream());
                JSONObject obj = new JSONObject(json);
                Iterator<String> data = obj.keys();
                ArrayList<String> noCcs = new ArrayList<String>();

                while(data.hasNext()){
                    String waktu = data.next();
                    noCcs.add(waktu);
                }
                ArrayNoCc aNoCcs = new ArrayNoCc();
                aNoCcs.setNoCcs(noCcs);

                return aNoCcs;
            } catch (IOException ex) {
                System.out.println(ex);
            }

            return null;
        }
}
