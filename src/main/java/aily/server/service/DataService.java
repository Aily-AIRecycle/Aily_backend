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

    //Number의 갯수
    public Optional<List<String>> findnumber(){

        return dataRepository.findnumbers();
    }
    

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
    
    
    //최근에 저장된 날짜 순으로 정렬하여 데이터를 뽑아오는 함수
    public List<AvgDataDTO> findAvgData() {
        Optional<List<String>> distinctNumber = findnumber();
        List<AvgDataDTO> dataDTOList = new ArrayList<>();

        distinctNumber.ifPresent(numbers -> {
            for (String number : distinctNumber.get()) {
                Optional<AvgDataEntity> dataOptional = dataRepository.findLatestByNumber(String.valueOf(number));
                AvgDataDTO dataDTO = new AvgDataDTO();
                // Set data from distinctNumber
                // Set data from dataOptional
                dataDTO.setAilynumber(number);
                dataDTO.setDay(dataOptional.get().getDay());
                dataDTO.setAvgpet(dataOptional.get().getPet());
                dataDTO.setAvggen(dataOptional.get().getGen());
                dataDTO.setAvgcan(dataOptional.get().getCan());
                // do the mapping for the rest of the fields
                dataDTOList.add(dataDTO);
            }
        });
        return dataDTOList;
    }
}
