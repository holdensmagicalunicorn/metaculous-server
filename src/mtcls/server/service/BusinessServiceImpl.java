package mtcls.server.service;

import java.io.Serializable;
import java.util.Map;

import mtcls.common.model.service.BaseServiceListLoadResult;
import mtcls.common.model.service.ServiceListLoadResult;
import mtcls.common.service.BusinessService;
import mtcls.server.adapter.gxt.GxtServiceAdapter;

import com.extjs.gxt.ui.client.data.ModelData;

@SuppressWarnings("serial")
public class BusinessServiceImpl extends SpringControllerGWT implements BusinessService{
	protected Map<String, GxtServiceAdapter> serviceMap;
	
	@Override
	public ServiceListLoadResult<Serializable> fetch(ModelData config) {
		ServiceListLoadResult<Serializable> retval = service(config, "fetch");		
		return retval;	
	}
	
	@Override
	public ServiceListLoadResult<Serializable> submit(ModelData config) {
		ServiceListLoadResult<Serializable> retval = service(config, "submit");		
		return retval;	
	}
	
	protected ServiceListLoadResult<Serializable> service(ModelData config, String operation) {
		String serviceName = config.get("service");
		config.set("_op_", operation);
		GxtServiceAdapter adapter = getService(serviceName);
		ServiceListLoadResult<Serializable> retval = null;
		if(adapter != null){
			retval = adapter.service(serviceName, config);
		}
		if(retval == null){
			retval = new BaseServiceListLoadResult<Serializable>(serviceName);
		}else{
			retval.setServiceName(serviceName);
		}
		return retval;
	}

	
	public GxtServiceAdapter getService(String serviceName){
		GxtServiceAdapter retval = null;
		retval = getServiceMap().get(serviceName);
		return retval;
	}

	/**
	 * @return the serviceMap
	 */
	public Map<String, GxtServiceAdapter> getServiceMap() {
		return serviceMap;
	}

	/**
	 * @param serviceMap the serviceMap to set
	 */
	public void setServiceMap(Map<String, GxtServiceAdapter> serviceMap) {
		this.serviceMap = serviceMap;
	}
}
