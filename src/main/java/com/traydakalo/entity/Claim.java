package com.traydakalo.entity;


import com.traydakalo.dto.ClaimDto;

public class Claim {
    private long id;
    private String name;
    private String claim;
    private boolean completed;
    private long userId;
    private long managerId;
    private long masterId;
    private long price;
    private String rejection;

    public Claim() {}

    public Claim(ClaimDto claimDto){
        id = claimDto.getId();
        name = claimDto.getName();
        claim = claimDto.getRequest();
        completed = claimDto.isCompleted();
        userId = claimDto.getUserId();
        managerId = claimDto.getManagerId();
        masterId = claimDto.getMasterId();
        rejection = claimDto.getRejection();
        price = claimDto.getPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
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
