
package WebClient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idParticipante" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes", propOrder = {
    "idParticipante"
})
public class ArrayOfArrayOfenviarApuestalistaApuestasapuestaParticipanteslistaParticipantes {

    @XmlElement(type = Integer.class)
    protected List<Integer> idParticipante;

    /**
     * Gets the value of the idParticipante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idParticipante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdParticipante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getIdParticipante() {
        if (idParticipante == null) {
            idParticipante = new ArrayList<Integer>();
        }
        return this.idParticipante;
    }

}
