package ee.leemet.helmes.entity;

public class Sector {

	private Long id;
	private String name;

	public Sector(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
