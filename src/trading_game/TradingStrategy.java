package trading_game;

import game.DailyInput;
import game.DailyOutput;
import game.TradingManager;
import game.BaseTradingStrategy;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

enum TradeStatus {
	BOUGHT,
	SOLD,
	HELD
}

public class TradingStrategy extends BaseTradingStrategy {
	
	// DEBUG turns on trade logging.
	private final static boolean DEBUG = true;

	public TradingStrategy (){
		// Initialise any variables needed.

	}

	@Override
	public DailyOutput makeDailyTrade(DailyInput input) throws InsufficientFundsException, InsufficientSharesException {

		// Use the trading manager to make trades based on input.
		final double buy = -0.9;
		final double sell = -1.1;
		DailyOutput output;
		TradeStatus status;


		double delta = input.getClose() - input.getOpen();

		if (delta < sell) {
			// share going down
			output = tradingManager.sellAllShares(input);
			//testing
			status = TradeStatus.SOLD;

		} else if (delta > buy) {
			// share going up
			output = tradingManager.buyMaxNumberOfShares(input);
			//testing
			status = TradeStatus.BOUGHT;

		}
		else{
			output = tradingManager.doNothing(input);
			//testing
			status = TradeStatus.HELD;
		}


		if (DEBUG) {
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
			case HELD:
				System.out.println("   HELD");
				break;
			}
		}

		return output;
	}
}
