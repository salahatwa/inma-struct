package com.inma.itp.order.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.order.model.bussinessObject.ETradeOrdMngRq;
import com.inma.itp.order.model.bussinessObject.ETradeOrdMngRs;
import com.inma.itp.order.model.bussinessObject.ETradeOrdsInqRq;
import com.inma.itp.order.model.bussinessObject.ETradeOrdsInqRs;
import com.inma.itp.order.model.bussinessObject.ETradePreOrdDtlsInqRq;
import com.inma.itp.order.model.bussinessObject.ETradePreOrdDtlsInqRs;
import com.inma.itp.order.model.dto.CommissionResponse;

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
	public CommissionResponse getCommissionDetails(String userId, ETradePreOrdDtlsInqRq rq) {

		rq.setFuncId(Constants.FUNCTION_PORTOLIO_COMMISSION);
		rq.setAgentId(userId);

		ETradePreOrdDtlsInqRs rs = msgTemplateService.sendMessage(rq, ETradePreOrdDtlsInqRs.class);

		CommissionResponse res = new CommissionResponse();

		BeanUtils.copyProperties(rs, res);

		return res;
	}

	public String executeOrder(String userId, ETradeOrdMngRq rq) {
		rq.setFuncId(Constants.FUNCTION_ORD_MNG);
		rq.setAgentId(userId);

		ETradeOrdMngRs rs = msgTemplateService.sendMessage(rq, ETradeOrdMngRs.class);

		return rs.getSpRefNum();
	}

	public List<com.inma.itp.order.model.bussinessObject.ETradeOrdsInqRs.ETradeOrdDtls> getOrderStatus(String userId,
			ETradeOrdsInqRq rq) {
		rq.setFuncId(Constants.FUNCTION_ORD_INQ);
		rq.setAgentId(userId);

		ETradeOrdsInqRs rs = msgTemplateService.sendMessage(rq, ETradeOrdsInqRs.class);

		return rs.geteTradeOrdDtls();
	}

	@PostMapping("/inquiry")
	public List<com.inma.itp.order.model.bussinessObject.ETradeOrdsInqRs.ETradeOrdDtls> inquiryOrders(String userId,
			ETradeOrdsInqRq rq) {

		rq.setFuncId(Constants.FUNCTION_ORD_INQ);
		rq.setAgentId(userId);

		ETradeOrdsInqRs rs = msgTemplateService.sendMessage(rq, ETradeOrdsInqRs.class);

		return rs.geteTradeOrdDtls();
	}
}
