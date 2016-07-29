package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Order;
import com.pgs.soft.dto.OrderDTO;
import com.pgs.soft.repository.OrderRepository;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDTO, Integer> {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@Override
	protected CrudRepository<Order, Integer> getCrudRepository() {
		return orderRepository;
	}

	@Override
	protected Order mapDtoToEntity(OrderDTO objectDTO) {
		Order order = new Order();
		order.setName(objectDTO.getName());
		order.setSurname(objectDTO.getSurname());
		order.setCity(objectDTO.getCity());
		order.setStreet(objectDTO.getStreet());
		order.setHomeNumber(objectDTO.getHomeNumber());
		order.setPostCode(objectDTO.getPostCode());
		order.setAwardName(objectDTO.getAwardName());
		order.setDescription(objectDTO.getDescription());
		order.setCategory(objectDTO.getDescription());
		order.setPointsPrice(objectDTO.getPointsPrice());
		return order;
	}

	@Override
	protected OrderDTO mapEntityToDto(Order entity) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setName(entity.getName());
		orderDTO.setSurname(entity.getSurname());
		orderDTO.setCity(entity.getCity());
		orderDTO.setStreet(entity.getStreet());
		orderDTO.setHomeNumber(entity.getHomeNumber());
		orderDTO.setPostCode(entity.getPostCode());
		orderDTO.setAwardName(entity.getAwardName());
		orderDTO.setDescription(entity.getDescription());
		orderDTO.setCategory(entity.getCategory());
		orderDTO.setPointsPrice(entity.getPointsPrice());

		return orderDTO;
	}

	// public void updateOrders(OrderDTO orderDTO){
	// saveOrUpdate(orderDTO);
	// emailServiceImpl.sendOrderConfirmationEmail(to, orderDTO);
	//
	// }

}
