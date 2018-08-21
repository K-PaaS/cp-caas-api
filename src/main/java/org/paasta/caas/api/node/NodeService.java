package org.paasta.caas.api.node;

import com.google.gson.Gson;
import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.paasta.caas.api.customService.CustomServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Node Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.8.13
 */
@Service
public class NodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomServiceService.class);
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;
    private final Gson gson;

    /**
     * Instantiates a new Node service.
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public NodeService(RestTemplateService restTemplateService, CommonService commonService,
                       PropertyService propertyService, Gson gson) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
        this.gson = gson;
    }


    /**
     * Gets node list.
     *
     * @return the node list
     */
    NodeList getNodeList() {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListNodesListUrl(), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (NodeList) commonService.setResultModel(gson.fromJson(gson.toJson(resultMap), NodeList.class), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Gets node.
     *
     * @param nodeName the node name
     * @return the node
     */
    Node getNode(String nodeName) {
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListNodesGetUrl().replace("{name}", nodeName), HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (Node) commonService.setResultModel(gson.fromJson(gson.toJson(resultMap), Node.class), Constants.RESULT_STATUS_SUCCESS);
    }

}
