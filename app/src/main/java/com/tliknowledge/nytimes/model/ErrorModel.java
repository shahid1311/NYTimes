package com.tliknowledge.nytimes.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaurav on 12/12/15.
 */
public class ErrorModel implements Serializable {
    private String status;
    private String copyright;
    private List<String> errors;
    private List<Result> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
