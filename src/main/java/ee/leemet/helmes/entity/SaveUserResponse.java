package ee.leemet.helmes.entity;

import java.util.Collection;

public class SaveUserResponse {
	private Collection<String> errors;
	private UserData userData;

	public Collection<String> getErrors() {
		return errors;
	}

	public void setErrors(Collection<String> errors) {
		this.errors = errors;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
}
