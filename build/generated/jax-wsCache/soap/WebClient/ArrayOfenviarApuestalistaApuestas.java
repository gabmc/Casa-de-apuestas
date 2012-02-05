
package WebClient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfenviarApuestalistaApuestas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfenviarApuestalistaApuestas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apuesta" type="{http://192.168.19.191:8000/Casa_de_Apuestas/default/call/soap}ArrayOfenviarApuestalistaApuestasapuesta" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfenviarApuestalistaApuestas", propOrder = {
    "apuesta"
})
public class ArrayOfenviarApuestalistaApuestas {

    protected List<ArrayOfenviarApuestalistaApuestasapuesta> apuesta;

    /**
     * Gets the value of the apuesta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apuesta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApuesta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfenviarApuestalistaApuestasapuesta }
     * 
     * 
     */
    public List<ArrayOfenviarApuestalistaApuestasapuesta> getApuesta() {
        if (apuesta == null) {
            apuesta = new ArrayList<ArrayOfenviarApuestalistaApuestasapuesta>();
        }
        return this.apuesta;
    }

}
