package com.security.complete.mapping;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user", catalog = "springsecurity")
public class User implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -2157474406694015050L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;
	private String loginId;
	private String password;
	private Date createdtime;
	private String name;
	private String mobile;
	private Long channel;
	private String pushId;
	private String loginIdType;
	private String username;
	private boolean isEnabled;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private List<Role> roles;

	public User() {
	}

	/**
	 * @param userId
	 * @param loginId
	 * @param password
	 * @param createdtime
	 * @param name
	 * @param mobile
	 * @param channel
	 * @param pushId
	 * @param loginIdType
	 * @param username
	 * @param roles
	 */
	public User(Long userId, String loginId, String password, Date createdtime, String name, String mobile,
			Long channel, String pushId, String loginIdType, String username, boolean isEnabled, List<Role> roles) {
		super();
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.createdtime = createdtime;
		this.name = name;
		this.mobile = mobile;
		this.channel = channel;
		this.pushId = pushId;
		this.loginIdType = loginIdType;
		this.username = username;
		this.isEnabled = isEnabled;
		this.roles = roles;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "login_id", length = 100)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdtime", length = 19)
	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "channel")
	public Long getChannel() {
		return this.channel;
	}

	public void setChannel(Long channel) {
		this.channel = channel;
	}

	@Column(name = "push_id", length = 200)
	public String getPushId() {
		return this.pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	@Column(name = "login_id_type", length = 100)
	public String getLoginIdType() {
		return this.loginIdType;
	}

	public void setLoginIdType(String loginIdType) {
		this.loginIdType = loginIdType;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the email to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the isEnabled
	 */
	@Column(columnDefinition = "boolean default true")
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled
	 *            the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
