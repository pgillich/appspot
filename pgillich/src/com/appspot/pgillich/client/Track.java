package com.appspot.pgillich.client;

/**
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-3078521-4']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
 */
public class Track {

	private static final String GA_ACCOUNT = "UA-3078521-4";
	
	/**
	 * constructor - nothing to do
	 */
	public Track() {
	}

	/**
	 * track an event
	 * 
	 * @param historyToken
	 */
	public static void track(String Token1, String Token2) {

		String token = "/";
		
		if(Token1 != null) {
			token = token + Token1 + "/";
		} 

		if(Token2 != null) {
			token = token + Token2;
		} 
		
		trackGoogleAnalytics(GA_ACCOUNT, token);

	}

	/**
	 * trigger google analytic native js - included in the build CHECK -
	 * DemoGoogleAnalytics.gwt.xml for -> <script src="../ga.js"/>
	 * 
	 * http://code.google.com/intl/en-US/apis/analytics/docs/gaJS/gaJSApiEventTracking.html
	 * 
	 * @param historyToken
	 */
	public static native void trackGoogleAnalytics(String account, String historyToken) /*-{

		try {
// new GA code
//			_gaq.push(['_trackPageview', historyToken]);
			
			// setup tracking object with account
			var pageTracker = $wnd._gat._getTracker(account); // change account please!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			pageTracker._setRemoteServerMode();

			// turn on anchor observing
			pageTracker._setAllowAnchor(true)

			// send event to google server
			pageTracker._trackPageview(historyToken);

		} catch (err) {

			// debug
			alert('FAILURE: to send in event to google analytics: ' + err);
		}

	}-*/;

}
