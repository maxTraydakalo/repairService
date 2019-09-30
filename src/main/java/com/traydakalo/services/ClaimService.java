package com.traydakalo.services;

import com.traydakalo.dao.queries.ClaimDao;
import com.traydakalo.dao.queries.ClaimDaoInterface;
import com.traydakalo.dto.ClaimDto;
import com.traydakalo.dto.ClaimDtoBuilder;
import com.traydakalo.dto.UserDto;
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
        return new ClaimDtoBuilder()
                .setId(claim.getId())
                .setName(claim.getName())
                .setClaim(claim.getClaim())
                .setCompleted(claim.isCompleted())
                .setUserId(claim.getUserId())
                .setManagerId(claim.getManagerId())
                .setMasterId(claim.getMasterId())
                .setPrice(claim.getPrice())
                .setRejection(claim.getRejection())
                .createClaimDto();
    }

    public List<ClaimDto> getAllClaims() {
        return claimDao.list().stream()
                .map(this::mapToClaimDto)
                .collect(Collectors.toList());
    }

    public void saveClaim(String name, String claim, UserDto userDto) {
        if (userDto != null && userDto.getId() != 0) {
            claimDao.saveClaim(name, claim, userDto.getId());
        } else {
            claimDao.saveClaim(name, claim);
        }
    }

    public List<ClaimDto> getUnmanagedClaims(int recordsPerPage, int currentPage) {
        return claimDao.findUnmanagedClaims(recordsPerPage, (currentPage-1) * recordsPerPage)
                .stream()
                .map(this::mapToClaimDto)
                .collect(Collectors.toList());
    }

    public Integer getNumberOfUnmanagedClaims(){
        return claimDao.getNumberOfUnmanagedClaims();
    }

    public ClaimDto find(long id){
        return mapToClaimDto(claimDao.find(id));
    }

    public void updateClaimByManager(UserDto user, ClaimDto claimDto,
                                     long price, long masterId, String rejection){
        claimDto.setPrice(price);
        claimDto.setManagerId(user.getId());
        claimDto.setMasterId(masterId);
        claimDto.setRejection(rejection);
        claimDao.updateByManager(new Claim(claimDto));
    }
}
