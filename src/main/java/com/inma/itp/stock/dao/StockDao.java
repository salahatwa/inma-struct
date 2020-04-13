package com.inma.itp.stock.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.stock.model.dto.StockDetails;
import com.inma.itp.stock.models.bussinessObject.SecsInqRq;
import com.inma.itp.stock.models.bussinessObject.SecsInqRs;

@Service
public class StockDao {

	@Autowired
	private MessageTemplateService msgTemplateService;

	public StockDetails getStockDetails(SecsInqRq rq, String userId) {
		rq.setFuncId(Constants.FUNCTION_SEC);
		rq.setAgentId(userId);
		SecsInqRs rs = msgTemplateService.sendMessage(rq, SecsInqRs.class);

		StockDetails stockDetails = new StockDetails();
		BeanUtils.copyProperties(rs.getSecRec().get(0), stockDetails);
		return stockDetails;
	}

}
