package org.gallant.account.controller;

import org.gallant.account.exception.AccountServiceException;
import org.springframework.validation.BindingResult;

/**
 * @author kongyong
 * @date 2019/11/27
 */
public abstract class BaseController {

    private static final String COMMA = ",";

    void processBindingResult(BindingResult errors) {
        if(errors.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            errors.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append(COMMA));
            throw new AccountServiceException(sb.toString());
        }
    }

}
