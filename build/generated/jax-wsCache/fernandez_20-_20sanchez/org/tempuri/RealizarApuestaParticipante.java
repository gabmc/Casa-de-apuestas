
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;sequence>
 *         &lt;element name="idParticipante" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idMaquina" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idDetallePe" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idParticipante",
    "idMaquina",
    "monto",
    "idDetallePe",
    "token"
})
@XmlRootElement(name = "realizarApuestaParticipante")
public class RealizarApuestaParticipante {

    protected int idParticipante;
    protected int idMaquina;
    protected int monto;
    protected int idDetallePe;
    protected String token;

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
     * Gets the value of the idMaquina property.
     * 
     */
    public int getIdMaquina() {
        return idMaquina;
    }

    /**
     * Sets the value of the idMaquina property.
     * 
     */
    public void setIdMaquina(int value) {
        this.idMaquina = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     */
    public int getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     */
    public void setMonto(int value) {
        this.monto = value;
    }

    /**
     * Gets the value of the idDetallePe property.
     * 
     */
    public int getIdDetallePe() {
        return idDetallePe;
    }

    /**
     * Sets the value of the idDetallePe property.
     * 
     */
    public void setIdDetallePe(int value) {
        this.idDetallePe = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

}
