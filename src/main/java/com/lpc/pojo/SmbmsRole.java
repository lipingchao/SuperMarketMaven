package com.lpc.pojo;

import java.io.Serializable;
import java.util.Date;

public class SmbmsRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.roleCode
     *
     * @mbggenerated
     */
    private String rolecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.roleName
     *
     * @mbggenerated
     */
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.createdBy
     *
     * @mbggenerated
     */
    private Long createdby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.creationDate
     *
     * @mbggenerated
     */
    private Date creationdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.modifyBy
     *
     * @mbggenerated
     */
    private Long modifyby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column smbms_role.modifyDate
     *
     * @mbggenerated
     */
    private Date modifydate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.id
     *
     * @return the value of smbms_role.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.id
     *
     * @param id the value for smbms_role.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.roleCode
     *
     * @return the value of smbms_role.roleCode
     *
     * @mbggenerated
     */
    public String getRolecode() {
        return rolecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.roleCode
     *
     * @param rolecode the value for smbms_role.roleCode
     *
     * @mbggenerated
     */
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode == null ? null : rolecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.roleName
     *
     * @return the value of smbms_role.roleName
     *
     * @mbggenerated
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.roleName
     *
     * @param rolename the value for smbms_role.roleName
     *
     * @mbggenerated
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.createdBy
     *
     * @return the value of smbms_role.createdBy
     *
     * @mbggenerated
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.createdBy
     *
     * @param createdby the value for smbms_role.createdBy
     *
     * @mbggenerated
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.creationDate
     *
     * @return the value of smbms_role.creationDate
     *
     * @mbggenerated
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.creationDate
     *
     * @param creationdate the value for smbms_role.creationDate
     *
     * @mbggenerated
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.modifyBy
     *
     * @return the value of smbms_role.modifyBy
     *
     * @mbggenerated
     */
    public Long getModifyby() {
        return modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.modifyBy
     *
     * @param modifyby the value for smbms_role.modifyBy
     *
     * @mbggenerated
     */
    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column smbms_role.modifyDate
     *
     * @return the value of smbms_role.modifyDate
     *
     * @mbggenerated
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column smbms_role.modifyDate
     *
     * @param modifydate the value for smbms_role.modifyDate
     *
     * @mbggenerated
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}