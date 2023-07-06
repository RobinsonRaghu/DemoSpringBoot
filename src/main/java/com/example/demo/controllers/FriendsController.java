package com.example.demo.controllers;


import com.example.demo.dao.FriendsDao;
import com.example.demo.models.FriendsInputVo;
import com.example.demo.models.FriendsTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 4800, allowCredentials = "false", methods = {
        RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT,
        RequestMethod.HEAD, RequestMethod.TRACE })
@RestController
public class FriendsController {

    @Autowired
    FriendsDao friendsDao;

    @GetMapping("")
    public ResponseEntity<List<FriendsInputVo>> getFriends() {

        List<FriendsTable> friendsTables = friendsDao.findAll();
        List<FriendsInputVo> out = new ArrayList<>();
        for (FriendsTable friend : friendsTables) {
            FriendsInputVo input = new FriendsInputVo();
            input.setName(friend.getName());
            input.setId(friend.getId());
            input.setArea(friend.getArea());
            input.setDob(friend.getDob());
            input.setPhoto(new String(friend.getPhoto()));
            out.add(input);
        }

        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<FriendsTable> addFriends(@RequestBody FriendsInputVo input) throws IOException {

        FriendsTable table = new FriendsTable();
        table.setName(input.getName());
        table.setArea(input.getArea());
        table.setPhoto(input.getPhoto().getBytes(StandardCharsets.UTF_8));
        table.setDob(input.getDob());

        FriendsTable _table = friendsDao.save(table);

        return new ResponseEntity<>(_table, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteFriends(@RequestBody FriendsInputVo input) throws IOException {

        friendsDao.deleteById(input.getId());

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
