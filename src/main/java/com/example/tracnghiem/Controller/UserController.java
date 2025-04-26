package com.example.tracnghiem.Controller;

import com.example.tracnghiem.DTO.ChangePasswordDTO;
import com.example.tracnghiem.DTO.ChangeProfileDTO;
import com.example.tracnghiem.DTO.UserDTO;
import com.example.tracnghiem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PutMapping("information/{idUser}")
    public ResponseEntity<?> UpdateInformationUser(@RequestBody ChangeProfileDTO changeProfileDTO, @PathVariable int idUser) {
        try{
            UserDTO userDTO=userService.changeInformationUser(changeProfileDTO,idUser);
            return ResponseEntity.ok().body(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("changePassword")
    public ResponseEntity<?> ChangePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            boolean checked= userService.changePassword(changePasswordDTO);
            if(checked){
                return ResponseEntity.ok().body("Thay Đổi Mật Khẩu Thành Công!");
            }
            return ResponseEntity.badRequest().body("Có lỗi xảy ra trong trình thay đổi ");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
