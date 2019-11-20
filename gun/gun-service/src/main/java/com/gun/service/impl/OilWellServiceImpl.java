package com.gun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.databinding.types.soapencoding.DateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import antlr.StringUtils;

import com.cyber.lottery.dao.OilWellDAO;
import com.cyber.lottery.dao.WellLevelDAO;
import com.gun.common.entity.OilWell;
import com.gun.common.entity.WellLevel;
import com.gun.common.entity.pojo.ArrGunFormDTO;
import com.gun.common.entity.pojo.OilLevelDTO;
import com.gun.common.entity.pojo.OilWellDTO;
import com.gun.common.entity.pojo.WellLevelDTO;
import com.gun.common.exception.ConvertException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.service.OilWellService;
@Service
public class OilWellServiceImpl implements OilWellService {

	private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	private static final Log log = LogFactory.getLog(OilWellServiceImpl.class);
	
	private static final String WELL_TYPE_TCP = "TCP";
	private static final String WELL_TYPE_CCP = "CCP";
	
	@Autowired
	private WellLevelDAO wellLevelDAO;
	
	@Autowired
	private OilWellDAO oilWellDAO;
	
	/**
	 * 储存
	 */
	public void saveOilWell(ArrGunFormDTO formDTO){
		OilWell oilWell = new OilWell();
		oilWell.setWellType(WELL_TYPE_TCP);
		oilWell.setOwnName(formDTO.getFirstParty());
		oilWell.setOilNo(formDTO.getWellNo());
		oilWell.setArea(formDTO.getArea());
		oilWell.setManualDeep(formDTO.getManualWell());
//		oilWell.setSurfaceTemperature(surfaceTemperature);
//		oilWell.setOilTemperature(oilTemperature);
		oilWell.setEquipmentModelId(formDTO.getGunModel());
		oilWell.setEquipments(arrayToString(formDTO.getGun()));
		oilWell.setSpacerModelId(formDTO.getSpaceModel());
		oilWell.setSpacers(arrayToString(formDTO.getSpaceGun()));
		oilWell.setPerforatingId(formDTO.getMagazine());
		oilWell.setConnectId(formDTO.getConnector());
		oilWell.setPhase(formDTO.getPhase());
		oilWell.setExtraLength(formDTO.getExtraLength());
		oilWell.setUse3M(formDTO.isUseSafety() ? 'Y' : 'N');
		if(formDTO.isUseSafety()){
			oilWell.setSafetyLength(formDTO.getSafeLength());
		}
		oilWell.setTechRemark(formDTO.getRemark());
		if(formDTO.getWellId() != null && formDTO.getWellId() > 0){
			oilWell.setId(formDTO.getWellId());
			oilWell.setUpdatedById("");
			oilWell.setUpdatedByName("");
			oilWell.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		} else {
			oilWell.setCreateById("");
			oilWell.setCreatedByName("");
			oilWell.setCreatedDate(DateTimeUtils.getCurrentTimestamp());
			oilWell.setUpdatedById("");
			oilWell.setUpdatedByName("");
			oilWell.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		}
		oilWell = this.oilWellDAO.saveOrUpdate(oilWell);
		// 储存就是先删后增
		this.wellLevelDAO.deleteByWellId(oilWell.getId());
		List<OilLevelDTO> oilLevelList = formDTO.getOilLevelDTOList();
		if(oilLevelList != null && oilLevelList.size() > 0){
			oilWell.setLevelQuanlity(oilLevelList.size());
			OilLevelDTO oilLevelDTO = null;
			WellLevel wellLevel = null;
			for(int i=0;i<oilLevelList.size();i++){
				oilLevelDTO = oilLevelList.get(i);
				wellLevel = new WellLevel();
				wellLevel.setWellId(oilWell.getId());
				wellLevel.setOilStart(oilLevelDTO.getStartLevel());
				wellLevel.setOilEnd(oilLevelDTO.getEndLevel());
				wellLevel.setLevelDepth(oilLevelDTO.getDeep());
				wellLevel.setBackinDepth(oilLevelDTO.getBackinDepth());
				wellLevel.setDeepSum(oilLevelDTO.getDeepSum());
				wellLevel.setBackfinSum(oilLevelDTO.getBackfinSum());
				this.wellLevelDAO.save(wellLevel);
			}
		}
	}
	
