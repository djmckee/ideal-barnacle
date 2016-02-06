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
		int sold;//0 if nothing 1 if sold 2 if bought
		
		double delta = input.getClose() - input.getOpen();
		
		if (delta > 0) {
			// share going up
			output = tradingManager.buyMaxNumberOfShares(input);
			//testing
			sold = 2;
			
		}
		else if(delta < 0){
			// share going down
			output = tradingManager.sellAllShares(input);
			//testing
			sold = 1;
		}
		//just for testing
		else{
			output = tradingManager.doNothing(input);
			sold = 0;
		}
		//do nothing if the shares haven't changed
		
		//test stuff
		System.out.print("DAY " + input.getDay() + ": opening: " + input.getOpen() + ", close: " + input.getClose()
				+ ", high: " + input.getHigh() + ", low: " + input.getLow());
		switch(sold){
		case 2:
			System.out.println("   BOUGHT");
			break;
		case 1:
			System.out.println("   SOLD");
			break;
		case 0:
			System.out.println();
			break;
		}
		
		return output;
	}
}
