package com.example.nuzlocke_tracker_api.Security.user;

import com.example.nuzlocke_tracker_api.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(userService.getAllUsers(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainer(@PathVariable(value = "id") Integer id){
        try {
            return ResponseEntity.ok().body(userService.getUser(id));
        } catch(Exception e){
            ApiResponse response = new ApiResponse("An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PostMapping("/email")
    public ResponseEntity<?> getTrainerByEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        try {
            return ResponseEntity.ok().body(userService.getUserByEmail(email));
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
