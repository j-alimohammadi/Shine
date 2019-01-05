package com.shine.api.rest.endpoint.user;

import com.shine.api.rest.endpoint.BaseEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

import static com.shine.api.rest.endpoint.BaseEndpoint.API_VERSION;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@RestController
@RequestMapping(path = "api/" + API_VERSION + "/user")
public class UserManagement extends BaseEndpoint {

    @PostMapping("/")
    public ResponseEntity registerUser(String userName, String password) {

    }
}
