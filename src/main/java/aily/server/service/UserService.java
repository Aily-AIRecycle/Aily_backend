package aily.server.service;

import aily.server.DTO.MyPageDTO;
import aily.server.entity.MyPage;
import aily.server.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.OptionalInt;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MyPageRepository myPageRepository;
    private static final String IMAGE_DIRECTORY = "/home/lee/image/";
    private static final String sourceFilePath = "/home/lee/image/default/image.png";


    //유저의 프로필사진 표시
    public String userimageshow(String phonenumber){
        String data = userRepository.finduserprofile(phonenumber);
        return data;

    }

    @Transactional
    public void userupdateinformation(User user, String number){
        System.out.println(user.getMyPage().getNickname());
        userRepository.updateUserEmail(user.getEmail(), number);
        userRepository.updateMyPageNickname(user.getMyPage().getNickname(), number);
        userRepository.updateMyPageProfile(user.getMyPage().getProfile(), number);
    }

    //회원 내정보 수정 데이터 조회
    public Optional<User> finduserinformation(String phonenumber){
        return userRepository.findByPhoneNumber(phonenumber);
    }

    //회원탈퇴
    public void deleteuser(User user){
        userRepository.delete(user);
    }

    public String findpassworduser(User user){
        if(userRepository.findUserPassword(user.getPassword())){
            return "yes";
        }else {
            return "no";
        }
    }

    //개인 쓰레기 데이터 조회
    public String userTotalDonutes(String phonenumber){
       return myPageRepository.finduserTotalDonut(phonenumber);
    }

    //회원 핸드폰 번호를 이용한 닉네임 조회
    public String userPhonenumberbyNickname(String phonenumber){
        System.out.println("userPhonenumber" + phonenumber);
        return userRepository.findNameByPhonenumber(phonenumber);
    }

    //회원 닉네임을 이용한 휴대폰 번호 조회
    public String userPhonenumber(String phonenumber){
        System.out.println("userPhonenumber" + phonenumber);
        return userRepository.findPhoneNumberByNickname(phonenumber);
    }


    public void signUp(User user) {
        userRepository.save(user);
    }

    public UserDTO signIn(UserDTO params) {
        Optional<User> user = userRepository.findByEmail(params.getEmail());
        if(user.isPresent()){
            UserDTO userDTO = UserDTO.toUserDTO(user.get());
            String dbPass = userDTO.getPassword();
            String inputPass = params.getPassword();
            System.out.println("db = " + dbPass);
            System.out.println("input = " + inputPass);
            if(dbPass.equals(inputPass)){
                //all_ok
                userDTO.setPassword("");
                System.out.println("all_ok");
                return userDTO;
            }
            //id만 맞고 비밀번호 틀렸을 때 id_ok
            System.out.println("id_ok");
            return params;
        } else {
            System.out.println("faild");
            params.setEmail("Faild");
            return params;
        }
    }

    public String test(String phone) {
        Optional<User> user = userRepository.findUserByPhonenumber(phone);
        if(user.isPresent()){
            MyPageDTO myPageDTO = MyPageDTO.toMyPageDTO(user.get().getMyPage());
            return myPageDTO.toString();
        } else {
            return "NFT";
        }
    }

    public String delUser(User user){
        userRepository.delete(user);
        return user.getMyPage().getNickname() + "삭제";
    }

    public MyPageDTO getupdatemypage(String phone) {
        Optional<User> user = userRepository.findUserByPhonenumber(phone);
        if(user.isPresent()){
            MyPageDTO myPageDTO = MyPageDTO.toMyPageDTO(user.get().getMyPage());
            return myPageDTO;
        }
        System.out.println("시스템오류");
        return null;
    }



    public String checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return "no";
        } else {
            return "yes";
        }
    }

    public String checkPwd(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if(user.isPresent()){
            String phone = user.get().getPhonenumber();
            if(phone.equals(userDTO.getPhonenumber())){
                return user.get().getEmail();
            }
            return "Different PhoneNumber";
        } else {
            return "Not Found";
        }
    }

    public String changPwd(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if(user.isPresent()){
            if(user.get().getPassword().equals(userDTO.getPassword())){
                return "No";
            } else {
                user.get().setPassword(userDTO.getPassword());
                User chUser = user.get();
                userRepository.save(chUser);
                return "Clear";
            }
        } else {
            return "No";
        }

    }

    public String checkNick(String nickname) {
        Optional<MyPage> myPage = myPageRepository.findByNickname(nickname);
        if(myPage.isPresent()){
            //중복 됨
            return "no";
        } else {
            //중복 안됨
            return "yes";
        }
    }


    public ResponseEntity<ByteArrayResource> getImage(String id) throws IOException {
        String imagePath = IMAGE_DIRECTORY + id + "/image.png";
        Path path = Paths.get(imagePath);

            // 파일이 존재하지 않을 경우
            // 요구에 맞게 파일 생성
            File sourceFile = new File(sourceFilePath);
            File destinationFile = new File(imagePath);



            createImageFile(id);
            createJsonFile(id);

            copyFile(sourceFile, destinationFile);

            // 파일을 다시 읽어와 리턴
            byte[] imageBytes = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return ResponseEntity.ok()
                    .contentLength(imageBytes.length)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        }

//    }
    //신규 가입시 회원 폴더 생성
    private void createImageFile(String id) throws IOException {
        String directoryPath = IMAGE_DIRECTORY + id;

        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    //회원 가입시 JSON파일 생성
    private void createJsonFile(String id) throws IOException {
        String path = IMAGE_DIRECTORY + id;
        File f2 = new File(path, id+".json");

        if (!f2.exists()) {    // 파일이 존재하지 않으면 생성
            try {
                if (f2.createNewFile())
                    System.out.println("파일 생성 성공");
                else
                    System.out.println("파일 생성 실패");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {    // 파일이 존재한다면
            System.out.println("파일이 이미 존재합니다.");
        }
        System.out.println();

    }

    //default 이미지 파일 복사
    private static void copyFile(File source, File destination) throws IOException {
        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    //비회원이 회원가입시 or 회원정보 수정 시 폴더,json파일 이름 변경
    public void renameFileFolder(String id, String oldid){
        File oldFolder = new File(IMAGE_DIRECTORY + oldid );
        File newFolder = new File(IMAGE_DIRECTORY + id);

        File oldFile = new File(IMAGE_DIRECTORY + id + "/" + oldid + ".json" );
        File newFile = new File(IMAGE_DIRECTORY + id+ "/" + id + ".json");

        boolean Folderresult = oldFolder.renameTo(newFolder);
        boolean Fileresult = oldFile.renameTo(newFile);
        System.out.println("파일 이름 변경 : " + Fileresult);
        System.out.println("폴더 이름 변경 : " + Folderresult);

    }
}