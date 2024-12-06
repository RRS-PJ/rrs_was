package com.korit.projectrrs.dto.user.response;

import com.korit.projectrrs.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private String userName;
    private String password;
    private String userNickName;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    private String userEmail;
    private String userProfileImageUrl;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.userNickName = user.getUserNickName();
        this.userPhone = user.getUserPhone();
        this.userAddress = user.getUserAddress();
        this.userAddressDetail = user.getUserAddressDetail();
        this.userEmail = user.getUserEmail();
        this.userProfileImageUrl = user.getUserProfileImageUrl();
    }

}
