package com.agileengine.config.date;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;

@Component
@RequestScope
public class RequestDate {

    private Date date;

    public RequestDate() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }
}
