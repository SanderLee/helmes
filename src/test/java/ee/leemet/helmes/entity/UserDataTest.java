package ee.leemet.helmes.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class UserDataTest {

	@Test
	public void testValidate_success() {
		UserData userData = new UserData();
		userData.setName("Sander");
		userData.setAgreeToTerms(true);
		userData.setSectorIds(Arrays.asList(1L, 2L, 3L));

		Collection<String> errors = userData.validate();
		Assert.assertTrue("UserData should not have any errors", errors.isEmpty());
	}

	@Test
	public void testValidate_error() {
		UserData userData = new UserData();

		Collection<String> errors = userData.validate();
		Assert.assertEquals("UserData should have 3 errors", 3, errors.size());

		userData.setName("Sander");

		errors = userData.validate();
		Assert.assertEquals("UserData should have 2 errors", 2, errors.size());

		userData.setAgreeToTerms(true);

		errors = userData.validate();
		Assert.assertEquals("UserData should have 1 error", 1, errors.size());
	}
}
