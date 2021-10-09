package com.todo.controllers;

import com.google.common.base.Strings;
import com.todo.entities.Todo;
import com.todo.models.TodoModel;
import com.todo.services.TodoService;
import com.todo.utils.HttpResponseStatus;
import com.todo.utils.ResponseModel;
import com.todo.utils.ResponseStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos-web/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todos/events", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> createEvent(@RequestBody TodoModel todoModel) {
        ResponseModel rs;
        if (todoModel.getTitle() == null && todoModel.getTitle().equals("")) {
            rs = ResponseStatus.create(HttpResponseStatus.TITLE_IS_MISSING,
                    null,
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
        } else {
            TodoModel todoResponse = todoService.createEvent(todoModel);
            if (todoResponse != null) {
                rs = ResponseStatus.create(HttpResponseStatus.EVENT_CREATED,
                        todoResponse,
                        HttpStatus.OK,
                        HttpStatus.OK.value());
                return new ResponseEntity<>(rs, HttpStatus.OK);
            } else {
                rs = ResponseStatus.create(HttpResponseStatus.SOMETHING_WENS_WRONG,
                        todoResponse,
                        HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
            }
        }
    }
    
    @RequestMapping(value = "/todos/events/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseModel> updateEvent(@PathVariable(value = "id")Long id, @RequestBody TodoModel todoModel) {
        ResponseModel rs;
        if (id == null && Strings.isNullOrEmpty(todoModel.getTitle())) {
            rs = ResponseStatus.create(HttpResponseStatus.PARAM_IS_MISSING,
                    null,
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value());
        } else {
            todoModel.setId(id);
            TodoModel todoResponse = todoService.createEvent(todoModel);
            if (todoResponse != null) {
                rs = ResponseStatus.create(HttpResponseStatus.EVENT_UPDATED,
                        todoResponse,
                        HttpStatus.OK,
                        HttpStatus.OK.value());
            } else {
                rs = ResponseStatus.create(HttpResponseStatus.SOMETHING_WENS_WRONG,
                        null,
                        HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.value());
            }
        }
        return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/todos/events", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseModel> deleteEvents(@RequestParam(value = "ids")String id) {
        ResponseModel rs = null;
        if (Strings.isNullOrEmpty(id)) {
            rs = ResponseStatus.create(HttpResponseStatus.PARAM_IS_MISSING,
                    null,
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
        List<TodoModel> data = todoService.deleteEvents(id);
        if (!data.isEmpty() && data.size() > 0) {
            rs = ResponseStatus.create(HttpResponseStatus.DELETE_SELECTED_EVENT,
                data,
                HttpStatus.OK,
                HttpStatus.OK.value());
        } 
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/events/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> getEventById(@PathVariable(value = "id") Long eventId) {
        ResponseModel rs;
        if (eventId == null) {
            rs = ResponseStatus.create(HttpResponseStatus.PARAM_IS_MISSING,
                    null,
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value());
        } else {
            TodoModel todoModel = todoService.getById(eventId);
            if (todoModel != null) {
                rs = ResponseStatus.create(HttpResponseStatus.FOUND_EVENT_SUCCESS,
                        todoService.getById(eventId),
                        HttpStatus.OK,
                        HttpStatus.OK.value());
            } else {
                rs = ResponseStatus.create(HttpResponseStatus.EVENT_NOT_FOUND,
                        null,
                        HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.value());
            }
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @RequestMapping(value = "todos/events", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> searchEvents(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "pageId") int pageId,@RequestParam(value = "limit") int limit) {
        ResponseModel rs = null;
        Page<Todo> data = todoService.searchEvents(keyword, pageId,limit);
        if (data != null) {
            rs = ResponseStatus.create(HttpResponseStatus.FETCH_ALL_EVENTS,
                data,
                HttpStatus.OK,
                HttpStatus.OK.value());
        } 
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

}
