package com.university.restaurant1.login.service;

import com.university.restaurant1.login.dto.LoginDTO;
import com.university.restaurant1.login.dto.LoginResponse;
import com.university.restaurant1.login.dto.SignupDTO;
import com.university.restaurant1.login.dto.SignupResponse;
import com.university.restaurant1.login.model.User;
import com.university.restaurant1.login.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.sql.Timestamp;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // إنشاء المستخدم الافتراضي كـ Admin
    @PostConstruct
    public void initAdminUser() {
        if (!userRepository.existsByUsername("ali95")) {
            User user = new User();
            user.setUsername("ali95");
            user.setPassword(passwordEncoder.encode("11111995"));  // تعيين كلمة المرور
            user.setEmail("admin@example.com");  // يمكنك تغيير البريد الإلكتروني إذا كنت ترغب في ذلك
            user.setRole("ADMIN");  // تعيين الصلاحيات كمسؤول
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
        }
    }

    @Transactional
    public SignupResponse registerUser(SignupDTO signupDTO) {
        // التحقق من وجود المستخدم بالفعل
        if (userRepository.existsByUsername(signupDTO.getUsername())) {
            return new SignupResponse("اسم المستخدم موجود بالفعل", false);
        }
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            return new SignupResponse("البريد الإلكتروني موجود بالفعل", false);
        }

        // إنشاء مستخدم جديد وحفظه
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setEmail(signupDTO.getEmail());
        user.setRole("USER");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return new SignupResponse("تم تسجيل المستخدم بنجاح", true);
    }

    public LoginResponse login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new LoginResponse("تم تسجيل الدخول بنجاح", true, null);
        } catch (Exception e) {
            return new LoginResponse("اسم المستخدم أو كلمة المرور غير صحيحة", false, null);
        }
    }

}
