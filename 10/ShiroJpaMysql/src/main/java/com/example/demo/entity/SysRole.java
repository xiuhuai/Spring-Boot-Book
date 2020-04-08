package com.example.demo.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class SysRole {
    @Id@GeneratedValue
    /**
     *  编号
     */
    private Integer id;
    @Column(unique =true)
    /**
     * 角色标识程序中判断使用,如"admin",这个是唯一的
     */
    private String role;
    /**
     *  角色描述,UI界面显示使用
     */
    private String description;
    /**
     *  是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;
 /**
  * 角色权限关系：多对多关系;
  */
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;
  /**
   * 用户角色关系定义;
   */
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
   /**
    * 一个角色对应多个用户
    */
   private List<Admin> admins;

}