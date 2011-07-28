package mtcls.server.adapter.gxt;

import java.io.Serializable;

import mtcls.common.model.service.ServiceListLoadResult;

import com.extjs.gxt.ui.client.data.ModelData;

public interface GxtServiceAdapter {
	public ServiceListLoadResult<Serializable> service(String serviceName, ModelData config);
}
