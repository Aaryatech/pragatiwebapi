package com.ats.feedback.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.feedback.model.master.Company;
import com.ats.feedback.model.master.Customer;
import com.ats.feedback.model.master.ErrorMessage;
import com.ats.feedback.model.master.LoginResponse;
import com.ats.feedback.model.master.Screen;
import com.ats.feedback.model.master.ServiceAdviser;
import com.ats.feedback.model.master.User;
import com.ats.feedback.repository.master.CompanyRepo;
import com.ats.feedback.repository.master.CustomerRepository;
import com.ats.feedback.repository.master.ScreenRepository;
import com.ats.feedback.repository.master.ServiceAdviserRepository;
import com.ats.feedback.repository.master.UserRepository;

@RestController
public class MasterApiControlller {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ServiceAdviserRepository serviceAdviserRepository;

	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	ScreenRepository screenRepository;

	// -------------------User------------------------

	@RequestMapping(value = { "/saveUser" }, method = RequestMethod.POST)
	public @ResponseBody User saveUser(@RequestBody User user) {

		User res = new User();

		try {

			res = userRepository.saveAndFlush(user);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/getUserByUserId" }, method = RequestMethod.POST)
	public @ResponseBody User getUserByUserId(@RequestParam("userId") int userId) {

		User user = null;
		try {
			user = userRepository.findUserByUserIdAndDelStatus(userId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return user;

	}

	@RequestMapping(value = { "/getUserByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<User> getUserByCompanyId(@RequestParam("companyId") int companyId) {

		List<User> userList = new ArrayList<>();
		try {
			userList = userRepository.findUserByCompanyIdAndDelStatus(companyId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return userList;

	}

	@RequestMapping(value = { "/getAllUsersByDelStatus" }, method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsersByDelStatus() {

		List<User> user = new ArrayList<User>();

		try {

			user = userRepository.findAllByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return user;

	}

	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteUser(@RequestParam("userId") int userId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = userRepository.deleteUser(userId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage("Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}

	// -------------------ServiceAdviser------------------------

	@RequestMapping(value = { "/saveServiceAdviser" }, method = RequestMethod.POST)
	public @ResponseBody ServiceAdviser saveServiceAdviser(@RequestBody ServiceAdviser serviceAdviser) {

		ServiceAdviser res = new ServiceAdviser();

		try {

			res = serviceAdviserRepository.saveAndFlush(serviceAdviser);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/getSaBySaId" }, method = RequestMethod.POST)
	public @ResponseBody ServiceAdviser getSaBySaId(@RequestParam("saId") int saId) {

		ServiceAdviser serviceAdviser = null;
		try {
			serviceAdviser = serviceAdviserRepository.findServiceAdviserBySaIdAndDelStatus(saId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return serviceAdviser;

	}

	@RequestMapping(value = { "/getSaByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ServiceAdviser> getSaByCompanyId(@RequestParam("companyId") int companyId) {

		List<ServiceAdviser> saList = new ArrayList<>();
		try {
			saList = serviceAdviserRepository.findServiceAdviserByCompanyIdAndDelStatus(companyId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return saList;

	}

	@RequestMapping(value = { "/getAllSaByDelStatus" }, method = RequestMethod.GET)
	public @ResponseBody List<ServiceAdviser> getAllSaByDelStatus() {

		List<ServiceAdviser> serviceAdviser = new ArrayList<ServiceAdviser>();

		try {

			serviceAdviser = serviceAdviserRepository.findAllByDelStatus(0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return serviceAdviser;

	}

	@RequestMapping(value = { "/deleteServiceAdviser" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteServiceAdviser(@RequestParam("saId") int saId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = serviceAdviserRepository.deleteServiceAdviser(saId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage("Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}

	// ------------------Customer------------------------

	@RequestMapping(value = { "/saveCustomer" }, method = RequestMethod.POST)
	public @ResponseBody Customer saveCustomer(@RequestBody Customer customer) {

		Customer res = new Customer();

		try {

			res = customerRepository.saveAndFlush(customer);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/getCustomerByCustId" }, method = RequestMethod.POST)
	public @ResponseBody Customer getCustomerByCustId(@RequestParam("custId") int custId) {

		Customer customer = null;
		try {
			customer = customerRepository.findCustomerByCustIdAndDelStatus(custId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return customer;

	}

	@RequestMapping(value = { "/getCustomerByCustMobile" }, method = RequestMethod.POST)
	public @ResponseBody Customer getCustomerByCustMobile(@RequestParam("custMobile") String custMobile) {

		List<Customer> custList = new ArrayList<>();
		Customer customer = null;

		try {
			custList = customerRepository.findCustomerByCustMobileAndDelStatus(custMobile, 0);
			customer = custList.get(0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return customer;

	}

	@RequestMapping(value = { "/getCustomerByVehicleRegNo" }, method = RequestMethod.POST)
	public @ResponseBody Customer getCustomerByVehicleRegNo(@RequestParam("vehicleRegNo") String vehicleRegNo) {

		Customer cust = null;
		try {
			cust = customerRepository.findByVehicleRegNoAndDelStatus(vehicleRegNo, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return cust;

	}

	@RequestMapping(value = { "/getCustByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<Customer> getCustByCompanyId(@RequestParam("companyId") int companyId) {

		List<Customer> custList = new ArrayList<>();
		try {
			custList = customerRepository.findCustomerByCompanyIdAndDelStatus(companyId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return custList;

	}

	@RequestMapping(value = { "/getAllCustomersByDelStatus" }, method = RequestMethod.GET)
	public @ResponseBody List<Customer> getAllCustomersByDelStatus() {

		List<Customer> customer = new ArrayList<Customer>();

		try {

			customer = customerRepository.findAllByDelStatus(0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return customer;

	}

	@RequestMapping(value = { "/deleteCustomer" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteCustomer(@RequestParam("custId") int custId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = customerRepository.deleteCustomer(custId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage("Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}

	// -------------------Company------------------------

	@RequestMapping(value = { "/saveCompany" }, method = RequestMethod.POST)
	public @ResponseBody Company saveCompany(@RequestBody Company company) {

		Company res = new Company();

		try {

			res = companyRepo.saveAndFlush(company);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/getCompanyByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody Company getCompanyByCompanyId(@RequestParam("companyId") int companyId) {

		Company company = null;
		try {
			company = companyRepo.findCompanyByCompanyIdAndDelStatus(companyId, 0);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return company;

	}

	@RequestMapping(value = { "/getAllCompanyByDelStatus" }, method = RequestMethod.GET)
	public @ResponseBody List<Company> getAllCompanyByDelStatus() {

		List<Company> company = new ArrayList<Company>();

		try {

			company = companyRepo.findAllByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return company;

	}

	@RequestMapping(value = { "/deleteCompany" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteCompany(@RequestParam("companyId") int companyId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = companyRepo.deleteCompany(companyId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage("Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}

	// -------------------Screen------------------------

	@RequestMapping(value = { "/saveScreen" }, method = RequestMethod.POST)
	public @ResponseBody Screen saveScreen(@RequestBody Screen screen) {

		Screen res = new Screen();

		try {

			res = screenRepository.saveAndFlush(screen);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/getAllScreen" }, method = RequestMethod.GET)
	public @ResponseBody List<Screen> getAllScreen() {

		List<Screen> screen = new ArrayList<Screen>();

		try {

			screen = screenRepository.findAll();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return screen;

	}

	@RequestMapping(value = { "/getScreenByScreenId" }, method = RequestMethod.POST)
	public @ResponseBody Screen getScreenByScreenId(@RequestParam("screenId") int screenId) {

		Screen screen = null;
		try {
			screen = screenRepository.findScreenByScreenId(screenId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return screen;

	}

	@RequestMapping(value = { "/getScreenByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<Screen> getScreenByCompanyId(@RequestParam("companyId") int companyId) {

		List<Screen> screenList = new ArrayList<>();
		try {
			screenList = screenRepository.findScreenByCompanyId(companyId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return screenList;

	}

	@RequestMapping(value = { "/loginResponse" }, method = RequestMethod.POST)
	public @ResponseBody LoginResponse loginResponse(@RequestParam("userMobile") String userMobile,
			@RequestParam("password") String password) {

		LoginResponse loginResponse = new LoginResponse();
		try {

			User user = userRepository.findByUserMobileAndPasswordAndDelStatus(userMobile, password, 0);
			if (user == null) {
				loginResponse.setError(true);
				loginResponse.setMsg("login Failed");
			} else {
				loginResponse.setError(false);
				loginResponse.setMsg("login successfully");
				loginResponse.setUser(user);
			}

		} catch (Exception e) {

			e.printStackTrace();
			loginResponse.setError(true);
			loginResponse.setMsg("login Failed");
		}

		return loginResponse;
	}

}
