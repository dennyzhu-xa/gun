package com.gun.server.controller.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.common.entity.pojo.MagazineLogDTO;
import com.gun.common.entity.pojo.SpacerDTO;
import com.gun.common.entity.pojo.SpacerLogDTO;
import com.gun.service.SpacerService;

/**
 * Purpose:器材Controller
 * @author 
 *
 */
@RequestMapping("/Gui/Spc")
@Controller
public class SpacerController extends AbstractController {

	@Autowired
	public SpacerService equipmentService;
	
	@RequestMapping(value = "/listEquipModel", method = RequestMethod.POST)
	public void listEquipmentModel(){
		List<String> models = this.equipmentService.listEquipModel();
		writeJson(models);
	}
	
	@RequestMapping(value="/editEquip" ,method = RequestMethod.POST)
	public void editEquipInfo(SpacerDTO equipDTO){
		this.equipmentService.saveEquipModel(equipDTO);
		writeJson();
	}
	
	@RequestMapping(value="/queryList" ,method = RequestMethod.POST)
	public void queryList(String equipName ,String equipModel){
		List<SpacerDTO> result = this.equipmentService.queryList(equipName, equipModel);
		writeJson(result);
	}
	
	@RequestMapping(value="/getEquip" ,method = RequestMethod.POST)
	public void getEquip(String id){
		SpacerDTO equipDTO = this.equipmentService.getEquip(id);
		writeJson(equipDTO);
	}
	
	@RequestMapping(value="/delEquip" ,method = RequestMethod.POST)
	public void delEquip(String id){
		this.equipmentService.delEquip(id);
		writeJson();
	}
	
	@RequestMapping(value="/saveEquipLog" ,method = RequestMethod.POST)
	public void saveEquipLog(SpacerLogDTO equipLogDTO){
		this.equipmentService.saveEquipLog(equipLogDTO);
		writeJson();
	}
	
	@RequestMapping(value="/queryEquipLog" ,method = RequestMethod.POST)
	public void queryEquipLogList(String equipId){
		List<SpacerLogDTO> result = this.equipmentService.queryList(equipId);
		writeJson(result);
	}
}
