package br.com.lucas.petshopserviceuse.service.impl;

import br.com.lucas.petshopserviceuse.model.Admin;
import br.com.lucas.petshopserviceuse.model.Role;
import br.com.lucas.petshopserviceuse.enums.RoleName;
import br.com.lucas.petshopserviceuse.repository.AdminRepositoy;
import br.com.lucas.petshopserviceuse.repository.RoleRepositoy;
import br.com.lucas.petshopserviceuse.repository.UserRepositoy;
import br.com.lucas.petshopserviceuse.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import static br.com.lucas.petshopserviceuse.enums.RoleName.ROLE_ADMIN;
import static java.lang.System.out;

@Service
public class DBServiceImpl implements DBService {
    @Value("${api.profiles.images.profile}")
    private String image;
    @Autowired
    private RoleRepositoy roleRepositoy;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepositoy userRepositoy;
    @Autowired
    private AdminRepositoy adminRepositoy;

    private Role roleAdmin;


    @Override
    public void instantiateDatabase() {
        if (roleRepositoy.count()<1){
            this.createRole();
            this.createUser();
        }



    }


    @Override
    public void createRole() {
        out.println("-Create role....");
        for (RoleName profile : RoleName.values()) {
            var role = new Role(profile.name());
          role =   roleRepositoy.save(role);
          out.println("\n new Role:"+profile.name());
            if(role.getNameRole().equals(ROLE_ADMIN.name())){
                roleAdmin = role;
            }
        }
        out.println("\nCreate end role-");
    }

    @Override
    public void createUser() {
        out.println("-Create user....");
       Admin admin;
       admin = new Admin("lucasDev@email.com",encoder.encode("senha123456789"),"admin",this.image);
       admin.addRole(roleAdmin);
       roleAdmin.addUser(admin);
       adminRepositoy.save(admin);
       userRepositoy.save(admin);
        out.println("\n new User:"+admin.toString());
        out.println("\nCreate end user-");

    }



}
