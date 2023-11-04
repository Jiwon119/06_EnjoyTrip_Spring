package com.ssafy.attraction.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.service.AttractionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
@Api(tags = {"Attraction Controller  API V1"})
public class AttractionController {

	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>전체 목록</big>을 반환해 줍니다.")
	@PostMapping("/mapList")
	public ResponseEntity<?> list(@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword) {

		if (keyword != null && !keyword.isEmpty())
			System.out.println("keyword is" + keyword);
		
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		if (area != null)
			attractionInfoDto.setSidoCode(Integer.parseInt(area));
		if (type != null)
			attractionInfoDto.setContentTypeId(Integer.parseInt(type));
		
		List<AttractionInfoDto> list = attractionService.attractionList(attractionInfoDto, keyword);

		return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>검색 결과</big>을 반환해 줍니다.")
	@PostMapping("/mapSearch")
	public ResponseEntity<?> search(@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword) {

		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		if(area != null) attractionInfoDto.setSidoCode(Integer.parseInt(area));
		if(type != null) attractionInfoDto.setContentTypeId(Integer.parseInt(type));
		List<AttractionInfoDto> list = attractionService.attractionList(attractionInfoDto, keyword);

		return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
	}
	
//	@PostMapping("/mapSearch")
//	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String area = req.getParameter("area");
//		String type = req.getParameter("type");
//		String keyword = req.getParameter("keyword");
//		if (keyword != null && !keyword.isEmpty())
//			System.out.println("keyword is" + keyword);
//		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
//		if (area != null)
//			attractionInfoDto.setSidoCode(Integer.parseInt(area));
//		if (type != null)
//			attractionInfoDto.setContentTypeId(Integer.parseInt(type));
//		List<AttractionInfoDto> list = attractionService.attractionList(attractionInfoDto, keyword);
//		req.setAttribute("result", list);
//		req.getRequestDispatcher("/map/map.jsp").forward(req, resp);
//	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String action = req.getParameter("action");
//		search(req, resp);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
//		String action = req.getParameter("action");
//		System.out.print(action);
//		
//		switch(action) {
//		case "mapSearch":
//			search(req, resp);
//			break;
//		}
//	}
//	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String area = req.getParameter("area");
//		String type = req.getParameter("type");
//		String keyword = req.getParameter("keyword");
//		if(keyword != null && !keyword.isEmpty()) System.out.println("keyword is" + keyword );
//		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
//		if(area != null) attractionInfoDto.setSidoCode(Integer.parseInt(area));
//		if(type != null) attractionInfoDto.setContentTypeId(Integer.parseInt(type));
//		List<AttractionInfoDto> list = attractionService.attractionList(attractionInfoDto, keyword);
//		req.setAttribute("result", list);
//		req.getRequestDispatcher("/map/map.jsp").forward(req, resp);
//	}
	
}
