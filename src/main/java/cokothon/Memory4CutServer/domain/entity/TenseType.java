package cokothon.Memory4CutServer.domain.entity;

import lombok.Getter;

@Getter
public enum TenseType {

	PAST("과거"),
	PRESENT("현재"),
	FUTURE("미래"),
	UNKNOWN("알수없음");

	private final String type;

	TenseType(String type) {
		this.type = type;
	}
}
