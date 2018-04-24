package ee.leemet.helmes.service;

import ee.leemet.helmes.entity.UserData;
import ee.leemet.helmes.repository.UserDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class UserDataServiceTest {

	@Mock
	private UserDataRepository userDataRepository;

	@InjectMocks
	private UserDataService userDataService;

	@Test
	public void testSaveUserData_success() {
		UserData userData = Mockito.mock(UserData.class);
		Mockito.when(userData.validate()).thenReturn(new ArrayList<>());

		userDataService.saveUserData(userData);
		Mockito.verify(userDataRepository).saveUserData(userData);
	}

	@Test
	public void testSaveUserData_error() {
		UserData userData = Mockito.mock(UserData.class);
		Mockito.when(userData.validate()).thenReturn(Arrays.asList("error1", "error2"));

		userDataService.saveUserData(userData);
		Mockito.verify(userDataRepository, Mockito.never()).saveUserData(userData);
	}
}
