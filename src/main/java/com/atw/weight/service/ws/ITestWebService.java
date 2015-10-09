package com.atw.weight.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ITestWebService {

	@WebMethod
	public String sayHi(@WebParam(name = "who") String who);

}
