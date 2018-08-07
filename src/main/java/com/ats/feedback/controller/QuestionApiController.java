package com.ats.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.feedback.model.master.ErrorMessage;
import com.ats.feedback.model.master.QueDetail;
import com.ats.feedback.model.master.Question;
import com.ats.feedback.repository.master.QueDetailRepo;
import com.ats.feedback.repository.master.QuestionRepo;

@RestController
public class QuestionApiController {

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	QueDetailRepo queDetailRepo;

	@RequestMapping(value = { "/saveQuestionHeaderDetail" }, method = RequestMethod.POST)
	public @ResponseBody Question saveQuestionHeaderDetail(@RequestBody Question questionHeader) {

		Question queHeaderRes = new Question();

		try {

			System.out.println(queHeaderRes);

			queHeaderRes = questionRepo.saveAndFlush(questionHeader);

			for (int i = 0; i < questionHeader.getQueDetailList().size(); i++)
				questionHeader.getQueDetailList().get(i).setQueNo((queHeaderRes.getQueNo()));

			List<QueDetail> queDetailList = queDetailRepo.saveAll(questionHeader.getQueDetailList());
			System.out.println("queDetailList" + queDetailList.toString());
			queHeaderRes.setQueDetailList(queDetailList);
		} catch (Exception e) {

			e.printStackTrace();

		}
		return queHeaderRes;

	}

	@RequestMapping(value = { "/getQuestionHeaderAndDetail" }, method = RequestMethod.POST)
	public @ResponseBody Question getQuestionHeaderAndDetail(@RequestParam("queNo") int queNo) {

		Question questionHeader = new Question();

		try {

			questionHeader = questionRepo.findByQueNo(queNo);
			List<QueDetail> queDetailList = queDetailRepo.findByQueNo(queNo);
			questionHeader.setQueDetailList(queDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return questionHeader;

	}

	@RequestMapping(value = { "/deleteQuestion" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteQuestion(@RequestParam("queNo") int queNo) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = questionRepo.deleteQue(queNo);

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