	public List<ArrGunFormDTO> listOilWellDTOByUser(String userId){
		List<OilWellDTO> result = this.oilWellDAO.listOilWellDTO(userId);
		List<ArrGunFormDTO> formDTOList = null;
		if(!CollectionUtils.isEmpty(result)){
			OilWellDTO oilWellDTO = null;
			ArrGunFormDTO formDTO = null;
			formDTOList = new ArrayList();
			for(int i=0;i<result.size();i++){
				oilWellDTO = result.get(i);
				formDTO = this.transToArrGunFormDTO(oilWellDTO, null);
				formDTOList.add(formDTO);
			}
		}
		return formDTOList;
	}
	
	// 透过井号与异动时间查询资料
	public ArrGunFormDTO getArrGunForm(String wellNo,DateTime updateTime){
		ArrGunFormDTO result = null;
		OilWellDTO oilWellDTO = this.oilWellDAO.getOilWellDTO(wellNo, updateTime);
		List<WellLevelDTO> wellLevelList = this.wellLevelDAO.listByWellId(oilWellDTO.getId());
		result = this.transToArrGunFormDTO(oilWellDTO, wellLevelList);
		return result;
	}
	
	public ArrGunFormDTO getArrGunFormById(String id){
		if(id == null || id.equals("") || id.equals("0")) return null;
		OilWell oilWell = this.oilWellDAO.findById(OilWell.class, Integer.valueOf(id));
		List<WellLevelDTO> wellLevelList = this.wellLevelDAO.listByWellId(oilWell.getId());
		OilWellDTO oilWellDTO = null;
		try {
			oilWellDTO = (OilWellDTO) transformer.transform(oilWell, new OilWellDTO());
		} catch (ConvertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrGunFormDTO result = this.transToArrGunFormDTO(oilWellDTO, wellLevelList);
		return result;
	}
	
	// 封装ArrGunFormDTO
	private ArrGunFormDTO transToArrGunFormDTO(OilWellDTO oilDTO,List<WellLevelDTO> wellLevelList){
		if(oilDTO == null) return null;
		ArrGunFormDTO formDTO = new ArrGunFormDTO();
		formDTO.setWellId(oilDTO.getId());
		formDTO.setArea(oilDTO.getArea());
		formDTO.setConnector(oilDTO.getConnectId());
		formDTO.setExtraLength(oilDTO.getExtraLength());
		formDTO.setFirstParty(oilDTO.getOwnName());
		formDTO.setGunModel(oilDTO.getEquipmentModelId());
		formDTO.setGun(oilDTO.getEquipments() == null || oilDTO.getEquipments().length() <= 0 ? null : oilDTO.getEquipments().split(","));
		formDTO.setMagazine(oilDTO.getPerforatingId());
		formDTO.setManualWell(oilDTO.getManualDeep());
		formDTO.setPhase(oilDTO.getPhase());
		formDTO.setRemark(oilDTO.getTechRemark());
		formDTO.setSafeLength(oilDTO.getSafetyLength());
		formDTO.setSpaceModel(oilDTO.getSpacerModelId());
		formDTO.setSpaceGun(oilDTO.getSpacers() ==null || oilDTO.getSpacers().length() <=0 ? null : oilDTO.getSpacers().split(","));
		formDTO.setUseSafety(oilDTO.getUse3M() == 'Y' ? true :false);
		formDTO.setWellNo(oilDTO.getOilNo());
		if(wellLevelList != null && wellLevelList.size() > 0){
			List<OilLevelDTO> oilLevelDTOList = new ArrayList<OilLevelDTO>();
			OilLevelDTO oilLevelDTO = null;
			WellLevelDTO wellLevelDTO = null;
			for(int i=0;i<wellLevelList.size();i++){
				wellLevelDTO = wellLevelList.get(i);
				oilLevelDTO = new OilLevelDTO();
				oilLevelDTO.setType(oilLevelDTO.TYPE_OIL);
				oilLevelDTO.setStartLevel(wellLevelDTO.getOilStart());
				oilLevelDTO.setEndLevel(wellLevelDTO.getOilEnd());
				oilLevelDTO.setDeep(wellLevelDTO.getLevelDepth());
				oilLevelDTO.setBackinDepth(wellLevelDTO.getBackinDepth());
				oilLevelDTO.setDeepSum(wellLevelDTO.getDeepSum());
				oilLevelDTO.setBackfinSum(wellLevelDTO.getBackfinSum());
				oilLevelDTOList.add(oilLevelDTO);
			}
			formDTO.setOilLevelDTOList(oilLevelDTOList);
		}
		return formDTO;
	}
	
	private String arrayToString(String[] value){
		if(value != null && value.length > 0){
			String str = "";
			for(int i=0;i<value.length;i++){
				str += value[i];
				if(i< value.length -1 ){
					str += ",";
				}
			}
			return str;
		}
		return "";
	}

	@Override
	public Model init(Object request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
