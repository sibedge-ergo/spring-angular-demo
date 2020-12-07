package com.sibedge.ergo.api;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Additional set of advice binders.
 */
@ControllerAdvice
class ApiBinderAdvice {

    /**
     * Trims form values before they are validated.
     *
     * @param binder binding registry
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        var trimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmerEditor);
    }

}
