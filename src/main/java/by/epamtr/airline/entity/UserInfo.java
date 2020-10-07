package by.epamtr.airline.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5623520519697589263L;
	private int idUserInfo;
	private String login;
	private String password;

	public UserInfo(int idUserInfo, String login, String password) {
		super();
		this.idUserInfo=idUserInfo;
		this.login = login;
		this.password = password;
	}

	public UserInfo() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public int getIdUserInfo() {
		return idUserInfo;
	}

	public void setIdUserInfo(int idUserInfo) {
		this.idUserInfo = idUserInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUserInfo;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

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

	@Override
	public String toString() {
		return "UserInfo [idUserInfo=" + idUserInfo + ", login=" + login + ", password=" + password + "]";
	}

	
	
}
