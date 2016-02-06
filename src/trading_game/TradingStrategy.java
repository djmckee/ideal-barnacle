package trading_game;

import game.DailyInput;
import game.DailyOutput;
import game.TradingManager;
import game.BaseTradingStrategy;

import java.util.ArrayList;

import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

enum TradeStatus {
	BOUGHT,
	SOLD,
	HELD
}

public class TradingStrategy extends BaseTradingStrategy {

	private final ArrayList<Double> closePrices = new ArrayList<Double>();
	
	
	// DEBUG turns on trade logging.
	private final static boolean DEBUG = true;

	public TradingStrategy (){
		// Initialise any variables needed.

	}

	@Override
	public DailyOutput makeDailyTrade(DailyInput input) throws InsufficientFundsException, InsufficientSharesException {
		
		int smallN = 5;
		int bigN = 12;
		final double buy = 0.4;
		final double sell = -8;
		DailyOutput output;
		TradeStatus status;


		double delta = input.getClose() - input.getOpen();
		
		closePrices.add(input.getClose());

		
		if(input.getDay() > bigN){
			//if the share is going down OR the average price in the last smallN days is lower than the last bigN
			if (delta < sell || getNDayAverage(smallN) < getNDayAverage(bigN)) {
				output = tradingManager.sellAllShares(input);
				//testing
				status = TradeStatus.SOLD;

			} else if(delta > buy) {
				// share going up
				output = tradingManager.buyMaxNumberOfShares(input);
				//testing
				status = TradeStatus.BOUGHT;

			}
			else{
				output = tradingManager.doNothing(input);
				status = TradeStatus.HELD;
			}
		}
		else{
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
		}
		


		//test stuff
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
	
	/*
	 * This method will return the average for the last n days
	 */
	public Double getNDayAverage(int n){
		Double output = 0.0;
		int currentDay = closePrices.size();
		if(currentDay < n){
			return null;

		}
		else{ // we can calculate this
			for(int i=currentDay; i > (currentDay - n); i--){
				output = output + closePrices.get(i - 1);
			}
			output = output / n;

		}
		return output;
	}
	
	
}
