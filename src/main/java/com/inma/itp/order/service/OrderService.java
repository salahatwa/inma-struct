package com.inma.itp.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.inma.itp.config.models.queues.DtRange;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.order.dao.OrderDao;
import com.inma.itp.order.models.dto.CommissionRequest;
import com.inma.itp.order.models.dto.CommissionResponse;
import com.inma.itp.order.models.dto.OrderInquiry;
import com.inma.itp.order.models.dto.OrderRequest;
import com.inma.itp.order.models.queue.ETradeOrdMngRq;
import com.inma.itp.order.models.queue.ETradeOrdsInqRq;
import com.inma.itp.order.models.queue.ETradeOrdsInqRs.ETradeOrdDtls;
import com.inma.itp.order.models.queue.ETradePreOrdDtlsInqRq;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	/**
	 * Get all commission details
	 * 
	 * @param currentUser
	 * @param commissionRequest
	 * @return Commission details
	 */
	public CommissionResponse getCommissionDetails(String userId, CommissionRequest commReq) {

		ETradePreOrdDtlsInqRq request = new ETradePreOrdDtlsInqRq();
		request.setPortfolioNum(commReq.getPortfolioNum());
		request.setSymbol(commReq.getStockSymbol());
		request.setOrdSide(commReq.getOrderSide());
		request.setOrdType(String.valueOf(commReq.getOrderType()));
		request.setOrdQty(String.valueOf(commReq.getOrderQty()));
		request.setUnitPrice(String.valueOf(commReq.getUnitPrice()));

		CommissionResponse res = orderDao.getCommissionDetails(userId, request);

		return res;
	}

	public String executeOrder(String userId, OrderRequest ordReq) {
		ETradeOrdMngRq request = new ETradeOrdMngRq();
		request.setProduct(Constants.PRODUCT);
		request.setMinFillQty(ordReq.getMinFillQty() + "");
		request.setTifType(ordReq.getTifType() + "");
		request.setExpDt(ordReq.getExpDt());
		request.setPortfolioNum(ordReq.getWalletNumber());
		request.setSymbol(ordReq.getStockSymbol());
		request.setOrdSide(ordReq.getOrderSide());
		request.setOrdType(String.valueOf(ordReq.getOrderType()));
		request.setOrdQty(String.valueOf(ordReq.getOrderQty()));
		request.getCurAmt().setAmt(String.valueOf(ordReq.getUnitPrice()));

		String rs = orderDao.executeOrder(userId, request);

		return rs;
	}

	public List<com.inma.itp.order.models.queue.ETradeOrdsInqRs.ETradeOrdDtls> getOrderStatus(String userId,
			String ordRefNum) {
		ETradeOrdsInqRq request = new ETradeOrdsInqRq();
		request.setProduct(Constants.PRODUCT);
		request.setOwnOrder("Y");
		DtRange date = new DtRange();
		date.setEndDt(Constants.DATE_DEFAULT);
		date.setStartDt(Constants.DATE_DEFAULT);
		request.setDtRange(date);
		request.setOmsRefNum(ordRefNum);

		List<ETradeOrdDtls> rs = orderDao.getOrderStatus(userId, request);

		return rs;
	}

	@PostMapping("/inquiry")
	public List<com.inma.itp.order.models.queue.ETradeOrdsInqRs.ETradeOrdDtls> inquiryOrders(String userId,
			OrderInquiry inqReq) {

		ETradeOrdsInqRq request = new ETradeOrdsInqRq();
		request.setPortfolioNum(inqReq.getWalletNumber());
		request.setOrdStatus(inqReq.getOrdStatus());
		request.setProduct(Constants.PRODUCT);
		request.setOrdSide(inqReq.getOrderSide());
		request.setSymbol(inqReq.getStockSymbol());
		request.setDtRange(inqReq.getDtRange());
		request.setOmsRefNum(inqReq.getOrdRefNum());

		List<ETradeOrdDtls> rs = orderDao.inquiryOrders(userId, request);

		return rs;
	}
}
