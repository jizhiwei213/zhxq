package com.system.service;

import java.util.List;

import com.system.dao.DataDictionaryDAO;
import com.system.model.DataDictionaryModel;

public class DataDictionaryService {
	private DataDictionaryDAO dataDictionaryDAO;

	public void setDataDictionaryDAO(DataDictionaryDAO dataDictionaryDAO) {
		this.dataDictionaryDAO = dataDictionaryDAO;
	}
	
	/**
	 * 根据dataType查询字典项集合
	 * @param dataType
	 * @return
	 */
	public List<DataDictionaryModel> getDateDictionaryListByDataType(String dataType) {
		return dataDictionaryDAO.getDateDictionaryListByDataType(dataType);
	}

	public List<DataDictionaryModel> getDataDictionaryList(String dataType, Integer start, Integer rows) {
		return dataDictionaryDAO.getDataDictionaryList(dataType,start,rows);
	}

	public int getDataDictionaryCount(String dataType) {
		return dataDictionaryDAO.getDataDictionaryCount(dataType);
	}

	public DataDictionaryModel getDataDictionaryById(String id) {
		return dataDictionaryDAO.getDataDictionaryById(id);
	}

	/**
	 * 根据dataType查询父类
	 * @param dataType
	 * @return
	 */
	public DataDictionaryModel getParentByDataType(String dataType) {
		return dataDictionaryDAO.getParentByDataType(dataType);
	}

	public void updateDataDictionary(DataDictionaryModel dataDictionary) {
		dataDictionaryDAO.updateDataDictionary(dataDictionary);
	}

	public void saveDataDictionary(DataDictionaryModel dataDictionary) {
		dataDictionaryDAO.saveDataDictionary(dataDictionary);
	}

	public void deleteDataDictionary(String id) {
		dataDictionaryDAO.deleteDataDictionary(id);
	}

	public DataDictionaryModel getDataDictionaryByNameAndDataType(String name,String dataType) {
		return dataDictionaryDAO.getDataDictionaryByNameAndDataType(name,dataType);
	}
}
