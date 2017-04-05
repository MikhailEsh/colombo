//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.27 at 02:20:20 PM MSK 
//


package main.schemas.srvprivateoperstate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransferLineInfo_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransferLineInfo_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SrcCurAmt" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="Turnover">
 *           &lt;simpleType>
 *             &lt;restriction base="{}String">
 *               &lt;enumeration value="CHARGE"/>
 *               &lt;enumeration value="RECEIPT"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferLineInfo_Type", propOrder = {
    "srcCurAmt",
    "turnover"
})
public class TransferLineInfoType {

    @XmlElement(name = "SrcCurAmt", required = true)
    protected Object srcCurAmt;
    @XmlElement(name = "Turnover", required = true)
    protected String turnover;

    /**
     * Gets the value of the srcCurAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSrcCurAmt() {
        return srcCurAmt;
    }

    /**
     * Sets the value of the srcCurAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSrcCurAmt(Object value) {
        this.srcCurAmt = value;
    }

    /**
     * Gets the value of the turnover property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTurnover() {
        return turnover;
    }

    /**
     * Sets the value of the turnover property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTurnover(String value) {
        this.turnover = value;
    }

}