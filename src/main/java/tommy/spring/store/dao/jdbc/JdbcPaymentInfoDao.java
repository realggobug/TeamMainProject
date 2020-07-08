package tommy.spring.store.dao.jdbc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import tommy.spring.store.dao.PaymentInfoDao;
import tommy.spring.store.vo.PaymentInfo;

public class JdbcPaymentInfoDao implements PaymentInfoDao {

	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate namedjdbcTemplate;
	public JdbcPaymentInfoDao(SimpleJdbcInsert insert) {
		this.insert = insert;
		insert.withTableName("payment_info").usingColumns("payment_info_id","price");
	}
	public void setNamedjdbcTemplate(NamedParameterJdbcTemplate namedjdbcTemplate) {
		this.namedjdbcTemplate = namedjdbcTemplate;
	}
	public int nextVal() {
		return namedjdbcTemplate.queryForObject("select payment_seq.nextval from dual"
				, Collections.emptyMap(), Integer.class);
	}
	
	@Override
	public void insert(PaymentInfo paymentInfo) {
		Map<String, Object> paramValueMap = new HashMap<String, Object>();
		paymentInfo.setId(nextVal());
		paramValueMap.put("payment_info_id", paymentInfo.getId());
		paramValueMap.put("price", paymentInfo.getPrice());
		insert.execute(paramValueMap);
	}

}
