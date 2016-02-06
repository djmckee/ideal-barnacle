package trading_game;

import game.DailyInput;
import game.DailyOutput;
import game.TradingManager;
import game.BaseTradingStrategy;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

enum TradeStatus {
	BOUGHT,
	SOLD
}

public class TradingStrategy extends BaseTradingStrategy {

	public TradingStrategy (){
		// Initialise any variables needed.

	}

	@Override
	public DailyOutput makeDailyTrade(DailyInput input) throws InsufficientFundsException, InsufficientSharesException {

		// Use the trading manager to make trades based on input.

		DailyOutput output;
		TradeStatus status;

		double delta = input.getClose() - input.getOpen();

		if (delta < -1) {
			// share going down
			output = tradingManager.sellAllShares(input);
			//testing
			status = TradeStatus.SOLD;
		} else {
			// share going up
			output = tradingManager.buyMaxNumberOfShares(input);
			//testing
			status = TradeStatus.BOUGHT;

		}


		//test stuff
		System.out.print("DAY " + input.getDay() + ": opening: " + input.getOpen() + ", close: " + input.getClose()
		+ ", high: " + input.getHigh() + ", low: " + input.getLow());
		switch(status){
		case BOUGHT:
			System.out.println("   BOUGHT");
			break;
		case SOLD:
			System.out.println("   SOLD");
			break;

		}

		return output;
	}
}
