package com.traydakalo.services;

import com.traydakalo.dao.queries.ClaimDao;
import com.traydakalo.dao.queries.ClaimDaoInterface;
import com.traydakalo.dto.ClaimDto;
import com.traydakalo.entity.Claim;

import java.util.List;
import java.util.stream.Collectors;

public class ClaimService {
    private ClaimDaoInterface claimDao;
    private static ClaimService claimService;

    private ClaimService() {
        claimDao = ClaimDao.getClaimDao();
    }

    public static ClaimService getClaimService() {
        if (claimService == null) {
            synchronized (ClaimService.class) {
                if (claimService == null) {
                    claimService = new ClaimService();
                }
            }
        }
        return claimService;
    }

    private ClaimDto mapToClaimDto(Claim claim) {
        return new ClaimDto.Builder()
                .id(claim.getId())
                .name(claim.getName())
                .claim(claim.getClaim())
                .completed(claim.isCompleted())
                .userAccountId(claim.getUserAccountId())
                .managerAccountId(claim.getManagerAccountId())
                .masterAccountId(claim.getMasterAccountId())
                .build();
    }

    public List<ClaimDto> getAllClaims() {
        return claimDao.list().stream()
                .map(this::mapToClaimDto)
                .collect(Collectors.toList());
    }

    public void saveClaim(ClaimDto claimDto){
        claimDao.saveClaim(new Claim(claimDto));
    }




}
