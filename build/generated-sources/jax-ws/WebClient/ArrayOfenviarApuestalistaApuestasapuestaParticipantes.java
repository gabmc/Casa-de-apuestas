
package WebClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfenviarApuestalistaApuestasapuestaParticipantes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfenviarApuestalistaApuestasapuestaParticipantes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="listaParticipantes" type="{http://192.168.19.191:8000/Casa_de_Apuestas/default/call/soap}ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfenviarApuestalistaApuestasapuestaParticipantes", propOrder = {

})
public class ArrayOfenviarApuestalistaApuestasapuestaParticipantes {

    @XmlElement(required = true)
    protected ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes listaParticipantes;

    /**
     * Gets the value of the listaParticipantes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes }
     *     
     */
    public ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes getListaParticipantes() {
        return listaParticipantes;
    }

    /**
     * Sets the value of the listaParticipantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes }
     *     
     */
    public void setListaParticipantes(ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes value) {
        this.listaParticipantes = value;
    }

}
