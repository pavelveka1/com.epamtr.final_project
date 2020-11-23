package by.epamtr.airline.entity;

import java.io.Serializable;

/**
 * Entity class UserInfo
 * 
 * @author Pavel Veka
 *
 */
public class UserInfo implements Serializable {
	/**
	 * Serial version uid for serialization
	 */
	private static final long serialVersionUID = -5623520519697589263L;
	/**
	 * Unique identifier of info about user who has the same id
	 */
	private int idUserInfo;
	/**
	 * login
	 */
	private String login;
	/**
	 * password
	 */
	private String password;

	/**
	 * Constructor of UserInfo object with all parameters
	 * 
	 * @param idUserInfo
	 * @param login
	 * @param password
	 */
	public UserInfo(int idUserInfo, String login, String password) {
		super();
		this.idUserInfo = idUserInfo;
		this.login = login;
		this.password = password;
	}

	/**
	 * Constructor of UserInfo object without parameters
	 */
	public UserInfo() {

	}

	/**
	 * 
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 
	 * @param login new value
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password new value
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return id of UserInfo
	 */
	public int getIdUserInfo() {
		return idUserInfo;
	}

	/**
	 * 
	 * @param idUserInfo new value
	 */
	public void setIdUserInfo(int idUserInfo) {
		this.idUserInfo = idUserInfo;
	}

	/**
	 * @return hash code of object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUserInfo;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * @param obj - object with which is compared object this
	 * @return true - if objects are equals and false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (idUserInfo != other.idUserInfo)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "UserInfo [idUserInfo=" + idUserInfo + ", login=" + login + ", password=" + password + "]";
	}

}
