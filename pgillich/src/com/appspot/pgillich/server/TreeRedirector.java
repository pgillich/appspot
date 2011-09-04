package com.appspot.pgillich.server;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appspot.pgillich.shared.SharedConfig;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;

public class TreeRedirector extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		String tree = req.getServletPath();
		String item = req.getPathInfo();
		String debugParameter = req.getParameter(SharedConfig.URL_PARAMETER_GWT_CODESVR);

		if (tree != null && item != null) {
			tree = StringUtil.trimStart(tree, "/");
			item = StringUtil.trimStart(item, "/");
			String redirUrl = "/?"
					+ SharedConfig.URL_PARAMETER_TREE
					+ "="
					+ tree
					+ "&"
					+ SharedConfig.URL_PARAMETER_ITEM
					+ "="
					+ item
					+ "&"
					+ (debugParameter == null ? "" : SharedConfig.URL_PARAMETER_GWT_CODESVR + "="
							+ debugParameter);
			String redirUrlEnc = resp.encodeRedirectURL(redirUrl);
			resp.sendRedirect(redirUrlEnc);
		} else {
			String msg = ResourceBundle.getBundle("javax.servlet.http.LocalStrings").getString(
					"http.method_get_not_supported");
			if (req.getProtocol().endsWith("1.1"))
				resp.sendError(405, msg);
			else
				resp.sendError(400, msg);
		}
	}
}
