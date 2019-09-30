package com.traydakalo.dto;

public class ClaimDtoBuilder {
    private long id;
    private String name;
    private String claim;
    private boolean completed;
    private long userId;
    private long managerId;
    private long masterId;
    private long price;
    private String rejection;

    public ClaimDtoBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ClaimDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClaimDtoBuilder setClaim(String claim) {
        this.claim = claim;
        return this;
    }

    public ClaimDtoBuilder setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    public ClaimDtoBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public ClaimDtoBuilder setManagerId(long managerId) {
        this.managerId = managerId;
        return this;
    }

    public ClaimDtoBuilder setMasterId(long masterId) {
        this.masterId = masterId;
        return this;
    }

    public ClaimDtoBuilder setRejection(String rejection){
        this.rejection = rejection;
        return this;
    }

    public ClaimDtoBuilder setPrice(long price){
        this.price = price;
        return this;
    }
    public ClaimDto createClaimDto() {
        return new ClaimDto(id, name, claim, completed,
                price, userId, managerId, masterId, rejection);
    }
}