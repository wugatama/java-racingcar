package racingCar;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private final List<Integer> status = new ArrayList<>();
	private final String name;

	public Car(String name) {
		if (5 < name.length()) {
			throw new RuntimeException();
		}
		this.name = name;
	}

	public List<Integer> getStatus() {
		return this.status;
	}

	public String getName() {
		return this.name;
	}

	public void movingOfRound(int random) {
		this.status.add(random);
	}
}
