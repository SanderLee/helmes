package ee.leemet.helmes.repository;

import ee.leemet.helmes.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class UserDataRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDataRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public UserData saveUserData(UserData userData) {
		if (userData.getId() == null) {
			userData = insertUserData(userData);
		} else {
			userData = updateUserData(userData);
		}
		return userData;
	}

	private UserData insertUserData(UserData userData) {
		Long userId = insertUser(userData.getName(), userData.isAgreeToTerms());
		userData.setId(userId);
		saveUserSectors(userId, userData.getSectorIds());
		return userData;
	}

	private UserData updateUserData(UserData userData) {
		updateUser(userData.getId(), userData.getName(), userData.isAgreeToTerms());
		saveUserSectors(userData.getId(), userData.getSectorIds());
		return userData;
	}

	private Long insertUser(String name, boolean agreeToTerms) {
		String sql = "insert into user (name, agree_to_terms) values (?, ?)";
		PreparedStatementCreator psc = connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setBoolean(2, agreeToTerms);
			return ps;
		};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	private void updateUser(Long userId, String name, boolean agreeToTerms) {
		String sql = "update user set name = ?, agree_to_terms = ? where id = ?";
		jdbcTemplate.update(sql, name, agreeToTerms, userId);
	}

	private void saveUserSectors(Long userId, Collection<Long> sectorIds) {
		deleteUserSectors(userId);
		insertUserSectors(userId, sectorIds);
	}

	private void deleteUserSectors(Long userId) {
		String sql = "delete from user_sectors where user_id = ?";
		jdbcTemplate.update(sql, userId);
	}

	private void insertUserSectors(Long userId, Collection<Long> sectorIds) {
		String sql = "insert into user_sectors (user_id, sector_id) values (?, ?)";
		sectorIds.forEach(sectorId -> jdbcTemplate.update(sql, userId, sectorId));
	}
}
