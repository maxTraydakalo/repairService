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
        return claimDao.findUnmanagedClaims(recordsPerPage, (currentPage - 1) * recordsPerPage)
                .stream()
                .map(this::mapToClaimDto)
                .collect(Collectors.toList());
    }

    public Integer getNumberOfUnmanagedClaims() {
        return claimDao.getNumberOfUnmanagedClaims();
    }

    public ClaimDto findClaim(long id) {
        return mapToClaimDto(claimDao.findClaim(id));
    }

    public void updateClaimByManager(UserDto manager, ClaimDto claimDto,
                                     long price, long masterId, String rejection) {
        claimDao.updateByManager(manager.getId(), masterId, price, rejection, claimDto.getId());
    }

    public List<ClaimDto> getClaimsOfMaster(long id, int recordsPerPage, int currentPage) {
        return claimDao.findClaimsOfMaster(id, recordsPerPage, (currentPage - 1) * recordsPerPage).stream()
                .map(this::mapToClaimDto)
                .collect(Collectors.toList());
    }

    public Integer getNumberOfClaimsOfMaster(long id) {
        return claimDao.getNumberOfClaimsOfMaster(id);
    }

    public void updateByMaster(long claimId) {
        claimDao.updateByMaster(claimId);
    }

    public int getRecordsPerPage(String stringRecordsPerPage) {
        int recordsPerPage;
        try {
            recordsPerPage = Integer.valueOf(stringRecordsPerPage);
            if (recordsPerPage > 100) {
                recordsPerPage = 100;
            }
        } catch (Exception NullPointerException) {
            recordsPerPage = 10;
        }
        return recordsPerPage;
    }

    public int getCurrentPage(String stringCurrentPage, int numberOfPages) {
        int currentPage;
        try {
            currentPage = Integer.valueOf(stringCurrentPage);
            if (currentPage > numberOfPages) {
                currentPage = numberOfPages;
            }
        } catch (Exception NullPointerException) {
            currentPage = 1;
        }
        return currentPage;
    }

    public int getNumberOfPages(int rows, int recordsPerPage) {
        int numberOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }
        return numberOfPages;
    }
}
