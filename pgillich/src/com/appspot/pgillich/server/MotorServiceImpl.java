package com.appspot.pgillich.server;

import java.util.Map;

import com.appspot.pgillich.client.MotorService;
import com.appspot.pgillich.engineering.motor46.ServerRunner;
import com.appspot.pgillich.shared.MotorResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MotorServiceImpl extends RemoteServiceServlet implements MotorService {

	private static final long serialVersionUID = 1L;

	@Override
	public MotorResult motorServer(Map<String, String> values) throws IllegalArgumentException {

		ServerRunner gwtRunner = new ServerRunner();
		MotorResult result = gwtRunner.start(values);
		
		return result;
	}

}
