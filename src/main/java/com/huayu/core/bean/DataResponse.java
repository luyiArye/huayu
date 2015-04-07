package com.huayu.core.bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.huayu.core.exception.ApplicationException;

/**
 * ajax数据响应对象
 * @author Administrator
 *
 */
public class DataResponse {
	protected Logger LOG=Logger.getLogger(this.getClass());
	
	private ApplicationException applicationException;
	private String exCode;
	private String exDesc;
	private String exStack;
	private Object data;
	//操作失败的次数
	private int ofc;
	
	//总行数
	private int tc;
	//总页数
	private int tp;

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public int getOfc() {
		return ofc;
	}

	public void setOfc(int ofc) {
		this.ofc = ofc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getExCode() {
		return exCode;
	}

	public String getExDesc() {
		return exDesc;
	}

	public String getExStack() {
		return exStack;
	}

	@JsonIgnore
	public ApplicationException getApplicationException() {
		return applicationException;
	}

	public void setApplicationException(ApplicationException applicationException) {
		this.applicationException = applicationException;
	}
	
	/**
	 * 输出json
	 * @param response
	 * @return
	 */
	public void writeToResponse(HttpServletResponse response){
		PrintWriter write;
		try {
			write = response.getWriter();
			write.print(this.toString());
		} 
		catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		
		if(applicationException!=null){
			this.exCode=applicationException.getExCode();
			this.exDesc=applicationException.getExDesc();
			this.exStack=applicationException.getExStack();
		}
		
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			LOG.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		
		return "{exCode:\"EX_000001\",exDesc:\"转化Json异常!\"}";
	}
}
