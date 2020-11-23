package by.epamtr.airline.jsptag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ErrorTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5632579156390919340L;
	private Throwable errorType;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StackTraceElement[] arrayStackTrace = errorType.getStackTrace();

		try {
			out.println("<h3>");
			out.println("Error: " + errorType.getMessage());
			out.println("</h3>");
			out.println("<br>");
			for (int i = 0; i < arrayStackTrace.length; i++) {
				out.println(arrayStackTrace[i].toString());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return SKIP_BODY;
	}

	public Throwable getErrorType() {
		return errorType;
	}

	public void setErrorType(Throwable errorType) {
		this.errorType = errorType;
	}

}
