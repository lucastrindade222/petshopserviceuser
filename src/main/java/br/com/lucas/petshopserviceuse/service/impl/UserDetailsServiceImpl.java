package br.com.lucas.petshopserviceuse.service;




import br.com.lucas.petshopserviceuse.model.User;
import br.com.lucas.petshopserviceuse.repository.UserRepositoy;
import br.com.lucas.petshopserviceuse.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoy repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = repo.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado! "+email);
        }
        return new UserSS(usuario.getId(),usuario.getEmail(),usuario.getPassword());
    }





}
