package com.lpc.pojo;

import java.io.Serializable;
import java.util.Date;

public class SmbmsAddress implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.contact
     *
     * @mbggenerated
     */
    private String contact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.addressDesc
     *
     * @mbggenerated
     */
    private String addressdesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.postCode
     *
     * @mbggenerated
     */
    private String postcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.tel
     *
     * @mbggenerated
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.createdBy
     *
     * @mbggenerated
     */
    private Long createdby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.creationDate
     *
     * @mbggenerated
     */
    private Date creationdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.modifyBy
     *
     * @mbggenerated
     */
    private Long modifyby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.modifyDate
     *
     * @mbggenerated
     */
    private Date modifydate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_address.userId
     *
     * @mbggenerated
     */
    private Long userid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.id
     *
     * @return the value of smbms_address.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.id
     *
     * @param id the value for smbms_address.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.contact
     *
     * @return the value of smbms_address.contact
     *
     * @mbggenerated
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.contact
     *
     * @param contact the value for smbms_address.contact
     *
     * @mbggenerated
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.addressDesc
     *
     * @return the value of smbms_address.addressDesc
     *
     * @mbggenerated
     */
    public String getAddressdesc() {
        return addressdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.addressDesc
     *
     * @param addressdesc the value for smbms_address.addressDesc
     *
     * @mbggenerated
     */
    public void setAddressdesc(String addressdesc) {
        this.addressdesc = addressdesc == null ? null : addressdesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.postCode
     *
     * @return the value of smbms_address.postCode
     *
     * @mbggenerated
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.postCode
     *
     * @param postcode the value for smbms_address.postCode
     *
     * @mbggenerated
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.tel
     *
     * @return the value of smbms_address.tel
     *
     * @mbggenerated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.tel
     *
     * @param tel the value for smbms_address.tel
     *
     * @mbggenerated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.createdBy
     *
     * @return the value of smbms_address.createdBy
     *
     * @mbggenerated
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.createdBy
     *
     * @param createdby the value for smbms_address.createdBy
     *
     * @mbggenerated
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.creationDate
     *
     * @return the value of smbms_address.creationDate
     *
     * @mbggenerated
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.creationDate
     *
     * @param creationdate the value for smbms_address.creationDate
     *
     * @mbggenerated
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.modifyBy
     *
     * @return the value of smbms_address.modifyBy
     *
     * @mbggenerated
     */
    public Long getModifyby() {
        return modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.modifyBy
     *
     * @param modifyby the value for smbms_address.modifyBy
     *
     * @mbggenerated
     */
    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.modifyDate
     *
     * @return the value of smbms_address.modifyDate
     *
     * @mbggenerated
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.modifyDate
     *
     * @param modifydate the value for smbms_address.modifyDate
     *
     * @mbggenerated
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_address.userId
     *
     * @return the value of smbms_address.userId
     *
     * @mbggenerated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_address.userId
     *
     * @param userid the value for smbms_address.userId
     *
     * @mbggenerated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }
}