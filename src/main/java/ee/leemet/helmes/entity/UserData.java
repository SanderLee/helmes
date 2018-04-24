package ee.leemet.helmes.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserData {

	private Long id;
	private String name;
	private Collection<Long> sectorIds;
	private boolean agreeToTerms;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Long> getSectorIds() {
		return sectorIds;
	}

	public void setSectorIds(Collection<Long> sectorIds) {
		this.sectorIds = sectorIds;
	}

	public boolean isAgreeToTerms() {
		return agreeToTerms;
	}

	public void setAgreeToTerms(boolean agreeToTerms) {
		this.agreeToTerms = agreeToTerms;
	}

	public Collection<String> validate() {
		List<String> errors = new ArrayList<>();
		if (name == null || name.equals("")) {
			errors.add("Name must not be empty!");
		}
		if (sectorIds == null || sectorIds.isEmpty()) {
			errors.add("At least one sector must be selected!");
		}
		if (!agreeToTerms) {
			errors.add("'Agree to terms' must be checked!");
		}
		return errors;
	}
}
