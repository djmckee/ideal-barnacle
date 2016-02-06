package trading_game;

import game.BaseTradingStrategy;
import game.BaseStrategyFactory;

public class TradingStrategyFactory extends BaseStrategyFactory {

	@Override
	public BaseTradingStrategy createStrategy() {
		// Creates an instance of your TradingStrategy for each company.
		return new TradingStrategy();
	}

}
