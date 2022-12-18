package space.gavinklfong.demo.streamapi.practice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;

@Component
@RestController
@RequestMapping("/api")
public class TutorialController {
	
	@Autowired
	private CustomerRepo customerRepos;

	@GetMapping("/tutorials")
	public ResponseEntity<Object> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Customer> list = customerRepos.findAll();
		//	.forEach (c -> c.toString());
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Object> getTutorialById(@PathVariable("id") long id) {
		Optional<Customer> tutorialData = customerRepos.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/tutorials")
	public ResponseEntity<Customer> createTutorial(@RequestBody Customer customer) {
		try {
			Customer _tutorial = customerRepos
					.save(new Customer(customer.getId(),customer.getName(), customer.getTier()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Customer> updateTutorial(@PathVariable("id") long id, @RequestBody Customer customer) {
		Optional<Customer> tutorialData = customerRepos.findById(id);
		if (tutorialData.isPresent()) {
			Customer _customer = tutorialData.get();
			_customer.setTier(_customer.getTier());
			_customer.setId(_customer.getId());
			_customer.setName(_customer.getName());
			return new ResponseEntity<>(customerRepos.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			customerRepos.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			customerRepos.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
