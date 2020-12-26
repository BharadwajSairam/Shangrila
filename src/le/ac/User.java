package le.ac;

public class User {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public User(String name, String passwordHash) {
		super();
		this.name = name;
		this.passwordHash = passwordHash;
		// this.fullname = fullname;
	}

	String name;
	String passwordHash;
	// String fullname;

}
