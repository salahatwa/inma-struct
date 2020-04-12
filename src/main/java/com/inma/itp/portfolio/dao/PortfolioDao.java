package com.inma.itp.portfolio.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.portfolio.models.queue.ETradeCustPortfoliosInqRq;
import com.inma.itp.portfolio.models.queue.ETradeCustPortfoliosInqRs;
import com.inma.itp.portfolio.models.queue.ETradeCustPortfoliosInqRs.PortfolioDtls;

@Service
public class PortfolioDao {

	@Autowired
	private MessageTemplateService msgTemplateService;

	/**
	 * Get all wallet details by stock number and wallet number
	 * 
	 * @param currentUser
	 * @param stockSymbol
	 * @param walletNumber
	 * @return wallet details
	 */
	public PortfolioDtls getPortfolioDetailsByStockAndPortfolioNumber(String userId, String stockSymbol,
			String portfolioNumber) {

		ETradeCustPortfoliosInqRq request = new ETradeCustPortfoliosInqRq(Constants.FUNCTION_PORTFOLIO_INQ, userId, null);
		request.setPortfolioNum(portfolioNumber);
		request.setPortfolioType(Constants.PORTFOLIO_TYPE);
		request.setSymbol(stockSymbol);
		ETradeCustPortfoliosInqRs rs = msgTemplateService.sendMessage(request, ETradeCustPortfoliosInqRs.class);

		return rs.getPortfolioDtls().get(0);
	}

	/**
	 * Get all wallets details for given POI Number
	 * 
	 * @param currentUser
	 * @param poiNumber
	 * @return List of wallets details
	 */
	public List<PortfolioDtls> getAllPortfolioDetailsForPoiNumber(String userId, String poiNumber) {

		ETradeCustPortfoliosInqRq request = new ETradeCustPortfoliosInqRq(Constants.FUNCTION_PORTFOLIO_POI_INQ, userId, poiNumber);
		ETradeCustPortfoliosInqRs rs = msgTemplateService.sendMessage(request, ETradeCustPortfoliosInqRs.class);

		return rs.getPortfolioDtls();
	}

	
}
