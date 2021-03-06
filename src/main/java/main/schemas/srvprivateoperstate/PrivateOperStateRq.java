//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.27 at 02:20:20 PM MSK 
//


package main.schemas.srvprivateoperstate;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * ��� ���������-������� ��������� ���������� �������� �������� ����������
 *             
 * 
 * <p>Java class for PrivateOperStateRq_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrivateOperStateRq_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RqUID" type="{}RqUID_Type"/>
 *         &lt;element name="RqTm" type="{}DateTime"/>
 *         &lt;element name="OperUID" type="{}UID_Type"/>
 *         &lt;element name="SPName" type="{}SPName_Type"/>
 *         &lt;element name="BankInfo" type="{}BankInfo_Type" minOccurs="0"/>
 *         &lt;element name="SystemId" type="{}SystemId_Type"/>
 *         &lt;element name="OperDate" type="{}Date"/>
 *         &lt;element name="OperID" type="{}String" minOccurs="0"/>
 *         &lt;element name="OperType">
 *           &lt;simpleType>
 *             &lt;restriction base="{}String">
 *               &lt;enumeration value="FullResult"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SegmentId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{}String">
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
@XmlRootElement(name = "PrivateOperStateRq")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrivateOperStateRq_Type", propOrder = {
    "rqUID",
    "rqTm",
    "operUID",
    "spName",
    "bankInfo",
    "systemId",
    "operDate",
    "operID",
    "operType",
    "segmentId"
})
public class PrivateOperStateRq {

    @XmlElement(name = "RqUID", required = true)
    protected String rqUID;
    @XmlElement(name = "RqTm", required = true)
    protected XMLGregorianCalendar rqTm;
    @XmlElement(name = "OperUID", required = true)
    protected String operUID;
    @XmlElement(name = "SPName", required = true)
    protected SPNameType spName;
    @XmlElement(name = "BankInfo")
    protected BankInfoType bankInfo;
    @XmlElement(name = "SystemId", required = true)
    protected String systemId;
    @XmlElement(name = "OperDate", required = true)
    protected XMLGregorianCalendar operDate;
    @XmlElement(name = "OperID")
    protected String operID;
    @XmlElement(name = "OperType", required = true)
    protected String operType;
    @XmlElement(name = "SegmentId")
    protected String segmentId;

    /**
     * Gets the value of the rqUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqUID() {
        return rqUID;
    }

    /**
     * Sets the value of the rqUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqUID(String value) {
        this.rqUID = value;
    }

    /**
     * Gets the value of the rqTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRqTm() {
        return rqTm;
    }

    /**
     * Sets the value of the rqTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRqTm(XMLGregorianCalendar value) {
        this.rqTm = value;
    }

    /**
     * Gets the value of the operUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperUID() {
        return operUID;
    }

    /**
     * Sets the value of the operUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperUID(String value) {
        this.operUID = value;
    }

    /**
     * Gets the value of the spName property.
     * 
     * @return
     *     possible object is
     *     {@link SPNameType }
     *     
     */
    public SPNameType getSPName() {
        return spName;
    }

    /**
     * Sets the value of the spName property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPNameType }
     *     
     */
    public void setSPName(SPNameType value) {
        this.spName = value;
    }

    /**
     * Gets the value of the bankInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BankInfoType }
     *     
     */
    public BankInfoType getBankInfo() {
        return bankInfo;
    }

    /**
     * Sets the value of the bankInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankInfoType }
     *     
     */
    public void setBankInfo(BankInfoType value) {
        this.bankInfo = value;
    }

    /**
     * Gets the value of the systemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the value of the systemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * Gets the value of the operDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperDate() {
        return operDate;
    }

    /**
     * Sets the value of the operDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperDate(XMLGregorianCalendar value) {
        this.operDate = value;
    }

    /**
     * Gets the value of the operID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperID() {
        return operID;
    }

    /**
     * Sets the value of the operID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperID(String value) {
        this.operID = value;
    }

    /**
     * Gets the value of the operType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperType() {
        return operType;
    }

    /**
     * Sets the value of the operType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperType(String value) {
        this.operType = value;
    }

    /**
     * Gets the value of the segmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * Sets the value of the segmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentId(String value) {
        this.segmentId = value;
    }

    @Override
    public String toString()
    {
        return ("RqUID=" + rqUID + " RqTm=" + rqTm + " OperUID=" + operUID + " SPName=" + spName + " BankInfo=" + bankInfo +
                " SystemId=" + systemId + " OperDate=" + operDate + " OperID=" + operID + " OperType=" + operType + " SegmentId" + segmentId);

    }
}
