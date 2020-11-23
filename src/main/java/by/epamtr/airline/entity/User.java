package by.epamtr.airline.entity;

import java.io.Serializable;

/**
 * Entity class User
 * 
 * @author Pavel Veka
 *
 */
public class User implements Serializable {
	/**
	 * Serial version uid for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Unique identifier of user
	 */
	private int idUser;
	/**
	 * Name of user
	 */
	private String name;
	/**
	 * Surname of user
	 */
	private String surname;
	/**
	 * Patronimic of user
	 */
	private String patronimic;
	/**
	 * Email of user
	 */
	private String email;
	/**
	 * Object which contains name of role of user in airline company
	 */
	private UserRole role;

	/**
	 * Constructor of User object with parameters
	 * 
	 * @param idUser
	 * @param name
	 * @param surname
	 * @param patronimic
	 * @param email
	 * @param role
	 */
	public User(int idUser, String name, String surname, String patronimic, String email, UserRole role) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.patronimic = patronimic;
		this.email = email;
		this.role = role;

	}

	/**
	 * Constructor of User object without parameters
	 */
	public User() {

	}

	/**
	 * 
	 * @return name of user
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name new value
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return surname of user
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * 
	 * @param surname new value
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * 
	 * @return patronimic of user
	 */
	public String getPatronimic() {
		return patronimic;
	}

	/**
	 * 
	 * @param patronimic new value
	 */
	public void setPatronimic(String patronimic) {
		this.patronimic = patronimic;
	}

	/**
	 * 
	 * @return object UserRole which contains name of role of user in airline
	 *         company
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * 
	 * @param role new value
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * 
	 * @return id of user
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * 
	 * @param idUser new value
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * 
	 * @return email of user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email new value
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return hash code of object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idUser;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patronimic == null) ? 0 : patronimic.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUser != other.idUser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patronimic == null) {
			if (other.patronimic != null)
				return false;
		} else if (!patronimic.equals(other.patronimic))
			return false;
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", surname=" + surname + ", patronimic=" + patronimic
				+ ", email=" + email + ", role=" + role + "]";
	}

}
