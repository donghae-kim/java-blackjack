package controller;

import domain.*;
import strategy.ShuffleCardsStrategy;
import view.InputView;
import view.OutputView;

public class Controller {
    private static final String Y_COMMAND = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Players players = setPlayers();
        BlackjackGame game = new BlackjackGame(dealer, players,
                new CardDeck(new CardGenerator().generate(new ShuffleCardsStrategy())));

        distributeInitialCard(dealer, players, game);
        selectAdditionalCard(players, game);
        addWhenUnderStandard(dealer, game);
        outputView.printCardsResult(dealer, players);
        outputView.printWinnerResult(game.getDealerResult(), game.getPlayersResult());
    }

    private Players setPlayers(){
        try {
            return new Players(inputView.readPlayerNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPlayers();
        }
    }

    private void distributeInitialCard(Dealer dealer, Players players, BlackjackGame game) {
        game.distributeInitialCard();
        outputView.printInitialCards(dealer, players);
    }

    private void selectAdditionalCard(Players players, BlackjackGame game) {
        for (Player player : players.getPlayers()) {
            selectByPlayer(game, player);
        }
    }

    private void selectByPlayer(BlackjackGame game, Player player) {
        String command;
        do {
            command = inputView.readCommand(player.getName().getName());
            distributeByCommand(game, player, command);
            outputView.printPlayerCardsInfo(player);
        } while (!player.isOverBlackJack() && command.equals(Y_COMMAND));
    }

    private void distributeByCommand(BlackjackGame game, Player player, String command) {
        if (command.equals(Y_COMMAND)) {
            game.distributePlayer(player);
        }
    }

    private void addWhenUnderStandard(Dealer dealer, BlackjackGame game) {
        while (!dealer.isOverStandard()) {
            game.distributeDealer();
            outputView.printDistributeDealer(dealer);
        }
    }

}
