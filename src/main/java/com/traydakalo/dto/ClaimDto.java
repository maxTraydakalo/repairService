package com.traydakalo.dto;

public class ClaimDto {
    private long id;
    private String name;
    private String claim;
    private boolean completed;
    private long userId;
    private long managerId;
    private long masterId;
    private long price;
    private String rejection;

    public ClaimDto() {
    }

    public ClaimDto(long id, String name, String claim, boolean completed, long price,
                    long userId, long managerId, long masterId, String rejection) {
        this.id = id;
        this.name = name;
        this.claim = claim;
        this.completed = completed;
        this.userId = userId;
        this.managerId = managerId;
        this.masterId = masterId;
        this.price = price;
        this.rejection = rejection;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return claim;
    }

    public void setRequest(String claim) {
        this.claim = claim;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public String getRejection() {
        return rejection;
    }

    public void setRejection(String rejection) {
        this.rejection = rejection;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}


