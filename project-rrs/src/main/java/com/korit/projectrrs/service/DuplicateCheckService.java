package com.korit.projectrrs.service;

public interface DuplicateCheckService {
    boolean checkUsername(String username);
    boolean checkNickname(String nickname);
    boolean checkPhone(String phone);
    boolean checkEmail(String email);
}
