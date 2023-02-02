package com.springboard.spring_board.auth;


import com.springboard.spring_board.domain.User;
import com.springboard.spring_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티에서 .loginProcessingUrl("/login")
//login 요청이 오면 자동으로 PrincipalDetailsService타입으로 Ioc되어있는 loadUserByUsername 함수 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    //시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername!=null){
            return new PrincipalDetails(byUsername);
        }
        throw new
                UsernameNotFoundException("User not exist with name :" +username);

    }


}
