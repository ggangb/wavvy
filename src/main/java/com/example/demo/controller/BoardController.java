package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BoardService;

import vo.BoardVO;
import vo.ContentVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/login_check")
	public String logincheck() {
		return "page/login_check";
	}
	
	
	@RequestMapping(value = {"/board" , "/board_like","/board_normal","/searchBoard"}, method=RequestMethod.GET)
	public ModelAndView board(@RequestParam(defaultValue="1")int page, HttpServletRequest request, String keyword) {
		ModelAndView mv = new ModelAndView();
		if(request.getServletPath().equals("/board")) {
			mv.addObject("boardPage", service.makeBoardPage(page));
			mv.setViewName("page/board_list");
		} else if(request.getServletPath().equals("/board_like")) {
			mv.addObject("boardPage", service.makeBoardPageL(page));
			mv.setViewName("page/board_list");
		} else if(request.getServletPath().equals("/board_normal")) {
			mv.addObject("boardPage", service.makeBoardPageNormal(page));
			mv.setViewName("page/board_list");
			
		} else if(request.getServletPath().equals("/searchBoard")) {
			mv.addObject("boardPage", service.searchBoardPage(page, keyword));
			mv.setViewName("page/board_list");
		}
		return mv;
	}
	
	@RequestMapping(value = {"/moreContent"}, method=RequestMethod.GET)
	public ModelAndView moreContent(@RequestParam(defaultValue="1")int page,String contentCategory) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("moreContent", service.morePage(page, contentCategory));
		mv.setViewName("page/content_list");
		
		return mv;
	}
	
	@RequestMapping(value = {"/contentList"}, method=RequestMethod.GET)
	public ModelAndView content(@RequestParam(defaultValue="1")int page,String contentTitle) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("contentList", service.contentList(page, contentTitle));
		mv.setViewName("page/read");
		
		return mv;
	}
	
	
	@RequestMapping(value = {"/"}, method=RequestMethod.GET)
	public ModelAndView mainFunc(@RequestParam(defaultValue="1")int page) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mainContent", service.mainPage(page));
		mv.setViewName("page/main");
		
		return mv;
	}
	
//	@RequestMapping(value = "/board_like" , method=RequestMethod.GET)
//	public ModelAndView boardLike(@RequestParam(defaultValue="1")int page) {
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("boardPage", service.makeBoardPageL(page));
//		mv.setViewName("page/board_list_like");
//		
//		return mv;
//	}
//	
//	@RequestMapping(value = "/board_normal" , method=RequestMethod.GET)
//	public ModelAndView boardNormal(@RequestParam(defaultValue="1")int page) {
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("boardPage", service.makeBoardPageNormal(page));
//		mv.setViewName("page/board_list_normal");
//		
//		return mv;
//	}
	
	@RequestMapping("/writeForm")
	public String writeForm(HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		if(loginId != null && loginId.length()>0) {
			return "page/board_write";
		} else {
			return "page/main";
		}
	}
	
	@RequestMapping("/contentWriteForm")
	public String contentWriteForm(HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		if(loginId.equals("admin") && loginId.length()>0) {
			return "page/admin_write";
		} else {
			return "page/main";
		}
	}
	
	/*
	 * @RequestMapping("/contentList") public String contentList() { return
	 * "page/content_list"; }
	 */
	
