package com.traydakalo.entity;


import com.traydakalo.dto.ClaimDto;

public class Claim {
    private long id;
    private String name;
    private String claim;
    private boolean completed;
    private long userAccountId;
    private long managerAccountId;
    private long masterAccountId;

    public Claim() {}

    public Claim(ClaimDto claimDto){
        id = claimDto.getId();
        name = claimDto.getName();
        claim = claimDto.getRequest();
        completed = claimDto.isCompleted();
        userAccountId = claimDto.getUserAccountId();
        managerAccountId = claimDto.getManagerAccountId();
        masterAccountId = claimDto.getMasterAccountId();
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

    public long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public long getManagerAccountId() {
        return managerAccountId;
    }

    public void setManagerAccountId(long managerAccountId) {
        this.managerAccountId = managerAccountId;
    }

    public long getMasterAccountId() {
        return masterAccountId;
    }

    public void setMasterAccountId(long masterAccountId) {
        this.masterAccountId = masterAccountId;
    }
}
