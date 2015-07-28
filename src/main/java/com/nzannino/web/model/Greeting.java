package com.nzannino.web.model;

/**
 * User: zannino
 * Date: 20/03/2015
 * Time: 11:12
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
