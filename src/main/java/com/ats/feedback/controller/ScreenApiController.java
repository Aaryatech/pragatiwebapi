package com.ats.feedback.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.feedback.model.master.QueDetail;
import com.ats.feedback.model.master.Question;
import com.ats.feedback.model.master.ScreenList;
import com.ats.feedback.repository.master.QueDetailRepo;
import com.ats.feedback.repository.master.QuestionRepo;
import com.ats.feedback.repository.master.ScreenListRepo;

@RestController
public class ScreenApiController {
	@Autowired
	ScreenListRepo screenListRepo;

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	QueDetailRepo queDetailRepo;
	

	@RequestMapping(value = { "/getScreenListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ScreenList> getScreenListByCompanyId(@RequestParam("companyId") int companyId) {

		List<ScreenList> screenList = new ArrayList<>();
		List<Question> questionHeaderList = new ArrayList<>();
		try {
			screenList = screenListRepo.getScListAllCompanyId(companyId);

			System.out.println("screenList" + screenList.toString());
			for (int i = 0; i < screenList.size(); i++) {

				String sampleString = screenList.get(i).getQueNo();

				System.out.println("sampleString" + sampleString);

				List<String> dataList = Arrays.asList(sampleString.split(","));

				System.out.println("dataList" + dataList);

				questionHeaderList = questionRepo.getQuestionScreenList(dataList);
				System.out.println("questionHeaderList" + questionHeaderList.toString());

				for (int j = 0; j < questionHeaderList.size(); j++) {
					List<QueDetail> queDetailList = queDetailRepo.findByQueNo(questionHeaderList.get(j).getQueNo());
					questionHeaderList.get(j).setQueDetailList(queDetailList);
				}

				screenList.get(i).setQueList(questionHeaderList);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return screenList;

	}

}
