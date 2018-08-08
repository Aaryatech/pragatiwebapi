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

import com.ats.feedback.model.master.ErrorMessage;
import com.ats.feedback.model.transaction.FeedDetail;
import com.ats.feedback.model.transaction.FeedHeader;
import com.ats.feedback.repository.master.FeedDetailRepo;
import com.ats.feedback.repository.master.FeedHeaderRepo;

@RestController
public class FeedbackController {

	@Autowired
	FeedHeaderRepo feedHeaderRepo;

	@Autowired
	FeedDetailRepo feedDetailRepo;

	@RequestMapping(value = { "/saveFeedbackHeaderDetail" }, method = RequestMethod.POST)
	public @ResponseBody FeedHeader saveFeedbackHeaderDetail(@RequestBody FeedHeader feedHeader) {

		FeedHeader feedHeaderRes = new FeedHeader();

		try {

			System.out.println(feedHeaderRes);

			feedHeaderRes = feedHeaderRepo.saveAndFlush(feedHeader);

			for (int i = 0; i < feedHeader.getFeedDetailList().size(); i++)
				feedHeader.getFeedDetailList().get(i).setFbId(feedHeaderRes.getFbId());

			List<FeedDetail> feedDetailList = feedDetailRepo.saveAll(feedHeaderRes.getFeedDetailList());
			System.out.println("feedDetailList" + feedDetailList.toString());
			feedHeaderRes.setFeedDetailList(feedDetailList);
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
	public @ResponseBody List<FeedHeader> getFeedbackByCompanyIdAndStatus(@RequestParam("companyId") int companyId,
			@RequestParam("status") int status) {

		List<FeedHeader> feedHeaderList = new ArrayList<>();

		try {

			feedHeaderList = feedHeaderRepo.getFeedHeaderByCompanyId(companyId, status);
			for (int i = 0; i < feedHeaderList.size(); i++) {

				List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(feedHeaderList.get(i).getFbId());

				feedHeaderList.get(i).setFeedDetailList(feedDetailList);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return feedHeaderList;

	}

	@RequestMapping(value = { "/getFeedbackList" }, method = RequestMethod.POST)
	public @ResponseBody List<FeedHeader> getFeedbackList(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("saId") int saId, @RequestParam("status") int status) {

		List<FeedHeader> feedHeaderList = new ArrayList<>();

		try {

			if (companyId != 0 && saId != 0 && status != 0) {

				feedHeaderList = feedHeaderRepo.getFeedbackList(companyId, fromDate, toDate, saId, status);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setFeedDetailList(feedDetailList);
				}
			} else if (companyId != 0 && saId != 0 && status == 0) {

				feedHeaderList = feedHeaderRepo.getFeedbackListAllStatus(companyId, fromDate, toDate, saId);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setFeedDetailList(feedDetailList);
				}

			}

			else if (companyId != 0 && saId == 0 && status == 0) {

				feedHeaderList = feedHeaderRepo.getFeedbackListAllStatusAndSaId(companyId, fromDate, toDate);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setFeedDetailList(feedDetailList);
				}

			}

			else if (companyId != 0 && saId == 0 && status != 0) {

				feedHeaderList = feedHeaderRepo.getFeedbackListAllSaId(companyId, fromDate, toDate, status);
				for (int i = 0; i < feedHeaderList.size(); i++) {

					List<FeedDetail> feedDetailList = feedDetailRepo.findByFbId(feedHeaderList.get(i).getFbId());

					feedHeaderList.get(i).setFeedDetailList(feedDetailList);
				}

			}
		} catch (Exception e) {

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
