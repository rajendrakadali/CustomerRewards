package com.sravani.cr.service;

import com.sravani.cr.exception.UserAlreadyExistsException;
import com.sravani.cr.model.Customer;
import com.sravani.cr.model.Users;
import com.sravani.cr.repository.CustomerRepository;
import com.sravani.cr.repository.UsersRepository;
import com.sravani.cr.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	@Qualifier("userdatasource")
	DataSource dataSource;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public Customer save(Customer customer) throws UserAlreadyExistsException{
		log.info("Received customer inputs with username: {}",customer.getUser().getUsername());
		Users userInfo = customer.getUser();
		Customer savedCustomer = null;
		List<Users> findUserByReceivedInput = usersRepository.findByUsername(userInfo.getUsername());
		if(findUserByReceivedInput.isEmpty()){
			try{
				UserDetails user = User
						.withUsername(userInfo.getUsername())
						.password("{bcrypt}"+bCryptPasswordEncoder.encode(userInfo.getPassword()))
						.authorities(Constants.Authorities.READ.name(), Constants.Authorities.WRITE.name())
						.roles(Constants.Roles.USER.name())
						.build();
				log.info("Persisting new user details to database.");
				JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
				jdbcUserDetailsManager.createUser(user);
				savedCustomer = customerRepository.save(customer);
			}catch (Exception ex){
				throw new RuntimeException(ex.getMessage());
			}
		}else{
			StringBuilder sb = new StringBuilder("User with the username: ").append(userInfo.getUsername()).append(" already exists.");
			throw new UserAlreadyExistsException(sb.toString());
		}
		return savedCustomer;
	}
	
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomer(Integer custId) {
		return customerRepository.findById(custId).get();
	}

}
