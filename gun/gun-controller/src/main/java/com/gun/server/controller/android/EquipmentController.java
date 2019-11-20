package com.gun.server.controller.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.common.entity.pojo.EquipmentDTO;
import com.gun.common.entity.pojo.EquipmentLogDTO;
import com.gun.service.EquipmentService;

/**
 * Purpose:器材Controller
 * @author 
 *
 */
@RequestMapping("/Gui/Equip")
@Controller
public class EquipmentController extends AbstractController {

	@Autowired
	public EquipmentService equipmentService;
	
	@RequestMapping(value = "/listEquipModel", method = RequestMethod.POST)
	public void listEquipmentModel(){
		List<String> models = this.equipmentService.listEquipModel();
		writeJson(models);
	}
	
	@RequestMapping(value = "/listEquipByModel", method = RequestMethod.POST)
	public void listEquipmentByModel(String equipModel){
		List<EquipmentDTO> result = this.equipmentService.listEquipByModel(equipModel);
		writeJson(result);
	}
	
	
	@RequestMapping(value="/editEquip" ,method = RequestMethod.POST)
	public void editEquipInfo(EquipmentDTO equipDTO){
		this.equipmentService.saveEquipModel(equipDTO);
		writeJson();
	}
	
	@RequestMapping(value="/queryList" ,method = RequestMethod.POST)
	public void queryList(String equipName ,String equipModel){
		List<EquipmentDTO> result = this.equipmentService.queryList(equipName, equipModel);
		writeJson(result);
	}
	
	@RequestMapping(value="/getEquip" ,method = RequestMethod.POST)
	public void getEquip(String id){
		EquipmentDTO equipDTO = this.equipmentService.getEquip(id);
		writeJson(equipDTO);
	}
	
	@RequestMapping(value="/delEquip" ,method = RequestMethod.POST)
	public void delEquip(String id){
		this.equipmentService.delEquip(id);
		writeJson();
	}
	
	@RequestMapping(value="/saveEquipLog" ,method = RequestMethod.POST)
	public void saveEquipLog(EquipmentLogDTO equipLogDTO){
		this.equipmentService.saveEquipLog(equipLogDTO);
		writeJson();
	}
	
	@RequestMapping(value="/queryEquipLog" ,method = RequestMethod.POST)
	public void queryEquipLogList(String equipId){
		List<EquipmentLogDTO> result = this.equipmentService.queryList(equipId);
		writeJson(result);
	}
	
	@RequestMapping(value="/queryByNameArray" ,method = RequestMethod.POST)
	public void queryByNameArray(String equipName){
		List<EquipmentDTO> result = this.equipmentService.queryByNameArray(equipName);
		writeJson(result);
	}
}
