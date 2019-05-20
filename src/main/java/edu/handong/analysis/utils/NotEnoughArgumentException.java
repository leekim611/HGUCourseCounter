package edu.handong.analysis.utils;

public class NotEnoughArgumentException extends Exception{
	// Yet..

	public NotEnoughArgumentException() {
		super("The file path does not exist. Please check your CLI argument!");
	}
	public NotEnoughArgumentException(String message) {
		super(message);
	}
}