//	@RequestMapping("/readContent")
//	public String boardRead() {
//		return "page/read";
//	}
	
	@RequestMapping("read")
	public ModelAndView read(int boardNum , HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		BoardVO board = service.read(boardNum, loginId);
		BoardVO prevBoard = service.prevBoard(boardNum, loginId);
		BoardVO nextBoard = service.nextBoard(boardNum, loginId);
		if(prevBoard != null) {
			mv.addObject("prevBoard", prevBoard);
		} else {
			mv.addObject("prevBoard", new BoardVO());
		}
		if(nextBoard != null) {
			mv.addObject("nextBoard", nextBoard);
		} else {
			mv.addObject("nextBoard", new BoardVO());
		}
		mv.addObject("board", board);
		mv.setViewName("page/board_read");
		return mv;
	}
	@RequestMapping("contentRead")
	public ModelAndView readContent(@RequestParam(defaultValue="1")int page,int contentEp , String contentTitle, HttpSession session, int contentNum) {
		ModelAndView mv = new ModelAndView();
		String loginId = (String)session.getAttribute("loginId");
		ContentVO content = service.contentRead(contentEp, contentTitle, loginId, contentNum);
		mv.addObject("contentList", service.contentList(page, contentTitle));
		mv.addObject("content", content);
		mv.setViewName("page/read");
		return mv;
	}
	
	
	
	@RequestMapping(value = "/write" , method = RequestMethod.POST)
	public String write(BoardVO board, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		if(loginId != null && loginId.length()>0) {
			service.write(board, loginId);
			return "page/write_result";
		} else {
			return "page/board_list";
		}
		
	}
	
	
	@RequestMapping(value = "/writeContent", method = RequestMethod.POST)
	public String writeContent(ContentVO content, HttpSession session, MultipartFile imgFile,MultipartFile videoFile) throws Exception {
		String loginId = (String)session.getAttribute("loginId");
		if(loginId != null && loginId.length()>0) {
			service.writeContent(content, loginId, imgFile, videoFile);
			System.out.println("["+content.getContentTitle()+"]"+" 컨텐츠 추가 완료");
			return "page/writeContent_result";
		} else {
			System.out.println("컨텐츠 추가 실패");
			return "page/main";
		}
	}
	
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(int boardNum) {
		
		BoardVO board = service.readNoCount(boardNum);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("board", board);
		mv.setViewName("page/update_form");
		return mv;
	}
	
	@RequestMapping("/updateContentForm")
	public ModelAndView updateContentForm(int contentEp, String contentTitle) {
		
		ContentVO content = service.readContent(contentEp, contentTitle);
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", content);
		mv.setViewName("page/update_content_form");
		return mv;
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public ModelAndView update(BoardVO board, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = service.update(board, loginId);
		ModelAndView mv = new ModelAndView();
		
		if(result) {
			mv.addObject("boardNum", board.getBoardNum());
			mv.setViewName("page/update_success");
		} else {
			mv.setViewName("page/update_fail");
		}
		return mv;
		
	}
	
	@RequestMapping(value = "/updateContentFile" , method = RequestMethod.POST)
	public ModelAndView updateContentFile(int contentNum,int contentEp, String fileName, HttpSession session, String contentTitleName) {
		String loginId = (String)session.getAttribute("loginId");
		System.out.println(loginId);
		System.out.println(fileName);
		boolean result = service.updateContentFile(fileName, contentNum, loginId);
		System.out.println(result);
		ContentVO content = service.readContent(contentEp, contentTitleName);
		ModelAndView mv = new ModelAndView();
		if(result) {
			mv.addObject("content", content);
			mv.setViewName("page/update_content_form");
		} else {
			mv.setViewName("page/update_content_fail");
		}
		return mv;
		
	}
	@RequestMapping(value = "/updateContent", method = RequestMethod.POST)
	public ModelAndView updateContent(ContentVO content, HttpSession session, MultipartFile imgFile,MultipartFile videoFile) throws Exception{
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = service.updateContent(content, loginId, imgFile, videoFile);
		ModelAndView mv = new ModelAndView();
		
		if(result) {
			mv.addObject("contentNum", content.getContentNum());
			mv.setViewName("page/update_content_success");
		} else {
			mv.setViewName("page/update_content_fail");
		}
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(int boardNum, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = service.delete(boardNum, loginId);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("page/delete_result");
		return mv;
	}
	
	@RequestMapping("/deleteContent")
	public ModelAndView deleteContent(int contentNum, HttpSession session, String videoName, String imgName) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = service.deleteContent(contentNum, loginId, videoName, imgName);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("page/delete_content_result");
		return mv;
	}
	
	@RequestMapping("/searchContent")
	public ModelAndView searchContent(String keyword) {
		ModelAndView mv = new ModelAndView();
		

		mv.addObject("content", service.searchContent(keyword));
		mv.setViewName("page/search_content_list");
		
		return mv;
	}
	
	@GetMapping("/download")
	public ResponseEntity<Object> download(String fileName) {
		String path =  System.getProperty("user.dir") + "/files/"+ fileName;
		
		try {
			Path filePath = Paths.get(path);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
}
