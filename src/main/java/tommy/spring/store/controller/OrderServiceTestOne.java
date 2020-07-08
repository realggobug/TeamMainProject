package tommy.spring.store.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tommy.spring.store.service.PlaceOrderService;
import tommy.spring.store.vo.PurchaseOrderRequest;
import tommy.spring.store.vo.PurchaseOrderResult;

public class OrderServiceTestOne {
	private PlaceOrderService placeOrderService;
	private AbstractApplicationContext context;
	public OrderServiceTestOne() {
		String[] configLocations = new String[] {"transactionOne.xml"};
		context = new ClassPathXmlApplicationContext(configLocations);
		placeOrderService = (PlaceOrderService) context.getBean("placeOrderService");
	}
	
	public void order() {
		PurchaseOrderRequest orderRequest = new PurchaseOrderRequest();
		orderRequest.setItemId(1);
		orderRequest.setAddress("서울 종로구");
		PurchaseOrderResult orderResult = placeOrderService.order(orderRequest);
		System.out.println("주문 상태 정보");
		System.out.println("아이템 : " + orderResult.getItem().getId());
		System.out.println("상품명 : " + orderResult.getItem().getName());
		System.out.println("가격 : " + orderResult.getItem().getPrice());
	}
	
	public void close() {
		context.close();
	}
	
	public static void main(String[] args) {
		OrderServiceTestOne test = new OrderServiceTestOne();
		test.order();
		test.close();
	}
	
}
