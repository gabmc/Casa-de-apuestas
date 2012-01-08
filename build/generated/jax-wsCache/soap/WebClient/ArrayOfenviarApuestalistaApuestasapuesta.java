
package WebClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfenviarApuestalistaApuestasapuesta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfenviarApuestalistaApuestasapuesta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="idEvento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaApuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maquinaid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idParticipante" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="montoApuesta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfenviarApuestalistaApuestasapuesta", propOrder = {

})
public class ArrayOfenviarApuestalistaApuestasapuesta {

    protected int idEvento;
    @XmlElement(required = true)
    protected String fechaApuesta;
    protected int maquinaid;
    protected int idParticipante;
    protected int montoApuesta;

    /**
     * Gets the value of the idEvento property.
     * 
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * Sets the value of the idEvento property.
     * 
     */
    public void setIdEvento(int value) {
        this.idEvento = value;
    }

    /**
     * Gets the value of the fechaApuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaApuesta() {
        return fechaApuesta;
    }

    /**
     * Sets the value of the fechaApuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaApuesta(String value) {
        this.fechaApuesta = value;
    }

    /**
     * Gets the value of the maquinaid property.
     * 
     */
    public int getMaquinaid() {
        return maquinaid;
    }

    /**
     * Sets the value of the maquinaid property.
     * 
     */
    public void setMaquinaid(int value) {
        this.maquinaid = value;
    }

    /**
     * Gets the value of the idParticipante property.
     * 
     */
    public int getIdParticipante() {
        return idParticipante;
    }

    /**
     * Sets the value of the idParticipante property.
     * 
     */
    public void setIdParticipante(int value) {
        this.idParticipante = value;
    }

    /**
     * Gets the value of the montoApuesta property.
     * 
     */
    public int getMontoApuesta() {
        return montoApuesta;
    }

    /**
     * Sets the value of the montoApuesta property.
     * 
     */
    public void setMontoApuesta(int value) {
        this.montoApuesta = value;
    }

}
