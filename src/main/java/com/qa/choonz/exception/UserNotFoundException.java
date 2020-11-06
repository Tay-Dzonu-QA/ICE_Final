package com.qa.choonz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends PersistenceException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}