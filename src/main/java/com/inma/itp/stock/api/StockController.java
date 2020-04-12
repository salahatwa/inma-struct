package com.inma.itp.stock.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inma.itp.config.annotations.CurrentUser;
import com.inma.itp.config.secuirty.UserPrincipal;
import com.inma.itp.stock.dao.StockDao;
import com.inma.itp.stock.service.StockService;

/**
 * Api to return stock details by stock symbol
 * 
 * @author ssatwa
 *
 */
@RestController
@RequestMapping("${api.context.path}/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/{symbol}/details")
	public ResponseEntity<?> getStockDetails(@CurrentUser UserPrincipal currentUser,
			@PathVariable("symbol") String symbol) {
		return ResponseEntity.ok(stockService.getStockDetails(currentUser.getId(), symbol));
	}
}
