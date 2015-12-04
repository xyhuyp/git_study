package com.coco.spring.boot.web;

public enum PositionCaptcha {

	_1("39,42"), _2("115,44"), _3("196,43"), _4("256,40"), _5("48,118"), _6("113,122"), _7("186,113"), _8("263,109");

	private String position;

	PositionCaptcha(String position) {
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}

	public static PositionCaptcha valueOf(int i) {
		PositionCaptcha retVal = null;
		for (PositionCaptcha positionCaptcha : PositionCaptcha.values()) {
			if (i == positionCaptcha.ordinal() + 1) {
				retVal = positionCaptcha;
			}
		}
		return retVal;
	}
	
	public static  String getCaptchaPosition(String baseStr) {
		char[] chars = baseStr.toCharArray();
		StringBuilder captchaIn = new StringBuilder();
		boolean isFirst = true;
		for (char c : chars) {
			if (!isFirst)
				captchaIn.append(",");
			isFirst = false;
			captchaIn.append(PositionCaptcha.valueOf(Integer.valueOf("" + c)).getPosition());
		}
		return captchaIn.toString();
	}
}
