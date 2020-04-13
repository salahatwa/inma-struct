package com.inma.itp.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.config.utils.Constants;
import com.inma.itp.portfolio.dao.PortfolioDao;
import com.inma.itp.portfolio.model.bussinessObject.ETradeCustPortfoliosInqRq;
import com.inma.itp.portfolio.model.bussinessObject.ETradeCustPortfoliosInqRs.PortfolioDtls;

@Service
public class PortfolioService {
	@Autowired
	private PortfolioDao portfolioDao;

	public PortfolioDtls getPortfolioDetailsByStockAndPortfolioNumber(String userId, String stockSymbol,
			String portfolioNumber) {

		ETradeCustPortfoliosInqRq request = new ETradeCustPortfoliosInqRq();
		request.setPortfolioNum(portfolioNumber);
		request.setPortfolioType(Constants.PORTFOLIO_TYPE);
		request.setSymbol(stockSymbol);
		PortfolioDtls rs = portfolioDao.getPortfolioDetailsByStockAndPortfolioNumber(request, userId);

		return rs;
	}

	/**
	 * Get all wallets details for given POI Number
	 * 
	 * @param currentUser
	 * @param poiNumber
	 * @return List of wallets details
	 */
	public List<PortfolioDtls> getAllPortfolioDetailsForPoiNumber(String userId, String poiNumber) {

		ETradeCustPortfoliosInqRq request = new ETradeCustPortfoliosInqRq();
		List<PortfolioDtls> rs = portfolioDao.getAllPortfolioDetailsForPoiNumber(request, userId, poiNumber);

		return rs;
	}

}
