package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.repositoiry.ProviderRepository;
import com.korit.projectrrs.repositoiry.ReviewRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl {
    private final ReviewRepository reviewRepository;
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
}
