package tommy.spring.store.service;

import tommy.spring.store.vo.ItemNotFoundException;
import tommy.spring.store.vo.PurchaseOrderRequest;
import tommy.spring.store.vo.PurchaseOrderResult;

public interface PlaceOrderService {
	public PurchaseOrderResult order(PurchaseOrderRequest orderRequest) 
		throws ItemNotFoundException;
}
