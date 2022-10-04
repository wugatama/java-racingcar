package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Racing extends Condition {
	private static final int MAX_OF_RANDOM = 10;
	private static final RandomStrategy random = () -> new Random().nextInt(MAX_OF_RANDOM);

	public Racing() {
		super(new NumberCondition());
	}

	public void playRace() {
		Print.participantsOfRacing();
		String participant = InputView.input();
		Print.howManyMatches();
		String matches = InputView.input();

		Print.execute();

		Views.winOfRacing(result(participant, checkInput(matches)));
	}

	private static Cars result(String participant, String matches) {
		return playMatches(participate(participant), matches);
	}

	private static Cars playMatches(Cars cars, String matches) {
		for (int i = 0; i < Integer.parseInt(matches); i++) {
			playMatch(cars, i);
		}
		return cars;
	}

	private static void playMatch(Cars cars, int index) {
		for (int i = 0; i < cars.getCars().size(); i++) {
			cars.getCars().get(i).movingOfRound(random.makeRandom());
		}
		Views.results(cars, index);
	}

	public static Cars participate(String input) {
		List<Car> cars = new ArrayList<>();
		for (String car : input.split(",")) {
			cars.add(new Car(car));
		}
		return new Cars(cars);
	}

	public String checkInput(String input) {
		if (validInput(input)) {
			throw new RuntimeException();
		}
		return input;
	}
}
