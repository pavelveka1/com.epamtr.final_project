package by.epamtr.airline.jsptag;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

public class ErrorTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5632579156390919340L;
	private static final Logger logger = Logger.getLogger(ErrorTag.class);
	private static final String ERROR_TAG="Error in ErrorTag!";
	private static final String SERVER_ERROR="500";
	private static final String RES_NOT_FOUND="404";
	private static final String ACCESS_FORBIDDEN="403";
	private String errorCode;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
		if (errorCode.equalsIgnoreCase(SERVER_ERROR)) {
				out.println("<img src=\"https://doofinder-web.s3.amazonaws.com/main-files/uploads/2019/09/error-500-doofinder.jpg\" alt=\"Image\"//>");
		} else if (errorCode.equalsIgnoreCase(RES_NOT_FOUND)) {
			out.println("<img src=\"https://image.freepik.com/free-vector/glitch-error-404-page_23-2148075111.jpg\" alt=\"Image\"//>");
		} else if (errorCode.equalsIgnoreCase(ACCESS_FORBIDDEN)) {
			out.println("<img src=\"https://www.presstigers.com/wp-content/uploads/2016/06/3-Ways-to-Fix-the-403-Forbidden-Error-on-your-WordPress-Site-presstigers.jpg\" alt=\"Image\"//>");
		} else {
			out.println("<img src=\"https://miro.medium.com/max/760/1*PgU4r94kvlpOyMzHsH_5BQ.jpeg\" alt=\"Image\"//>");
		}
		}catch(IOException e) {
			logger.error(ERROR_TAG, e);
		}

		return SKIP_BODY;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
