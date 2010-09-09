package org.kuali.student.lum.lu.ui.tools.client.widgets.itemlist;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.search.dto.SearchParam;
import org.kuali.student.core.search.dto.SearchRequest;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CluSetRangeHelper;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.QueryParamHelper;
import org.kuali.student.lum.lu.dto.MembershipQueryInfo;

public class CluSetRangeModelUtil {
    
    public static CluSetRangeModelUtil INSTANCE = new CluSetRangeModelUtil();
    
    private CluSetRangeModelUtil() {
    }
    
    public Data toData(SearchRequest searchRequest, String searchRequestId) {
        if (searchRequest == null) {
            return null;
        }
        List<SearchParam> searchParams = searchRequest.getParams();
        CluSetRangeHelper cluSetRangeHelper = CluSetRangeHelper.wrap(new Data());
        cluSetRangeHelper.setId(searchRequestId);
        cluSetRangeHelper.setSearchTypeKey(searchRequest.getSearchKey());
        if (searchParams != null) {
            for (SearchParam searchParam : searchParams) {
                if (((String)searchParam.getValue()).isEmpty()) {
                    // skip the value if the user did not entered anything for this search parameter
                    continue;
                }
                QueryParamHelper queryParamHelper = QueryParamHelper.wrap(new Data());
                queryParamHelper.setValue((String)searchParam.getValue());
                queryParamHelper.setListValue(null);
                queryParamHelper.setKey(searchParam.getKey());
                if (cluSetRangeHelper.getQueryParams() == null) {
                    cluSetRangeHelper.setQueryParams(new Data());
                }
                cluSetRangeHelper.getQueryParams().add(queryParamHelper.getData());
            }
        }
        
        return cluSetRangeHelper.getData();
    }
    
    public Data toData(MembershipQueryInfo membershipQueryInfo) {
        if (membershipQueryInfo == null) {
            return null;
        }
        List<SearchParam> searchParams = membershipQueryInfo.getQueryParamValueList();
        CluSetRangeHelper cluSetRangeHelper = CluSetRangeHelper.wrap(new Data());
        cluSetRangeHelper.setId(membershipQueryInfo.getId());
        cluSetRangeHelper.setSearchTypeKey(membershipQueryInfo.getSearchTypeKey());
        if (searchParams != null) {
            for (SearchParam searchParam : searchParams) {
                QueryParamHelper queryParamHelper = QueryParamHelper.wrap(new Data());
                queryParamHelper.setValue((String)searchParam.getValue());
                queryParamHelper.setListValue(null);
                queryParamHelper.setKey(searchParam.getKey());
                if (cluSetRangeHelper.getQueryParams() == null) {
                    cluSetRangeHelper.setQueryParams(new Data());
                }
                cluSetRangeHelper.getQueryParams().add(queryParamHelper.getData());
            }
        }
        
        return cluSetRangeHelper.getData();
    }
    
//    public SearchRequest toSearchRequest(Data data) {
//        SearchRequest searchRequest = null;
//        if (data != null) {
//            CluSetRangeHelper cluSetRangeHelper = CluSetRangeHelper.wrap(data);
//            Data queryParamsData = cluSetRangeHelper.getQueryParams();
//            searchRequest = new SearchRequest();
//            searchRequest.setSearchKey(cluSetRangeHelper.getSearchTypeKey());
//            for (Data.Property p : queryParamsData) {
//                QueryParamHelper queryParamHelper = QueryParamHelper.wrap((Data)p.getValue());
//                SearchParam searchParam = new SearchParam();
//                searchParam.setKey(queryParamHelper.getKey());
//                searchParam.setValue(queryParamHelper.getValue());
//                if (searchRequest.getParams() == null) {
//                    searchRequest.setParams(new ArrayList<SearchParam>());
//                }
//                searchRequest.getParams().add(searchParam);
//            }
//        }
//        return searchRequest;
//    }
    
    public MembershipQueryInfo toMembershipQueryInfo(Data data) {
        MembershipQueryInfo membershipQueryInfo = null;
        if (data != null) {
            CluSetRangeHelper cluSetRangeHelper = CluSetRangeHelper.wrap(data);
            Data queryParamsData = cluSetRangeHelper.getQueryParams();
            membershipQueryInfo = new MembershipQueryInfo();
            membershipQueryInfo.setId(cluSetRangeHelper.getId());
            membershipQueryInfo.setSearchTypeKey(cluSetRangeHelper.getSearchTypeKey());
            // make sure the membershipQueryInfo has some contents in it.
            // Return null if otherwise.
            if (membershipQueryInfo.getSearchTypeKey() == null ||
                    membershipQueryInfo.getSearchTypeKey().trim().isEmpty()) {
                return null;
            }
            for (Data.Property p : queryParamsData) {
                QueryParamHelper queryParamHelper = QueryParamHelper.wrap((Data)p.getValue());
                SearchParam searchParam = new SearchParam();
                searchParam.setKey(queryParamHelper.getKey());
                searchParam.setValue(queryParamHelper.getValue());
                if (membershipQueryInfo.getQueryParamValueList() == null) {
                    membershipQueryInfo.setQueryParamValueList(new ArrayList<SearchParam>());
                }
                membershipQueryInfo.getQueryParamValueList().add(searchParam);
            }
        }
        return membershipQueryInfo;
    }

}
