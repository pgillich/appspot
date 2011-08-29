package com.appspot.pgillich.engineering.motor46;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appspot.pgillich.shared.MotorResult;
import com.appspot.pgillich.shared.MotorStep;

public class ServerRunner {

	public MotorResult start(Map<String, String> values) {
		MotorResult result = new MotorResult();
		ArrayList<MotorStep> steps = new ArrayList<MotorStep>();
		result.log = "";
		ByteArrayOutputStream stdoutStream = new ByteArrayOutputStream();
		PrintWriter stdout = new PrintWriter(stdoutStream);
		List<Double> mert = new ArrayList<Double>();
		
		Motor46 motor = new Motor46();
		motor.main(values,steps,mert,stdout);
		
		stdout.close();
		result.log = stdoutStream.toString();
		result.steps = new MotorStep[steps.size()];
		result.steps = steps.toArray(result.steps);
		
		return result;
	}
}
