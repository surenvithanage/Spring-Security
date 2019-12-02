
package com.security.complete.mapping;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "role", catalog = "springsecurity")
public class Role implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5943416112489776833L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", unique = true, nullable = false)
	private Long roleId;
	@NotBlank
	private String name;
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<User> users;

	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param name
	 */
	public Role(@NotNull String name) {
		super();
		this.name = name;
	}

	/**
	 * @param roleId
	 * @param name
	 * @param users
	 */
	public Role(Long roleId, @NotNull String name, List<User> users) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.users = users;
	}

	/**
	 * @return the id
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the id to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
