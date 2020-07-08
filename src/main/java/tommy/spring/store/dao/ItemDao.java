package tommy.spring.store.dao;

import tommy.spring.store.vo.Item;

public interface ItemDao {
	Item findById(Integer itemId);
}
