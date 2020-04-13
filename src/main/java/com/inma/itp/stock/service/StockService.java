package com.inma.itp.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.config.utils.Constants;
import com.inma.itp.stock.dao.StockDao;
import com.inma.itp.stock.model.dto.StockDetails;
import com.inma.itp.stock.models.bussinessObject.SecsInqRq;

@Service
public class StockService {

	@Autowired
	private StockDao stockDao;

	public StockDetails getStockDetails(String userId, String symbol) {

		SecsInqRq request = new SecsInqRq();
		request.setProductCode(Constants.PRODUCT_CODE);
		request.setSymbol(symbol);

		StockDetails stockDetails = stockDao.getStockDetails(request, userId);
		return stockDetails;
	}

}
