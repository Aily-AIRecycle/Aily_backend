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

    public void DataSave(AvgDataDTO avgDataDTO){


        AvgDataEntity dataEntity = new AvgDataEntity();
        dataEntity.setId(avgDataDTO.getAilynumber());
        dataEntity.setDay(avgDataDTO.getDay());
        dataEntity.setCan(avgDataDTO.getAvgcan());
        dataEntity.setGen(avgDataDTO.getAvggen());
        dataEntity.setPet(avgDataDTO.getAvgpet());


        dataRepository.save(dataEntity);

    }
    public Optional<Long> ResultAvgNumber(){
        Optional<Long> dd = dataRepository.countDistinctNumber();
        Long result = 0L;
        if (dd.isPresent()) {
            result = dd.get();
            System.out.println(result);
        }
        return result.describeConstable();
    }

    public List<AvgDataDTO> findAvgData() {
        Optional<List<AvgDataEntity>> data = dataRepository.findRecentDataOptional(ResultAvgNumber());
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
                System.out.println("dataDTO.getAilynumber() = " + dataDTO.getAilynumber());
                // 나머지 필드에 대한 매핑 작업 수행
                dataDTOList.add(dataDTO);
            }
        });

        return dataDTOList;
    }
}
