package com.appspot.pgillich.sandbox.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;

public interface HtmlResources extends ClientBundle {

	@Source("com/appspot/pgillich/sandbox/resource/html/PeopleMyself.html")
	ExternalTextResource htmlPeopleMyself();

	@Source("com/appspot/pgillich/sandbox/resource/html/PeopleCv.html")
	ExternalTextResource htmlPeopleCv();

	@Source("com/appspot/pgillich/sandbox/resource/html/PeopleFriends.html")
	ExternalTextResource htmlPeopleFriends();

	@Source("com/appspot/pgillich/sandbox/resource/html/PeopleBlogs.html")
	ExternalTextResource htmlPeopleBlogs();
	
	@Source("com/appspot/pgillich/sandbox/resource/html/PeopleBooks.html")
	ExternalTextResource htmlPeopleBooks();
	
	@Source("com/appspot/pgillich/sandbox/resource/html/EngineeringSimulation.html")
	ExternalTextResource htmlEngineeringSimulation();	
	
	@Source("com/appspot/pgillich/sandbox/resource/html/EngineeringModeling.html")
	ExternalTextResource htmlEngineeringModeling();	
	
	@Source("com/appspot/pgillich/sandbox/resource/html/EngineeringSystems.html")
	ExternalTextResource htmlEngineeringSystems();	
	
	@Source("com/appspot/pgillich/sandbox/resource/html/EngineeringPaperwork.html")
	ExternalTextResource htmlEngineeringPaperwork();	
}
