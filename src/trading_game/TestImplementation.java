package trading_game;

import exceptions.GameFailureException;
import game.Game;


public class TestImplementation {
	public static void main(String[] args) throws GameFailureException {
		
		Game game = new Game("Dartboard Monkey", new TradingStrategyFactory());
		game.run();

	}
}
