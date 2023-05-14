package ethci.lab.r3feditorback.service;

import ethci.lab.r3feditorback.Entity.projectsModel.Key;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.Repository.UserRepository;
import ethci.lab.r3feditorback.Entity.projectsModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public List<User> getUserRepository() {
        return repository.findAll();
    }

    public User insertUser(User user){
        return repository.save(user);
    }

    public Optional<User> findUserByID(String id) {
        return repository.findById(id);
    }
    public User findOne(String userName){
        return repository.findByName(userName);
    }

    public Resp<Key> setKey(String name, Key key){
        Optional<User> target = repository.findById(repository.findByName(name)._id);
        System.out.println(key);
        if (target.isPresent()) {
            User user = target.get();
            user.setKey(key);
            repository.save(user);
            return Resp.scusess(user.key);
        }else{
            return Resp.fail("403","user not found");
        }
    }

    public Resp<Key> getKey(String name){
        Optional<User> target = repository.findById(repository.findByName(name)._id);
        if (target.isPresent()) {
            return Resp.scusess(target.get().key);
        }else{
            return Resp.fail("403","user not found");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findOne(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User unfined");
        }else {
            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.role));
            return new org.springframework.security.core.userdetails.User(username,user.password, authorities);
        }
    }
}
