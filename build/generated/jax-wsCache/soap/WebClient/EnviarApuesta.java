
package WebClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="listaApuestas" type="{http://127.0.0.1:8000/Casa_de_Apuestas/default/call/soap}ArrayOfenviarApuestalistaApuestas"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "enviarApuesta")
public class EnviarApuesta {

    @XmlElement(required = true)
    protected ArrayOfenviarApuestalistaApuestas listaApuestas;

    /**
     * Gets the value of the listaApuestas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfenviarApuestalistaApuestas }
     *     
     */
    public ArrayOfenviarApuestalistaApuestas getListaApuestas() {
        return listaApuestas;
    }

    /**
     * Sets the value of the listaApuestas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfenviarApuestalistaApuestas }
     *     
     */
    public void setListaApuestas(ArrayOfenviarApuestalistaApuestas value) {
        this.listaApuestas = value;
    }

}
