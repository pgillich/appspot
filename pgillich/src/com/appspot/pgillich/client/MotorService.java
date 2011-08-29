package com.appspot.pgillich.client;

import java.util.Map;

import com.appspot.pgillich.shared.MotorResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("motor")
public interface MotorService extends RemoteService {
	MotorResult motorServer(Map<String, String> values) throws IllegalArgumentException;

}
