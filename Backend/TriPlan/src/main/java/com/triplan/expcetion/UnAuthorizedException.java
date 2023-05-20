package com.triplan.expcetion;

public class UnAuthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = -3904768638194153041L;

	public UnAuthorizedException() {
		super("계정 권한이 유효하지 않습니다.\n다시 로그인 해주세요.");
	}

}
