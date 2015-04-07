package com.huayu.core.bean.vo;

import java.util.ArrayList;

public class PermissionsList extends ArrayList<Permissions> {
	private static final long serialVersionUID = 4053872023716119412L;
	
	@Override
	public boolean contains(Object paramObject) {
		int i;
		if (paramObject == null){
			for (i = 0; i < this.size(); ++i){
				if (this.get(i) == null){
					return true;
				}
			}
		}
		else{
			Permissions permissionsParam=(Permissions)paramObject;
			Permissions permissions=null;
			for (i = 0; i < this.size(); ++i){
				permissions=(Permissions)this.get(i);
				if ((permissionsParam.getPermissionsKey()==null && permissions.getPermissionsKey()==null)
						|| (permissions.getPermissionsKey()!=null && permissions.getPermissionsKey().equals(permissionsParam.getPermissionsKey()))){
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder pStr=new StringBuilder();
		for(Permissions p: this){
			pStr.append("[")
			.append(p.getPermissionsKey())
			.append("]")
			.append(p.getPermissionsDesc())
			.append(", ");
		}
		return pStr.toString();
	}
	
	public boolean hasPermissions(String permissionsKey){
		if(this.isEmpty()){
			return false;
		}
		
		for(Permissions p: this){
			if(permissionsKey.equals(p.getPermissionsKey())){
				return true;
			}
		}
		
		return false;
	}
}
