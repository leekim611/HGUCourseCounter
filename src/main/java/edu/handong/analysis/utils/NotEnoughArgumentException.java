package edu.handong.analysis.utils;

public class NotEnoughArgumentException extends Exception{

	public NotEnoughArgumentException() {
		super();
	}
	public NotEnoughArgumentException(String message) {
		super(message);
	}
}