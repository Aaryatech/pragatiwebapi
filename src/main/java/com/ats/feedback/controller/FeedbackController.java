package com.ats.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.feedback.model.master.QueDetail;
import com.ats.feedback.model.master.Question;
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

}
