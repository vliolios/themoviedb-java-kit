/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vliolios.tmdb;

import java.util.List;

/**
 *
 * @author vliolios
 */
public class Response {

    private Integer page;
    private List<Result> results;
    private Integer totalResults;
    private Integer totalPages;

    private String statusMessage;
    private Integer statusCode;
    private Boolean success;

    public Integer getPage() {
    	return page;
    }

    public void setPage(Integer page) {
    	this.page = page;
    }

    public List<Result> getResults() {
    	return results;
    }

    public void setResults(List<Result> results) {
    	this.results = results;
    }

    public Integer getTotalResults() {
    	return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
    	this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
    	return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
    	this.totalPages = totalPages;
    }

    public String getStatusMessage() {
    	return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
    	this.statusMessage = statusMessage;
    }

    public Integer getStatusCode() {
    	return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
    	this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
    	return success;
    }

    public void setSuccess(Boolean success) {
    	this.success = success;
    }

    @Override
    public String toString() {
    	return "Response{" + "\npage=" + page + ", \nresults=" + results + ", \ntotalResults=" + totalResults + ", \ntotalPages=" + totalPages + ", \nstatusMessage=" + statusMessage + ", \nstatusCode=" + statusCode + ", \nsuccess=" + success + '}';
    }

}
