package com.korit.projectrrs.dto.user.response;

import com.korit.projectrrs.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private String userName;
    private String password;
    private String nickName;
    private String phone;
    private String address;
    private String addressDetail;
    private String email;
    private String profileImageUrl;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.nickName = user.getNickname();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.addressDetail = user.getAddressDetail();
        this.email = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
