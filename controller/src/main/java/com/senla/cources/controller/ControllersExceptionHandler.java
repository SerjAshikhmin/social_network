package com.senla.cources.controller;

import com.senla.cources.exceptions.groupexceptions.*;
import com.senla.cources.exceptions.messageexceptions.PostMessageException;
import com.senla.cources.exceptions.messageexceptions.RemoveMessageException;
import com.senla.cources.exceptions.messageexceptions.SendMessageException;
import com.senla.cources.exceptions.messageexceptions.ShowDialogException;
import com.senla.cources.exceptions.userexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AddGroupException.class)
    protected ResponseEntity<Exception> handleAddGroupException() {
        return new ResponseEntity<>(new Exception("Error adding a group"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddUserToGroupException.class)
    protected ResponseEntity<Exception> handleAddUserToGroupException() {
        return new ResponseEntity<>(new Exception("Error adding a user to group"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    protected ResponseEntity<Exception> handleGroupNotFoundException() {
        return new ResponseEntity<>(new Exception("Group not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RemoveGroupException.class)
    protected ResponseEntity<Exception> handleRemoveGroupException() {
        return new ResponseEntity<>(new Exception("Error removing a group"), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(RemoveUserFromGroupException.class)
    protected ResponseEntity<Exception> handleRemoveUserFromGroupException() {
        return new ResponseEntity<>(new Exception("Error removing a user from group"), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(PostMessageException.class)
    protected ResponseEntity<Exception> handlePostMessageException() {
        return new ResponseEntity<>(new Exception("Error posting a message"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RemoveMessageException.class)
    protected ResponseEntity<Exception> handleRemoveMessageException() {
        return new ResponseEntity<>(new Exception("Error removing a message"), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(SendMessageException.class)
    protected ResponseEntity<Exception> handleSendMessageException() {
        return new ResponseEntity<>(new Exception("Error sending a message"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShowDialogException.class)
    protected ResponseEntity<Exception> handleShowDialogException() {
        return new ResponseEntity<>(new Exception("Dialog or message not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegisterUserException.class)
    protected ResponseEntity<Exception> handleRegisterUserException() {
        return new ResponseEntity<>(new Exception("Error registering user"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RemoveUserException.class)
    protected ResponseEntity<Exception> handleRemoveUserException() {
        return new ResponseEntity<>(new Exception("Error removing user"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddToFriendsException.class)
    protected ResponseEntity<Exception> handleAddToFriendsException() {
        return new ResponseEntity<>(new Exception("Error adding user to friends"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RemoveFromFriendsException.class)
    protected ResponseEntity<Exception> handleRemoveFromFriendsException() {
        return new ResponseEntity<>(new Exception("Error removing user from friends"), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(UpdateUserException.class)
    protected ResponseEntity<Exception> handleUpdateUserException() {
        return new ResponseEntity<>(new Exception("Error updating user"), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Exception> handleUserNotFoundException() {
        return new ResponseEntity<>(new Exception("User not found"), HttpStatus.NOT_FOUND);
    }
}
