/**
 * 
 */
package org.chamerling.heroku.servicepaymentgateway;

import com.serverpaymentgateway.model.*;
import java.util.ArrayList;
import java.util.Date;
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
        public Boolean addTransaksi(@WebParam(name="noCc") String noCc, @WebParam(name = "nominal") int nominal, @WebParam(name = "dariBank") String dariBank, @WebParam(name = "keBank") String keBank);
        
        @WebMethod(operationName = "getListTransaksi")
        public ArrayTransaksi getListTransaksi();
        
        @WebMethod(operationName = "getTransaksi")
        public Transaksi getTransaksi(@WebParam(name="noCc") String noCc, @WebParam(name = "waktu") Date waktu);
}
