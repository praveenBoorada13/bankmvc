package com.cognizant.bankmvc.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.bankmvc.model.Account;
import com.cognizant.bankmvc.model.AccountInput;
import com.cognizant.bankmvc.model.TransactionInput;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;



@FeignClient(name = "account-ms", url = "${feign.url-account-service}")
public interface AccountFeign {
	
	/**
	 * @param token
	 * @param customerId
	 * @param account
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/createAccount/{customerId}")
	public ResponseEntity<?> createAccount(@RequestHeader("Authorization") String token,@PathVariable String customerId,@Valid @RequestBody Account account);

	/**
	 * @param token
	 * @param accInput
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@RequestHeader("Authorization") String token,@RequestBody AccountInput accInput);

	
	/* To Withdraw cash in  the account  
	 * 
	 *  This controller is called by Account-Ms to Transaction-MS for Withdrawing in the Account
	 *  */
	/**
	 * @param token
	 * @param accInput
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/withdraw")
	public ResponseEntity<?> withdraw(@RequestHeader("Authorization") String token, @RequestBody AccountInput accInput);
	

	/* To Transaction of cash in  the other account  
	 * 
	 *  This controller is called by Account-Ms to Transaction-MS for Transaction in the another Account
	 *  */
	/**
	 * @param token
	 * @param transInput
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/transaction")
	public ResponseEntity<?> transaction(@RequestHeader("Authorization")String token, @RequestBody TransactionInput transInput);
	
	/**
	 * @param token
	 * @param customerId
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping("deleteAccount/{customerId}")
	public ResponseEntity<String> deleteAccount(@RequestHeader("Authorization") String token,@PathVariable String customerId);

	
}
