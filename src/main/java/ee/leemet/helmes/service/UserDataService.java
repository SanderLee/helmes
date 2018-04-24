package ee.leemet.helmes.service;

import ee.leemet.helmes.entity.SaveUserResponse;
import ee.leemet.helmes.entity.UserData;
import ee.leemet.helmes.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserDataService {

	private UserDataRepository userDataRepository;

	@Autowired
	public UserDataService(UserDataRepository userDataRepository) {
		this.userDataRepository = userDataRepository;
	}

	@Transactional
	public SaveUserResponse saveUserData(UserData userData) {
		SaveUserResponse response = new SaveUserResponse();
		Collection<String> errors = userData.validate();
		if (errors.isEmpty()) {
			response.setUserData(userDataRepository.saveUserData(userData));
		} else {
			response.setErrors(errors);
		}
		return response;
	}
}
