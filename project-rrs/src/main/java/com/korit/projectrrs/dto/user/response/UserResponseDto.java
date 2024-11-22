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
    private String userName;
    private String userId;
    private String userPassword;
    private String userNickName;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    private String userEmail;
    private String userProfileImageUrl;

    public UserResponseDto(User user) {
        this.userName = user.getUserName();
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userNickName = user.getUserNickName();
        this.userPhone = user.getUserPhone();
        this.userAddress = user.getUserAddress();
        this.userAddressDetail = user.getUserAddressDetail();
        this.userEmail = user.getUserEmail();
        this.userProfileImageUrl = user.getUserProfileImageUrl();
    }

}
