package com.gun.server.controller.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.common.entity.pojo.ArrGunFormDTO;
import com.gun.service.OilWellService;

/**
 * Purpose:排枪Controller
 * @author 
 *
 */
@RequestMapping("/Gui/Well")
@Controller
public class OilWellController extends AbstractController {

	@Autowired
	private OilWellService oilWellService;
	
	@RequestMapping(value="/saveWell" ,method = RequestMethod.POST)
	public void saveOilWell(@RequestBody(required=true)ArrGunFormDTO formDTO){
		this.oilWellService.saveOilWell(formDTO);
		writeJson();
	}
	
	@RequestMapping(value="/listWell" ,method = RequestMethod.POST)
	public void listOilWell(String user){
		List<ArrGunFormDTO> results = this.oilWellService.listOilWellDTOByUser(user);
		writeJson(results);
	}
	
	@RequestMapping(value="/getOilWellById",method=RequestMethod.POST)
	public void getOilWell(String id){
		ArrGunFormDTO result = this.oilWellService.getArrGunFormById(id);
		writeJson(result);
	}
}
