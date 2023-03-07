package view;

import domain.BlackjackGame;
import domain.Result;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INITIAL_DISTRIBUTE_MESSAGE = "%s와 %s에게 2장을 나누었습니다." + System.lineSeparator();
    private static final String DEALER_DISTRIBUTE_MESSAGE = "%s 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator();
    private static final String DEALER_CARDS_RESULT_MESSAGE = "%s 카드: %s - 결과: %d" + System.lineSeparator();
    private static final String PLAYER_CARDS_RESULT_MESSAGE = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String DEALER_RESULT_MESSAGE = "%s: %d승 %d무 %d패" + System.lineSeparator();
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s" + System.lineSeparator();
    private static final String SPLIT_DELIMITER = ", ";
    private static final String FINAL_WIN_LOSE_RATIO_MESSAGE = System.lineSeparator() + "## 최종 승패";

    private String printCardsForm(Player player) {
        return player.getPlayerCards().stream()
                .map(e -> e.getCardName() + e.getCardPattern())
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    public void printInitialCards(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(INITIAL_DISTRIBUTE_MESSAGE, dealer.getName(), String.join(SPLIT_DELIMITER, players.getPlayersName()));
        System.out.println(dealer.getName() + ": " + printCardsForm(dealer).split(SPLIT_DELIMITER)[0]);
        for (Player player : players.getPlayersWithOutDealer()) {
            System.out.println(player.getName() + "카드: " + printCardsForm(player));
        }
    }

    public void printPlayerCardsInfo(Player player) {
        System.out.println(player.getName() + "카드: " + printCardsForm(player));
    }

    public void printDistributeDealer(Dealer dealer) {
        System.out.println();
        System.out.printf(DEALER_DISTRIBUTE_MESSAGE, dealer.getName());
    }

    public void printCardsResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.printf(DEALER_CARDS_RESULT_MESSAGE, dealer.getName(), printCardsForm(dealer),
                dealer.getCardsSum());
        for (Player player : players.getPlayersWithOutDealer()) {
            System.out.printf(PLAYER_CARDS_RESULT_MESSAGE,
                    player.getName(), printCardsForm(player), player.getCardsSum());
        }
    }

    public void printWinnerResult(Players players, BlackjackGame game) {
        System.out.println(FINAL_WIN_LOSE_RATIO_MESSAGE);
        List<Result> dealerResults = game.getDealerResult(players);
        System.out.printf(DEALER_RESULT_MESSAGE, players.findDealer().getName(),
                dealerResults.stream().filter(s -> s == Result.WIN).count(),
                dealerResults.stream().filter(s -> s == Result.DRAW).count(),
                dealerResults.stream().filter(s -> s == Result.LOSE).count());
        for (Player player : players.getPlayersWithOutDealer()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, player.getName(), game.getPlayersResult(player).getResult());
        }
    }
}
