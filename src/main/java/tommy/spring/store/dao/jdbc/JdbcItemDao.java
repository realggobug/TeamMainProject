package tommy.spring.store.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import tommy.spring.store.dao.ItemDao;
import tommy.spring.store.vo.Item;

public class JdbcItemDao implements ItemDao {
	
	private JdbcTemplate jdbcTemplate;
	public JdbcItemDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Item findById(Integer itemId) {
		return jdbcTemplate.queryForObject(
				"select * from item where item_id=?",
				new Object[] {itemId},
				new RowMapper<Item>() {

					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						Item item = new Item();
						item.setId(rs.getInt("item_id"));
						item.setName(rs.getString("item_name"));
						item.setPrice(rs.getInt("price"));
						return item;
					}
				});
	}

}
