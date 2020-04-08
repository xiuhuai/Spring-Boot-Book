package com.example.demo.entity.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

@Entity
/*@Table(name = "sys_role")*/
public class UserRole {

    @Id
    @GeneratedValue
    private long id;

    private String rolename;
    private String cnname;

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
