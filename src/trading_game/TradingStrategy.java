package trading_game;

import game.DailyInput;
import game.DailyOutput;
import game.TradingManager;
import game.BaseTradingStrategy;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

public class TradingStrategy extends BaseTradingStrategy {
			
	public TradingStrategy (){
		// Initialise any variables needed.
		
	}
	
	@Override
	public DailyOutput makeDailyTrade(DailyInput input) throws InsufficientFundsException, InsufficientSharesException {
		
		// Use the trading manager to make trades based on input.
		
		DailyOutput output;
		
		double delta = input.getClose() - input.getOpen();
		
		if (delta > 0) {
			// share going up
			output = tradingManager.buyMaxNumberOfShares(input);

			
		} else {
			// share going down
			output = tradingManager.sellAllShares(input);

		}
		
		return output;
	}
}
