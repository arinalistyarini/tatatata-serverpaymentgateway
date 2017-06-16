/**
 * 
 */
package org.chamerling.heroku.service;

import com.serverpaymentgateway.model.Transaksi;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author chamerling
 * 
 */
@WebService
public interface HelloService {

	@WebMethod(operationName = "addTransaksi")
        public Boolean addTransaksi(@WebParam(name = "nominal") int nominal, @WebParam(name = "dariBank") String dariBank, @WebParam(name = "keBank") String keBank);
        
        @WebMethod(operationName = "getListTransaksi")
        public ArrayList<Transaksi> getListTransaksi();
        
        @WebMethod(operationName = "getTransaksi")
        public Transaksi getTransaksi(@WebParam(name = "waktu") Timestamp waktu);
}
