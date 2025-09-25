package org.example.brokerapplication.service;

import org.example.brokerapplication.dto.response.MarketSecuritiesResponseDto;
import org.example.brokerapplication.entity.Security;
import org.example.brokerapplication.mapper.MarketMapper;
import org.example.brokerapplication.repository.SecurityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {

    private final SecurityRepository securityRepository;
    private final MarketMapper marketMapper;

    public MarketService(SecurityRepository securityRepository, MarketMapper marketMapper) {
        this.securityRepository = securityRepository;
        this.marketMapper = marketMapper;
    }

    @Transactional
    public List<MarketSecuritiesResponseDto> getSecurities() {
        List<Security> securityList = securityRepository.findAll();
        List<MarketSecuritiesResponseDto> marketSecuritiesResponseDtoList = new ArrayList<>();
        for (Security security : securityList) {
            marketSecuritiesResponseDtoList.add(marketMapper.toResponseDto(security));
        }
        return marketSecuritiesResponseDtoList;
    }
}
