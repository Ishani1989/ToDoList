package hello;

import java.util.ArrayList;
import java.util.List;

public class ToDoListResponse {
	private int code;
	private String message;
	private List<ToDoData> greetingList;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ToDoData> getGreetingList() {
		return greetingList;
	}

	public void setGreetingList(List<ToDoData> greetingList) {
		this.greetingList = greetingList;
	}

	@Override
	public String toString() {
		return "GreetingResponse [code=" + code + ", message=" + message
				+ ", greetingList=" + greetingList + "]";
	}
}
