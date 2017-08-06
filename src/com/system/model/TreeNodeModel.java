package com.system.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TreeNodeModel {
	private String id;
	private String text;
	private String state = "open";
	private String iconCls;
	private boolean checked;
	private List<TreeNodeModel> children=new ArrayList<TreeNodeModel>();
	
	public JSONObject buildJson(){
		JSONObject json=new JSONObject();
		json.put("id", id);
		json.put("text", text);
		json.put("state", state);
		json.put("iconCls", iconCls);
		json.put("checked", checked);
		if(children.size()>0){
			JSONArray childrenArray=new JSONArray();
			for (TreeNodeModel childNode : children) {
				childrenArray.add(childNode.buildJson());
			}
			json.put("children", childrenArray);
		}
		return json;
	}

	public TreeNodeModel() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeNodeModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeModel> children) {
		this.children = children;
	}
}
