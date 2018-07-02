package com.net.impl;

import com.net.IActivationFunction;
import com.net.consts.Bipolar;
import com.net.utils.BipolarHelper;

public class BipolarActivationFunction implements IActivationFunction<Bipolar> {

	@Override
	public Bipolar Calculate(double signal) {

		double rval = 0;
		
		if(signal >= 0.)
			rval = 1.;
		else
			rval = -1.;
		
		return BipolarHelper.DoubleToBipolar(rval);
	}

	
}
