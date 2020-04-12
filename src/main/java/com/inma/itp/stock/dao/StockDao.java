package com.inma.itp.stock.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.stock.models.dto.StockDetails;
import com.inma.itp.stock.models.queue.SecsInqRq;
import com.inma.itp.stock.models.queue.SecsInqRs;

@Service
public class StockDao {

	@Autowired
	private MessageTemplateService msgTemplateService;

	public StockDetails getStockDetails(String userId, String symbol) {

		SecsInqRq request = new SecsInqRq(Constants.FUNCTION_SEC, userId);
		request.setProductCode(Constants.PRODUCT_CODE);
		request.setSymbol(symbol);
		SecsInqRs rs = msgTemplateService.sendMessage(request, SecsInqRs.class);

		StockDetails stockDetails = new StockDetails();
		BeanUtils.copyProperties(rs.getSecRec().get(0), stockDetails);
		return stockDetails;
	}

}
