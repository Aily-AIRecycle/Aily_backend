package aily.server.service;

import aily.server.DTO.AvgDataDTO;
import aily.server.entity.AvgDataEntity;
import aily.server.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataRepository dataRepository;

    
    //데이터 셋을 db에 저장시키는 함수
    public void saveData(AvgDataDTO avgDataDTO) {
        AvgDataEntity dataEntity = new AvgDataEntity();
        dataEntity.setId(avgDataDTO.getAilynumber());
        dataEntity.setDay(avgDataDTO.getDay());
        dataEntity.setCan(avgDataDTO.getAvgcan());
        dataEntity.setGen(avgDataDTO.getAvggen());
        dataEntity.setPet(avgDataDTO.getAvgpet());
        dataRepository.save(dataEntity);
    }

    //기계 이름을 전부 조회후, 중복을 제거하여 몆대인지 파악하는 함수
    public Long countDistinctNumber() {
        Optional<Long> dd = dataRepository.countDistinctNumber();
        return dd.orElse(0L);
    }
    
    
    //최근에 저장된 날짜 순으로 정렬하여 데이터를 뽑아오는 함수
    public List<AvgDataDTO> findAvgData() {
        Long distinctNumber = countDistinctNumber();
        Optional<List<AvgDataEntity>> data = dataRepository.findRecentDataOptional(Optional.of(distinctNumber));
        List<AvgDataDTO> dataDTOList = new ArrayList<>();

        data.ifPresent(avgDataEntities -> {
            for (AvgDataEntity avgDataEntity : avgDataEntities) {
                AvgDataDTO dataDTO = new AvgDataDTO();
                // 데이터 매핑 작업
                dataDTO.setAilynumber(avgDataEntity.getId());
                dataDTO.setDay(avgDataEntity.getDay());
                dataDTO.setAvgpet(avgDataEntity.getPet());
                dataDTO.setAvggen(avgDataEntity.getGen());
                dataDTO.setAvgcan(avgDataEntity.getCan());
                // 나머지 필드에 대한 매핑 작업 수행
                dataDTOList.add(dataDTO);
            }
        });

        return dataDTOList;
    }

}
