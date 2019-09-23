package com.traydakalo.dto;

public class ClaimDto {
    private long id;
    private String name;
    private String claim;
    private boolean completed;
    private long userAccountId;
    private long managerAccountId;
    private long masterAccountId;

    public ClaimDto() {
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

    public static class Builder {
        private ClaimDto claimDto;

        public Builder() {
            claimDto = new ClaimDto();
        }

        public Builder id(long id) {
            claimDto.id = id;
            return this;
        }

        public Builder name(String name) {
            claimDto.name = name;
            return this;
        }

        public Builder claim(String claim) {
            claimDto.claim = claim;
            return this;
        }

        public Builder completed(boolean completed) {
            claimDto.completed = completed;
            return this;
        }

        public Builder userAccountId(long id) {
            claimDto.userAccountId = id;
            return this;
        }

        public Builder managerAccountId(long id) {
            claimDto.managerAccountId = id;
            return this;
        }

        public Builder masterAccountId(long id) {
            claimDto.masterAccountId = id;
            return this;
        }

        public ClaimDto build() {
            return claimDto;
        }
    }
}


