package com.skcc;

import com.skcc.account.Account;
import com.skcc.com.SecurityLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

    public void dashboard() { // UserDetailsService는 DAO를 통해 유저정보를 가져와서 시큐리티에 던져주기만한다.
                                //그러면 누가 인증을 하느냐 authenticatioManager가 한다.
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//홀더안에는 반드시 인증 된 정보가 들어간다.
//        Object principal = authentication.getPrincipal(); // 인증한 사용자를 나타내는 정보//우리가 커스터마이징한 그 객체타입으로 나온다
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); //사용자가 가지고 있는 권한을 나타내는 정보
//        authentication.getCredentials(); // 인증한다음에는 크레덴셜을 가지고 있을리가 없다. 널이다.
//        boolean authenticated = authentication.isAuthenticated();

    //스레드로컬 확인
        Account account = AccountContext.getAccount();
        System.out.println("==============");
        System.out.println(account.getUsername());

    }

    @Async
    public void asyncService() {
        SecurityLog.log("asyncService 안에");
    }
}

//Authentication
//        Principal과 GrantAuthority 제공.
//
//        Principal
//        “누구"에 해당하는 정보.
//        UserDetailsService에서 리턴한 그 객체.
//        객체는 UserDetails 타입.
//        GrantAuthority:
//        “ROLE_USER”, “ROLE_ADMIN”등 Principal이 가지고 있는 “권한”을 나타낸다.
//        인증 이후, 인가 및 권한 확인할 때 이 정보를 참조한다.
//
//        UserDetails
//        애플리케이션이 가지고 있는 유저 정보와 스프링 시큐리티가 사용하는 Authentication 객체 사이의 어댑터.
//        UserDetailsService
//        유저 정보를 UserDetails 타입으로 가져오는 DAO (Data Access Object) 인터페이스.
//        구현은 마음대로! (우리는 스프링 데이터 JPA를 사용했습니다.)

