package com.inma.itp.order.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.models.queues.DtRange;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.order.models.dto.CommissionRequest;
import com.inma.itp.order.models.dto.CommissionResponse;
import com.inma.itp.order.models.dto.OrderInquiry;
import com.inma.itp.order.models.dto.OrderRequest;
import com.inma.itp.order.models.queue.ETradeOrdMngRq;
import com.inma.itp.order.models.queue.ETradeOrdMngRs;
import com.inma.itp.order.models.queue.ETradeOrdsInqRq;
import com.inma.itp.order.models.queue.ETradeOrdsInqRs;
import com.inma.itp.order.models.queue.ETradePreOrdDtlsInqRq;
import com.inma.itp.order.models.queue.ETradePreOrdDtlsInqRs;

@Service
public class OrderDao {

	@Autowired
	private MessageTemplateService msgTemplateService;

	/**
	 * Get all commission details
	 * 
	 * @param currentUser
	 * @param commissionRequest
	 * @return Commission details
	 */
	public CommissionResponse getCommissionDetails(String userId, CommissionRequest commReq) {

		ETradePreOrdDtlsInqRq request = new ETradePreOrdDtlsInqRq(Constants.FUNCTION_PORTOLIO_COMMISSION, userId);
		request.setPortfolioNum(commReq.getPortfolioNum());
		request.setSymbol(commReq.getStockSymbol());
		request.setOrdSide(commReq.getOrderSide());
		request.setOrdType(String.valueOf(commReq.getOrderType()));
		request.setOrdQty(String.valueOf(commReq.getOrderQty()));
		request.setUnitPrice(String.valueOf(commReq.getUnitPrice()));

		ETradePreOrdDtlsInqRs rs = msgTemplateService.sendMessage(request, ETradePreOrdDtlsInqRs.class);

		CommissionResponse res = new CommissionResponse();

		BeanUtils.copyProperties(rs, res);

		return res;
	}

	public String executeOrder(String userId, OrderRequest ordReq) {
		ETradeOrdMngRq request = new ETradeOrdMngRq(Constants.FUNCTION_ORD_MNG, userId);
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

		ETradeOrdMngRs rs = msgTemplateService.sendMessage(request, ETradeOrdMngRs.class);

		return rs.getSpRefNum();
	}

	public List<com.inma.itp.order.models.queue.ETradeOrdsInqRs.ETradeOrdDtls> getOrderStatus(String userId,
			String ordRefNum) {
		ETradeOrdsInqRq request = new ETradeOrdsInqRq(Constants.FUNCTION_ORD_INQ, userId);
		request.setProduct(Constants.PRODUCT);
		request.setOwnOrder("Y");
		DtRange date = new DtRange();
		date.setEndDt(Constants.DATE_DEFAULT);
		date.setStartDt(Constants.DATE_DEFAULT);
		request.setDtRange(date);
		request.setOmsRefNum(ordRefNum);

		ETradeOrdsInqRs rs = msgTemplateService.sendMessage(request, ETradeOrdsInqRs.class);

		return rs.geteTradeOrdDtls();
	}

	@PostMapping("/inquiry")
	public List<com.inma.itp.order.models.queue.ETradeOrdsInqRs.ETradeOrdDtls> inquiryOrders(String userId,
			OrderInquiry inqReq) {

		ETradeOrdsInqRq request = new ETradeOrdsInqRq(Constants.FUNCTION_ORD_INQ, userId);
		request.setPortfolioNum(inqReq.getWalletNumber());
		request.setOrdStatus(inqReq.getOrdStatus());
		request.setProduct(Constants.PRODUCT);
		request.setOrdSide(inqReq.getOrderSide());
		request.setSymbol(inqReq.getStockSymbol());
		request.setDtRange(inqReq.getDtRange());
		request.setOmsRefNum(inqReq.getOrdRefNum());

		ETradeOrdsInqRs rs = msgTemplateService.sendMessage(request, ETradeOrdsInqRs.class);

		return rs.geteTradeOrdDtls();
	}
}
