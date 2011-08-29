package com.appspot.pgillich.client;

import java.util.Map;

import com.appspot.pgillich.shared.MotorResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MotorServiceAsync {
	void motorServer(Map<String, String> values, AsyncCallback<MotorResult> callback)
			throws IllegalArgumentException;
}
