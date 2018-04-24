package ee.leemet.helmes.repository;

import ee.leemet.helmes.entity.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class SectorRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public SectorRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Collection<Sector> getSectors() {
		String sql = "select id, name from sector";
		return jdbcTemplate.query(sql, (rs, rowNum) ->
				new Sector(rs.getLong("id"), rs.getString("name")));
	}
}
