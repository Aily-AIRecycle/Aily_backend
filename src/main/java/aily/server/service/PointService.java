package aily.server.service;

import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PointService {

    private static final String JSON_FILE = "/home/ubuntu/image/";

    private final UserRepository userRepository;
    public void savepoint(User user){
        userRepository.savePoint(user);
        System.out.println("Service : " + user.getMyPage().getCAN());
    }

    public List<Map<String, Object>> writeuserjson(String name, UserDTO userDTO) throws IOException {

        String jsonfilepath = JSON_FILE + name + "/" + name + ".json";
        File file = new File(jsonfilepath);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> data = new ArrayList<>();
        if (file.length() > 0) {
            data = objectMapper.readValue(file, new TypeReference<>() {});
        }

        Map<String, Object> newDate = new HashMap<>();
        SimpleDateFormat newnow = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat newnow2 = new SimpleDateFormat("HH시 mm분 ss초");

        String formatedNow = newnow.format(new Date());

        String formatedNow2 = newnow2.format(new Date());

        newDate.put("day",formatedNow );
        newDate.put("time",formatedNow2 );
        newDate.put("can", userDTO.getCAN());
        newDate.put("gen", userDTO.getGEN());
        newDate.put("pet", userDTO.getPET());
        newDate.put("point", userDTO.getPoint());
        data.add(newDate);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);

        return data;

    }

}
