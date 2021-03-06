package com.esipe.ms.resource;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esipe.ms.domain.Authenticate;
import com.esipe.ms.domain.SimpleToken;
import com.esipe.ms.domain.User;
import com.esipe.ms.security.JWTTokenGenerator;
import com.esipe.ms.security.JWTTokenValidator;
import com.esipe.ms.service.UserService;

@RestController
@RequestMapping(value = "users", produces = {"application/json"})
public class UserResource {

	private UserService userService;
	
	private JWTTokenGenerator tokenGenerator;
	
	private JWTTokenValidator tokenValidator;
	
	@Autowired
	public UserResource(UserService us, 
						JWTTokenGenerator tokenGenerator,
						JWTTokenValidator tokenValidator){
		userService = us;
		this.tokenGenerator = tokenGenerator;
		this.tokenValidator = tokenValidator;
	}
	
	@PostMapping("/authenticate")
	public String authenticate(@RequestBody Authenticate authenticate) throws AuthenticationException{
		
		User user = userService.getOne(authenticate.getLogin());
		
		if(user != null){
			return tokenGenerator.generateToken(user.getEmail(), user.getAuthorities());
		}
		throw new AuthenticationException();
	}
	
	@PostMapping("verify")
	public List<String> verifyToken(@RequestBody  SimpleToken token){
		
		return tokenValidator.verifyAndTransform(token.getToken());
	}

	@GetMapping("{email}")
	public User getOne(@PathVariable("email") String email){
		
		return userService.getOne(email);
	}
	
	@GetMapping
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@PostMapping
	public void add(@RequestBody User user){
		
		userService.add(user);
	}
	
	@PutMapping("{email}")
	public void update(@PathVariable("email") String email, @RequestBody User user){
		
		if(!email.equals(user.getEmail())){
			throw new RuntimeException("update user exception");
		}
		
		userService.update(user);
	}
	
	@DeleteMapping("{email}")
	public void delete(@PathVariable("email") String email){
		
		userService.delete(email);
	}
}
