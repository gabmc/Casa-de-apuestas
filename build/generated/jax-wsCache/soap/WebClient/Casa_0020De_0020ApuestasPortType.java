
package WebClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Casa de ApuestasPortType", targetNamespace = "http://127.0.0.1:8000/Casa_de_Apuestas/default/call/soap")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Casa_0020De_0020ApuestasPortType {


    /**
     * 
     * @param parameters
     * @return
     *     returns WebClient.EnviarApuestaResponse
     */
    @WebMethod(action = "http://127.0.0.1:8000/Casa_de_Apuestas/default/call/soapenviarApuesta")
    @WebResult(name = "enviarApuestaResponse", targetNamespace = "http://127.0.0.1:8000/Casa_de_Apuestas/default/call/soap", partName = "parameters")
    public EnviarApuestaResponse enviarApuesta(
        @WebParam(name = "enviarApuesta", targetNamespace = "http://127.0.0.1:8000/Casa_de_Apuestas/default/call/soap", partName = "parameters")
        EnviarApuesta parameters);

}