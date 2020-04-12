package com.inma.itp.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inma.itp.config.annotations.CurrentUser;
import com.inma.itp.config.secuirty.UserPrincipal;
import com.inma.itp.order.dao.OrderDao;
import com.inma.itp.order.models.dto.CommissionRequest;
import com.inma.itp.order.models.dto.OrderInquiry;
import com.inma.itp.order.models.dto.OrderRequest;

/**
 * Api to for order managing
 * 
 * @author ssatwa
 *
 */
@RestController
@RequestMapping("${api.context.path}/order")
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	/**
	 * Get all commission details
	 * 
	 * @param currentUser
	 * @param commissionRequest
	 * @return Commission details
	 */
	@PostMapping("/commission/details")
	public ResponseEntity<?> getCommissionDetails(@CurrentUser UserPrincipal currentUser,
			@RequestBody CommissionRequest commReq) {

		return ResponseEntity.ok(orderDao.getCommissionDetails(currentUser.getId(), commReq));
	}

	/**
	 * Execute Order
	 * 
	 * @param currentUser
	 * @param orderRequest
	 * @return reference for order
	 */
	@PostMapping("/execute")
	public ResponseEntity<?> executeOrder(@CurrentUser UserPrincipal currentUser, @RequestBody OrderRequest ordReq) {

		return ResponseEntity.ok(orderDao.executeOrder(currentUser.getId(), ordReq));
	}

	/**
	 * Get order Status
	 * 
	 * @param currentUser
	 * @param ordRefNum
	 * @return
	 */
	@GetMapping("/{ordRefNum}/status")
	public ResponseEntity<?> getOrderStatus(@CurrentUser UserPrincipal currentUser,
			@PathVariable("ordRefNum") String ordRefNum) {

		return ResponseEntity.ok(orderDao.getOrderStatus(currentUser.getId(), ordRefNum));
	}

	/**
	 * Inquiry order
	 * @param currentUser
	 * @param inqReq
	 * @return
	 */
	@PostMapping("/inquiry")
	public ResponseEntity<?> inquiryOrders(@CurrentUser UserPrincipal currentUser, @RequestBody OrderInquiry inqReq) {

		return ResponseEntity.ok(orderDao.inquiryOrders(currentUser.getId(), inqReq));
	}

}
