package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CreateUserDto;
import com.ecommerce.ecommerce.dto.UpdateUserDto;
import com.ecommerce.ecommerce.errorresponse.ErrorResponse;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class  )

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows(Exception.class)
    public ResponseEntity<Object>create(CreateUserDto dto){

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> res = new HashMap<String, Object>();

        User user = new User();

        user.setUsername(dto.getUsername().trim());
        user.setPhone(dto.getPhone().trim());
        user.setAddress(dto.getAddress().trim());


        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();

    }

    public ResponseEntity<Object>update(UpdateUserDto dto){

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> res = new HashMap<String, Object>();

        User user = userRepository.findById(dto.getId()).orElse(null);
        if(Optional.ofNullable(user).isPresent()){

            user.setUsername(dto.getUsername().trim());
            user.setPhone(dto.getPhone().trim());
            user.setAddress(dto.getAddress().trim());
            userRepository.save(user);
        }else
        {
            ErrorResponse err = new ErrorResponse("999","Data Not Found");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(err);
        }




        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();

    }

    public ResponseEntity<Object> getUser(String userName) {
        Map<String, Object> res = new HashMap<String, Object>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        val std = userRepository.findByUsername(userName);

        if (Optional.ofNullable(std).isPresent()) {
            res.put("message", "success");
            res.put("data", std);
        } else {
            res.put("message", "failed");
            res.put("data", null);
        }

        res.put("code", HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);
    }

    @SneakyThrows(Exception.class)
    public ResponseEntity<Object> deleteUser(Long id) {

        Map<String, Object> res = new HashMap<String, Object>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        val user = userRepository.findById(id).orElse(null);

        if (Optional.ofNullable(user).isPresent()) {

            userRepository.deleteById(id);

            res.put("message", "success");
            res.put("data", user);

        } else {
            res.put("message", "failed");
            res.put("data", null);
        }

        res.put("code", HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
    }
}
