package com.appspot.pgillich.engineering.motor46;

public class ChartFactoryGoogle implements ChartFactory {

	@Override
	public Chart createChart() {
		return new ChartGoogle();
	}

}
