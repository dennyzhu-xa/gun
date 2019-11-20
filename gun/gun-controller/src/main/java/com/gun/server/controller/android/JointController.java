package com.gun.server.controller.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.common.entity.pojo.EquipmentLogDTO;
import com.gun.common.entity.pojo.JointDTO;
import com.gun.common.entity.pojo.JointLogDTO;
import com.gun.service.JointService;

/**
 * Purpose:器材Controller
 * @author 
 *
 */
@RequestMapping("/Gui/Joint")
@Controller
public class JointController extends AbstractController {

	@Autowired
	public JointService jointService;
	
	@RequestMapping(value="/editJoint" ,method = RequestMethod.POST)
	public void editEquipInfo(JointDTO equipDTO){
		this.jointService.saveEquipModel(equipDTO);
		writeJson();
	}
	
	@RequestMapping(value="/queryList" ,method = RequestMethod.POST)
	public void queryList(String equipName){
		List<JointDTO> result = this.jointService.queryJointList(equipName);
		writeJson(result);
	}
	
	@RequestMapping(value="/queryByName" ,method = RequestMethod.POST)
	public void queryByName(String equipName){
		JointDTO result = this.jointService.queryByName(equipName);
		writeJson(result);
	}
	
	@RequestMapping(value="/getJoint" ,method = RequestMethod.POST)
	public void getEquip(String id){
		JointDTO equipDTO = this.jointService.getEquip(id);
		writeJson(equipDTO);
	}
	
	@RequestMapping(value="/delJoint" ,method = RequestMethod.POST)
	public void delEquip(String id){
		this.jointService.delEquip(id);
		writeJson();
	}
	
	@RequestMapping(value="/saveJointLog" ,method = RequestMethod.POST)
	public void saveEquipLog(JointLogDTO equipLogDTO){
		this.jointService.saveJointLog(equipLogDTO);
		writeJson();
	}
	
	@RequestMapping(value="/queryJointLog" ,method = RequestMethod.POST)
	public void queryEquipLogList(String equipId){
		List<JointLogDTO> result = this.jointService.queryList(equipId);
		writeJson(result);
	}
}
