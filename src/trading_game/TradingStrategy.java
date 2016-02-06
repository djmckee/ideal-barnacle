package trading_game;

import game.DailyInput;
import game.DailyOutput;
import game.TradingManager;
import game.BaseTradingStrategy;

import java.util.ArrayList;

import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

public class TradingStrategy extends BaseTradingStrategy {
	
	public ArrayList<Double> closeList;
	
	public TradingStrategy (){
		// Initialise any variables needed.
		// make an arrayList that has n elements, storing the close price of each day
		closeList = new ArrayList<Double>();
	}
	
	@Override
	public DailyOutput makeDailyTrade(DailyInput input) throws InsufficientFundsException, InsufficientSharesException {
		
		// Use the trading manager to make trades based on input.
		DailyOutput output;
		
		//store the current close price in the right element
		closeList.add(input.getClose());
		
		if(input.getDay() == 1){ //on the first day, buy all the shares
			output = tradingManager.buyMaxNumberOfShares(input);
		}
		else{ //let's start making choices
			if(closeList.get(input.getDay() - 1) > input.getClose()){ //if the share has fallen
				output = tradingManager.sellAllShares(input);
			}
			else{ //share has risen, get in now
				output = tradingManager.buyMaxNumberOfShares(input);
			}
		}
		
		return output;
	}
}
