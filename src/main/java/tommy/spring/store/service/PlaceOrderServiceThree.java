package tommy.spring.store.service;

import org.springframework.transaction.annotation.Transactional;

import tommy.spring.store.dao.ItemDao;
import tommy.spring.store.dao.PaymentInfoDao;
import tommy.spring.store.dao.PurchaseOrderDao;
import tommy.spring.store.vo.Item;
import tommy.spring.store.vo.ItemNotFoundException;
import tommy.spring.store.vo.PaymentInfo;
import tommy.spring.store.vo.PurchaseOrder;
import tommy.spring.store.vo.PurchaseOrderRequest;
import tommy.spring.store.vo.PurchaseOrderResult;

public class PlaceOrderServiceThree implements PlaceOrderService {

	private ItemDao itemDao;
	private PaymentInfoDao paymentInfoDao;
	private PurchaseOrderDao purchaseOrderDao;
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}
	
	public void setPurchaseOrderDao(PurchaseOrderDao purchaseOrderDao) {
		this.purchaseOrderDao = purchaseOrderDao;
	}

	@Override
	@Transactional
	public PurchaseOrderResult order(PurchaseOrderRequest orderRequest) throws ItemNotFoundException {
		Item item = itemDao.findById(orderRequest.getItemId());
		if(item == null)
			throw new ItemNotFoundException(orderRequest.getItemId());
		
		PaymentInfo paymentInfo = new PaymentInfo(item.getPrice());
		paymentInfoDao.insert(paymentInfo);
		PurchaseOrder order = new PurchaseOrder(
				item.getId(), orderRequest.getAddress(), paymentInfo.getId());
		purchaseOrderDao.insert(order);
		
		return new PurchaseOrderResult(item, paymentInfo, order);
	}

}
