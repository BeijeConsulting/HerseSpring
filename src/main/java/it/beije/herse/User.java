package it.beije.herse;

public class User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("{firstName: ").append(firstName)
				.append(", lastName: ").append(lastName)
				.append(", username: ").append(username)
				.append(", password: ").append(password)
				.append("}");
		
		return builder.toString();
	}

}
