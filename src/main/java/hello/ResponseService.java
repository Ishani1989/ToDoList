package hello;

import java.util.List;

public class ResponseService {

	public static ToDoListResponse errorResponse(int code, String msg) {
		ToDoListResponse response = new ToDoListResponse();
		response.setCode(code);
		response.setMessage(msg);
		return response;
	}
	
	public static ToDoListResponse successResponse(int code, String msg, List<ToDoData> toDoList) {
		ToDoListResponse response = new ToDoListResponse();
		response.setCode(code);
		response.setMessage(msg);
		response.setGreetingList(toDoList);
		
		return response;
	}
}
