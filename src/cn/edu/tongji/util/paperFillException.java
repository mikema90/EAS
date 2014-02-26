package cn.edu.tongji.util;

@SuppressWarnings("serial")
public class paperFillException extends Exception {

	private String errorMsg;

	public paperFillException() {
		// TODO Auto-generated constructor stub
	}

	public paperFillException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public paperFillException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public paperFillException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public paperFillException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
