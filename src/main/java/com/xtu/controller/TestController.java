package com.xtu.controller;

import com.xtu.DB.ContestProblemsRepository;
import com.xtu.DB.ContestRepository;
import com.xtu.DB.ProblemsRepository;
import com.xtu.DB.UsersRepository;
import com.xtu.DB.dto.ContestDTO;
import com.xtu.DB.dto.CreateTestDTO;
import com.xtu.DB.dto.ProblemsDTO;
import com.xtu.DB.entity.ContestProblemsEntity;
import com.xtu.DB.entity.ContestsEntity;
import com.xtu.DB.entity.ProblemsEntity;
import com.xtu.DB.entity.UsersEntity;
import com.xtu.DB.vo.AllContestProblemVO;
import com.xtu.DB.vo.AllContestVO;
import com.xtu.DB.vo.ProblemsEntityVO;
import com.xtu.constant.Pages;
import com.xtu.tools.DateUtil;
import com.xtu.tools.OUT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;

/**
 * Created by Ilovezilian on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/" + Pages.TEST)
public class TestController {
    @Autowired
    ContestRepository contestRepository;
    @Autowired
    ContestProblemsRepository contestProblemsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProblemsRepository problemsRepository;

    @RequestMapping(value = "/" + Pages.TEST_SUBMIT + "/{id}", method = RequestMethod.GET)
    public String submit(
            @PathVariable("id") @NotNull int id,
            @RequestParam("contestId") int contestId,
            @RequestParam("no") int no,
            Model model) {
        OUT.prt("requst", Pages.TEST_SUBMIT);
        ProblemsEntityVO entity = new ProblemsEntityVO();
        entity.setProblemId(id);
        model.addAttribute("id", id);
        model.addAttribute("contestId", contestId);
        model.addAttribute("no", no);
        OUT.prt("entity", entity);
        String res = Pages.TEST + "/" + Pages.TEST_SUBMIT;
        return res;
    }

    @RequestMapping(value = {"/" + Pages.TEST_PROBLEM_DETAIL + "/{id}"}, method = RequestMethod.GET)
    public String showTestProblemDetails(
            @PathVariable("id") int id,
            @RequestParam("contestId") int contestId,
            @RequestParam("no") int no,
            Model model) {
        OUT.prt("request", Pages.TEST_PROBLEM_DETAIL);

        ProblemsDTO dto = new ProblemsDTO();
        dto.setProblemId(id);
        dto.setTitle("^_^");
        ProblemsEntity entity = problemsRepository.findOne(dto);
        String[] contexts = entity.getContext().split("\\{\\{\\{\\(>_<\\)\\}\\}\\}");
        entity.setProblemDes(contexts[0]);
        entity.setInputDes(contexts[1]);
        entity.setOutputDes(contexts[2]);
        entity.setSampleInput(contexts[3]);
        entity.setSampleOutput(contexts[4]);
        model.addAttribute("entity", entity);
        OUT.prt("entity", entity);
        String res = Pages.TEST + "/" + Pages.TEST_PROBLEM_DETAIL;
        return res;
    }

    @RequestMapping(value = {"/" + Pages.TEST, "/" + Pages.TEST + "/{start}"}, method = RequestMethod.GET)
    public String showTest(
            @PathVariable("start") int start,
            Model model,
            Principal principal) {
        OUT.prt("request", Pages.TEST);
        String id = principal.getName();
        UsersEntity usersEntity = usersRepository.findOne(id);
        AllContestVO vo = contestRepository.queryAllContestPages(start, usersEntity.getUserId());
        model.addAttribute("vo", vo);
        OUT.prt("vo", vo);
        String res = Pages.TEST + "/" + Pages.TEST;
        return res;
    }

    @RequestMapping(value = {"/" + Pages.TEST_DETAIL, "/" + Pages.TEST_DETAIL + "/{id}"}, method = RequestMethod.GET)
    public String showTestDetail(
            @PathVariable("id") Integer contestId,
            Model model) {
        OUT.prt("request", Pages.TEST_DETAIL);
        AllContestProblemVO vo = contestProblemsRepository.findList(contestId);
        model.addAttribute("vo", vo);
        OUT.prt("vo", vo);
        String res = Pages.TEST + "/" + Pages.TEST_DETAIL;
        return res;
    }

    @RequestMapping(value = {"/" + Pages.TOTAL_TEST, "/" + Pages.TOTAL_TEST + "/{start}"}, method = RequestMethod.GET)
    public String showTotalTest(
            @PathVariable("start") int start,
            ContestDTO contestDTO,
            Model model,
            Principal principal) {
        OUT.prt("request", Pages.TOTAL_TEST);
        OUT.prt("contestDTO", contestDTO);
        contestDTO.setId(principal.getName());
        AllContestVO vo = contestRepository.queryContestPages(start, contestDTO);
        model.addAttribute("vo", vo);
        OUT.prt("vo", vo);
        String res = Pages.TEST + "/" + Pages.TOTAL_TEST;
        return res;
    }

    @RequestMapping(value = "/" + Pages.CREATE_TEST, method = RequestMethod.GET)
    public String createTest() {
        OUT.prt("request", Pages.CREATE_TEST);
        String res = Pages.TEST + "/" + Pages.CREATE_TEST;
        return res;
    }

    @RequestMapping(value = {Pages.MODIFY_TEST, "/" + Pages.MODIFY_TEST + "/{id}"}, method = RequestMethod.GET)
    public String modifyTest(
            @PathVariable("id") int id) {
        OUT.prt("request", Pages.MODIFY_TEST);
        String res = "/" + Pages.TEST + "/" + Pages.MODIFY_TEST;
        return res;
    }

    @Transactional
    @RequestMapping(
            value = {"/" + Pages.CREATE_TEST, "/" + Pages.MODIFY_TEST, "/" + Pages.MODIFY_TEST + "/{id}"},
            method = RequestMethod.POST)
    public String createTestPost(
            CreateTestDTO createTestDTO,
            Model model,
            Principal principal) {
        OUT.prt("post", Pages.CREATE_TEST);
        OUT.prt("post", createTestDTO);
//        createTestDTO.setStartTime(new Timestamp(createTestDTO.getStartTimeStr()));
//        createTestDTO.setFrozenStartTime(new Timestamp(createTestDTO.getFrozenStartTimeStr()));
//        createTestDTO.setEndTime(new Timestamp(createTestDTO.getEndTimeStr()));
        createTestDTO.setStartTime(DateUtil.getTimestamp(createTestDTO.getStartTimeStr()));
        createTestDTO.setFrozenStartTime(DateUtil.getTimestamp(createTestDTO.getFrozenStartTimeStr()));
        createTestDTO.setEndTime(DateUtil.getTimestamp(createTestDTO.getEndTimeStr()));
        ContestsEntity contestsEntity = new ContestsEntity();
        BeanUtils.copyProperties(createTestDTO, contestsEntity);

        /**
         * 获取user_id
         */
        String id = principal.getName();
        UsersEntity usersEntity = usersRepository.findOne(id);
        contestsEntity.setOwner(usersEntity.getUserId());

        contestRepository.save(contestsEntity);
        ContestProblemsEntity entity = new ContestProblemsEntity();
        int contestId = contestsEntity.getContestId();
        entity.setContestId(contestsEntity.getContestId());

        String problems = createTestDTO.getProblemList();
        contestProblemsRepository.deleteNoToMax(contestId);
        for (String s : problems.split("QAQ")) {
            String[] ss = s.split("TAT");
            entity.setNo(Byte.parseByte(ss[0]));
            entity.setProblemId(Integer.parseInt(ss[1]));
            entity.setScore(Byte.parseByte(ss[2]));
            contestProblemsRepository.insert(entity);
        }

        String res = Pages.TEST + "/" + Pages.TOTAL_TEST;
        return res;
    }

    @RequestMapping(value = "/" + Pages.JOIN_TEST, method = RequestMethod.GET)
    public String joinTest(
            Model model) {
        OUT.prt("request", Pages.JOIN_TEST);
        String res = Pages.TEST + "/" + Pages.JOIN_TEST;
        return res;
    }
}
