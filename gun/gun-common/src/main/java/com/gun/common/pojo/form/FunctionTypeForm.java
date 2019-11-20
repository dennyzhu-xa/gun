package com.gun.common.pojo.form;

import java.util.List;

import com.gun.common.entity.pojo.FunctionTypeDTO;

/**
 * @author felixli
 *
 */
public class FunctionTypeForm extends SystemForm{

  /**
   * 
   */
  private static final long serialVersionUID = -6562960794252459017L;
  public static final String FORM_NAME = "functionTypeForm";
  
  private List<FunctionTypeDTO> results;

  
  /**
   * @return the results
   */
  public List<FunctionTypeDTO> getResults() {
    return results;
  }

  
  /**
   * @param results the results to set
   */
  public void setResults(List<FunctionTypeDTO> results) {
    this.results = results;
  }
  
  

}
