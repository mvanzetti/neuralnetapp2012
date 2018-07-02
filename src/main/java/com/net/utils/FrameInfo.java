package com.net.utils;

public class FrameInfo {

	private int _width; 
	private int _height;
	
	public FrameInfo(int width, int height) {
	
		_width = width;
		_height = height;
	}
	
	public int GetWidth() {
		return _width;
	}
	
	public int GetHeight() {
		return _height;
	}
	
	public void SetWidth(int width) {
		_width = width;
	}
	
	public void SetHeight(int height) {
		_height = height;
	}
}
