package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import database.H2Database;

@RestController
public class ToDoListController {

	private H2Database dbService = new H2Database();

	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public ToDoListResponse create(@RequestBody ToDoData g1) {
		String msg = this.dbService.add(g1);

		List<ToDoData> toDoList = new ArrayList<ToDoData>();
		toDoList.add(g1);
		
		return msg==null ? ResponseService.successResponse(0, "success ", toDoList):
			ResponseService.errorResponse(10, msg);
	}
	
	@RequestMapping(value="/todo", method = RequestMethod.GET)
	public ToDoListResponse getAll() {
		List<ToDoData> greetingList = this.dbService.getAll();
		return ResponseService.successResponse(0, "success ", greetingList);
	}

	@RequestMapping(value="/todo/{id}", method = RequestMethod.GET)
	public ToDoListResponse getById(@PathVariable String id) {
		
		int idParam = 0;
		try {
			if(id!=null){
				idParam = Integer.parseInt(id);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseService.errorResponse(10, "Queryparam Id is blank or non integer. It's value - " + id);
		}

		if (idParam == 0) {
			return ResponseService.errorResponse(11,"idParam is 0 which is not allowed");
		} else {
			List<ToDoData> greetingList = this.dbService.getById(idParam);
			return greetingList.isEmpty() ? ResponseService.errorResponse(12,"No element found"):
			 ResponseService.successResponse(0, "success ", greetingList);
		}
	}

	@RequestMapping(value="/todo/{id}", method = RequestMethod.DELETE)
	public ToDoListResponse deleteSelected(@PathVariable String id) {
		
		int idParam = 0;
		try {
			if(id!=null){
				idParam = Integer.parseInt(id);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseService.errorResponse(30, "Queryparam Id is blank or non integer. It's value - " + id);
		}

		if (idParam == 0) {
			return ResponseService.errorResponse(31,"idParam is 0 which is not allowed");
		}else{
			Boolean msg = this.dbService.delete(idParam);
			
			return (msg == null) ? 
					ResponseService.successResponse(32, "Object deleted. Id Reference - " + id, null) 
					: ResponseService.errorResponse(34, "errorMsg");
		}
	}

	@RequestMapping(value = "/todo", method = RequestMethod.PUT)
	public ToDoListResponse update(@RequestBody ToDoData g1) {
		Boolean msg = this.dbService.update(g1);
		return (msg == null) ? 
				ResponseService.successResponse(10, "Object updated. Id Reference - " + g1.getId(), null) 
				: ResponseService.errorResponse(10, "errorMsg");
	}
}
