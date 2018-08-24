package com.ats.feedback.controller;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.feedback.common.Firebase;
import com.ats.feedback.model.master.Customer;
import com.ats.feedback.model.master.ErrorMessage;
import com.ats.feedback.model.master.Notification;
import com.ats.feedback.model.master.User;
import com.ats.feedback.model.transaction.FeedDetail;
import com.ats.feedback.model.transaction.FeedHeader;
import com.ats.feedback.model.transaction.GetFeedDetail;
import com.ats.feedback.model.transaction.GetFeedHeader;
import com.ats.feedback.repository.master.CustomerRepository;
import com.ats.feedback.repository.master.FeedDetailRepo;
import com.ats.feedback.repository.master.FeedHeaderRepo;
import com.ats.feedback.repository.master.GetFeedDetailRepo;
import com.ats.feedback.repository.master.GetFeedHeaderRepo;
import com.ats.feedback.repository.master.NotiRepo;
import com.ats.feedback.repository.master.UserRepository;

@RestController
public class FeedbackController {

	@Autowired
	FeedHeaderRepo feedHeaderRepo;

	@Autowired
	FeedDetailRepo feedDetailRepo;

	@Autowired
	GetFeedHeaderRepo getFeedHeaderRepo;

	@Autowired
	GetFeedDetailRepo getFeedDetailRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	NotiRepo notiRepo;

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping(value = { "/saveFeedbackHeaderDetail" }, method = RequestMethod.POST)
	public @ResponseBody FeedHeader saveFeedbackHeaderDetail(@RequestBody FeedHeader feedHeader) {

		FeedHeader feedHeaderRes = new FeedHeader();
		List<User> userList = new ArrayList<>();

		try {

			feedHeaderRes = feedHeaderRepo.saveAndFlush(feedHeader);
			System.out.println("feedHeader" + feedHeader.toString());

			for (int i = 0; i < feedHeader.getFeedDetailList().size(); i++) {
				feedHeader.getFeedDetailList().get(i).setFbId(feedHeaderRes.getFbId());

				List<FeedDetail> feedDetailList = feedDetailRepo.saveAll(feedHeader.getFeedDetailList());
				System.out.println("feedDetailList" + feedDetailList.toString());

				feedHeaderRes.setFeedDetailList(feedDetailList);
			}

			System.out.println("Status for notification test" + feedHeader.getStatus());

			Notification res = new Notification();
			if (feedHeader.getStatus() == 0) {
				userList = userRepository.findTokenByUserTypeIdAndDelStatus(2);

				System.out.println("STATUS : 0----------------" + userList);

				for (int j = 0; j < userList.size(); j++) {
					Firebase.sendPushNotification(userList.get(j).getToken(), "CRM Notification",
							"Following customer is unsatisfied\nCustomer Name : " + feedHeader.getCustName()
									+ "\nVehicle No. : " + feedHeader.getVehRegNo(),
							2);

					res.setDelStatus(1);
					res.setUserId(userList.get(j).getUserId());

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date dateobj = new Date();
					System.out.println(df.format(dateobj));
					res.setDate(df.format(dateobj));
					res.setDesc("Noti");
					res.setTitle("Notification");

					res = notiRepo.save(res);
					System.out.println("res1" + res);
				}
			} else if (feedHeader.getStatus() == 1) {
				userList = userRepository.findTokenByUserTypeIdAndDelStatus(1);

				System.out.println("STATUS : 1----------------" + userList);

				for (int j = 0; j < userList.size(); j++) {
					Firebase.sendPushNotification(userList.get(j).getToken(), "GM Notification",
							"Following customer is unsatisfied from CRM\nCustomer Name : " + feedHeader.getCustName()
									+ "\nVehicle No. : " + feedHeader.getVehRegNo(),
							1);
					res.setDelStatus(1);
					res.setUserId(userList.get(j).getUserId());

					DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date dateobj = new Date();
					System.out.println(df.format(dateobj));
					res.setDate(df.format(dateobj));
					res.setDesc("Noti");
					res.setTitle("Notification");

					res = notiRepo.saveAndFlush(res);
					System.out.println("res1" + res);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return feedHeaderRes;

	}

	@RequestMapping(value = { "/getFeedHeaderAndDetail" }, method = RequestMethod.POST)
	public @ResponseBody FeedHeader getFeedHeaderAndDetail(@RequestParam("fbId") int fbId) {

		FeedHeader feedHeader = new FeedHeader();

		try {

			feedHeader = feedHeaderRepo.findByFbId(fbId);
			List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(fbId);
			feedHeader.setFeedDetailList(feedDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return feedHeader;

	}

	@RequestMapping(value = { "/getFeedbackByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody FeedHeader getFeedbackByCompanyId(@RequestParam("companyId") int companyId) {

		FeedHeader feedHeader = new FeedHeader();

		try {

			feedHeader = feedHeaderRepo.findByCompanyId(companyId);
			List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(feedHeader.getFbId());
			feedHeader.setFeedDetailList(feedDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return feedHeader;

	}

	@RequestMapping(value = { "/getFeedbackByCompanyIdAndStatus" }, method = RequestMethod.POST)
	public @ResponseBody List<GetFeedHeader> getFeedbackByCompanyIdAndStatus(@RequestParam("companyId") int companyId,
			@RequestParam("status") int status) {

		List<GetFeedHeader> feedHeaderList = new ArrayList<>();

		try {

			feedHeaderList = getFeedHeaderRepo.getAllFeedHeaderByCompanyId(companyId, status);
			for (int i = 0; i < feedHeaderList.size(); i++) {

				List<GetFeedDetail> feedDetailList = getFeedDetailRepo
						.getDetailByQueNo(feedHeaderList.get(i).getFbId());

				feedHeaderList.get(i).setGetFeedDetailList(feedDetailList);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return feedHeaderList;

	}

	@RequestMapping(value = { "/getFeedbackList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetFeedHeader> getFeedbackList(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("saId") int saId, @RequestParam("status") int status) {

		List<GetFeedHeader> feedHeaderList = new ArrayList<>();

		try {

			if (saId != 0 && status != -1) {

				feedHeaderList = getFeedHeaderRepo.getFeedbackList(companyId, fromDate, toDate, saId, status);

				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<GetFeedDetail> feedDetailList = getFeedDetailRepo
							.getDetailByQueNo(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setGetFeedDetailList(feedDetailList);
				}
			} else if (saId != 0 && status == -1) {

				feedHeaderList = getFeedHeaderRepo.getFeedbackListAllStatus(companyId, fromDate, toDate, saId);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<GetFeedDetail> feedDetailList = getFeedDetailRepo
							.getDetailByQueNo(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setGetFeedDetailList(feedDetailList);
				}

			}

			else if (saId == 0 && status == -1) {

				feedHeaderList = getFeedHeaderRepo.getFeedbackListAllStatusAndSaId(companyId, fromDate, toDate);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<GetFeedDetail> feedDetailList = getFeedDetailRepo
							.getDetailByQueNo(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setGetFeedDetailList(feedDetailList);
				}

			}

			else if (saId == 0 && status != -1) {

				feedHeaderList = getFeedHeaderRepo.getFeedbackListAllSaId(companyId, fromDate, toDate, status);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<GetFeedDetail> feedDetailList = getFeedDetailRepo
							.getDetailByQueNo(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setGetFeedDetailList(feedDetailList);
				}

			}

		} catch (

		Exception e) {

			e.printStackTrace();

		}
		return feedHeaderList;

	}

	@RequestMapping(value = { "/deleteFeedHeader" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteFeedHeader(@RequestParam("fbId") int fbId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = feedHeaderRepo.deleteFeedHeader(fbId);

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

	@RequestMapping(value = { "/updateFeedHeader" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateFeedHeader(@RequestParam("fbId") int fbId,
			@RequestParam("gmRemark") String gmRemark, @RequestParam("status") int status) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = feedHeaderRepo.updateFeedHeader(fbId, gmRemark, status);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage("update Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("no update");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Update Failed :EXC");

		}
		return errorMessage;
	}

	@RequestMapping(value = { "/updateFeedHeader1" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateFeedHeader1(@RequestParam("fbId") int fbId,
			@RequestParam("relManId") int relManId, @RequestParam("proFound") String proFound,
			@RequestParam("actionTaken") String actionTaken, @RequestParam("status") int status) {

		ErrorMessage errorMessage = new ErrorMessage();
		List<User> userList = new ArrayList<>();

		Customer cust = new Customer();

		try {
			int delete = feedHeaderRepo.updateFeedHeader1(fbId, relManId, proFound, actionTaken, status);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage("update Successfully");
				Notification res = new Notification();

				if (status == 1) {
					userList = userRepository.findTokenByUserTypeIdAndDelStatus(1);
					cust = customerRepository.getCustomerInfo(fbId);

					System.out.println("STATUS : 1----------------" + userList);

					for (int j = 0; j < userList.size(); j++) {
						Firebase.sendPushNotification(userList.get(j).getToken(), "GM Notification",
								"Following customer is unsatisfied from CRM\nCustomer Name : " + cust.getCustName()
										+ "\nVehicle No. : " + cust.getVehicleRegNo(),
								1);
						res.setDelStatus(1);
						res.setUserId(userList.get(j).getUserId());

						DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date dateobj = new Date();
						System.out.println(df.format(dateobj));
						res.setDate(df.format(dateobj));
						res.setDesc("Noti");
						res.setTitle("Notification");

						res = notiRepo.saveAndFlush(res);
						System.out.println("res1" + res);
					}
				}
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("no update");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Update Failed :EXC");

		}
		return errorMessage;
	}

	@RequestMapping(value = { "/deleteFeedDetail" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteFeedDetail(@RequestParam("fbDetailId") int fbDetailId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = feedDetailRepo.deleteFeedDetail(fbDetailId);

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

}
