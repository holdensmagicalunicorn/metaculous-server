package mtcls.server.util;

public abstract class QueryHelper {
	public static String getOrderByClauseHql(String tableAlias, String orderByCol, boolean asc){
		String retval = null;
		String sortDir = (!asc) ? "desc" : "asc";
		orderByCol = (orderByCol!=null && !"".equals(orderByCol)) ? orderByCol : "";
		String pfx = (tableAlias!=null && !"".equals(tableAlias)) ? tableAlias + "." : "";
		retval = (orderByCol!=null && !"".equals(orderByCol)) ? pfx + orderByCol + " " + sortDir : "";
		if(retval!=null && !"".equals(retval)){
			retval = "order by " + retval;
		}
		return retval;		
	}
}
