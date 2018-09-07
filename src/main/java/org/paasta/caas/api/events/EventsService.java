package org.paasta.caas.api.events;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Events Service 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class EventsService {

    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new Events service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public EventsService(RestTemplateService restTemplateService, CommonService commonService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Gets event list.
     *
     * @param namespace the namespace
     * @param resourceName the resourceName
     * @return the event list
     */
    public EventsList getEventList(String namespace, String resourceName) {
        String requestSelector = "?fieldSelector=involvedObject.name=" + resourceName;

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListEventsListUrl()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        return (EventsList) commonService.setResultModel(
                commonService.setResultObject(resultMap, EventsList.class), Constants.RESULT_STATUS_SUCCESS);
    }

    EventsList getEventListByNode(String namespace, String nodeName) {
        String requestURL;
        if ("_all".equals(namespace)) {
            requestURL = propertyService.getCaasMasterApiListEventsAllListUrl();
        }else {
            requestURL = propertyService.getCaasMasterApiListEventsListUrl().replace("{namespace}", namespace);
        }

        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                requestURL, HttpMethod.GET, null, Map.class);
        List<Map<String, Object>> originalItems = (List<Map<String, Object>>)resultMap.get("items");
        List<Map<String, Object>> filterItems = new LinkedList<>();

        for(Map<String, Object> item : originalItems) {
            Map<String, Object> source = (Map<String, Object>)item.get("source");
            if (null == source) {
                continue;
            }
            String sourceHostname = (String) source.get("host");
            if (nodeName.equals(sourceHostname)) {
                filterItems.add(item);
            }
        }

        resultMap.put("items", filterItems);

        return (EventsList) commonService.setResultModel(commonService.setResultObject(resultMap, EventsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
