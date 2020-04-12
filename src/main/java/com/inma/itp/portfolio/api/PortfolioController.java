package com.inma.itp.portfolio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inma.itp.config.annotations.CurrentUser;
import com.inma.itp.config.secuirty.UserPrincipal;
import com.inma.itp.portfolio.service.PortfolioService;

/**
 * Api to return wallet details
 * 
 * @author ssatwa
 *
 */
@RestController
@RequestMapping("${api.context.path}/portfolio")
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	/**
	 * Get all wallet details by stock number and wallet number
	 * 
	 * @param currentUser
	 * @param stockSymbol
	 * @param walletNumber
	 * @return wallet details
	 */
	@GetMapping("/{stockSymbol}/{portfolioNumber}/details")
	public ResponseEntity<?> getPortfolioDetailsByStockAndPortfolioNumber(@CurrentUser UserPrincipal currentUser,
			@PathVariable("stockSymbol") String stockSymbol, @PathVariable("portfolioNumber") String portfolioNumber) {

		return ResponseEntity.ok(portfolioService.getPortfolioDetailsByStockAndPortfolioNumber(currentUser.getId(),
				stockSymbol, portfolioNumber));
	}

	/**
	 * Get all wallets details for given POI Number
	 * 
	 * @param currentUser
	 * @param poiNumber
	 * @return List of wallets details
	 */
	@GetMapping("/{poiNumber}/details")
	public ResponseEntity<?> getAllPortfolioDetailsForPoiNumber(@CurrentUser UserPrincipal currentUser,
			@PathVariable("poiNumber") String poiNumber) {

		return ResponseEntity.ok(portfolioService.getAllPortfolioDetailsForPoiNumber(currentUser.getId(), poiNumber));
	}

}
