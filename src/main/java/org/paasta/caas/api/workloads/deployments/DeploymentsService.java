package org.paasta.caas.api.workloads.deployments;

import org.paasta.caas.api.common.CommonService;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Deployments 관련 API에 대해 CaaS API를 향해 호출하고, 결과값을 처리하는 서비스이다.
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.13
 */
@Service
public class DeploymentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger( DeploymentsService.class );
    private final RestTemplateService restTemplateService;
    private final CommonService commonService;
    private final PropertyService propertyService;

    @Autowired
    public DeploymentsService(RestTemplateService restTemplateService, CommonService commonService,
                              PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
    }

    /**
     * Deployments List 조회 (전체 네임스페이스 대상)
     *
     * @return DeploymentsList
     */
    public DeploymentsList getDeploymentListByAllNamespace () {
        DeploymentsList responseObject;
        String resultCode;
        String resultStatusMessage = "";
        final String requestPath = propertyService.getCaasMasterApiListDeploymentAllList();
        HashMap<String, Object> responseMap = ( HashMap<String, Object> ) restTemplateService.send(
            Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class );
        LOGGER.info( "#### getDeploymentList() :: hashMap.toString() :: {}", responseMap.toString() );

        responseObject = commonService.setResultObject(responseMap, DeploymentsList.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (DeploymentsList) commonService.setResultModel(responseObject, resultCode);
    }

    /**
     * Deployments List 조회 (특정 네임스페이스 대상)
     *
     * @param namespace namespace
     * @return DeploymentsList
     */
    public DeploymentsList getDeploymentList (String namespace) {
        DeploymentsList responseObject;
        String resultCode;
        String resultStatusMessage = "";
        String requestPath = propertyService.getCaasMasterApiListDeploymentList().replace( "{namespace}", namespace );
        HashMap<String, Object> responseMap = ( HashMap<String, Object> ) restTemplateService.send(
            Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class );
        LOGGER.info( "#### getDeploymentList({}) :: hashMap.toString() :: {}", namespace, responseMap.toString() );

        responseObject = commonService.setResultObject(responseMap, DeploymentsList.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (DeploymentsList) commonService.setResultModel( responseObject, resultCode);
    }

    /**
     * Deployments 조회 (특정 네임스페이스에 있는 deployment)
     *
     * @param namespace namespace
     * @param deploymentName deployment name
     * @return Deployments
     */
    public Deployments getDeployment (String namespace, String deploymentName) {
        Deployments responseObject;
        String resultCode;
        String resultStatusMessage = "";
        String requestPath = propertyService.getCaasMasterApiListDeploymentGet()
            .replace( "{namespace}", namespace ).replace( "{deploymentName}", deploymentName );
        HashMap<String, Object> responseMap = ( HashMap<String, Object> ) restTemplateService.send(
            Constants.TARGET_CAAS_MASTER_API, requestPath, HttpMethod.GET, null, Map.class );


        String resultString = restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                requestPath, HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML);

        //noinspection unchecked
        responseMap.put("sourceTypeYaml", resultString);

        responseMap.put("source",new LinkedHashMap(responseMap));
        LOGGER.info( "#### getDeployment,({}, {}) :: hashMap.toString() :: {}",
            namespace, deploymentName, responseMap.toString() );

        responseObject = commonService.setResultObject(responseMap, Deployments.class);
        resultCode = Constants.RESULT_STATUS_SUCCESS;

        return (Deployments) commonService.setResultModel(responseObject, resultCode);
    }

    /**
     * Deployments List Label 조회
     *
     * @return Map
     */
    public DeploymentsList getDeploymentsListLabelSelector(String namespace, String selectors) {
        String requestSelector = "?labelSelector=" + selectors;
        HashMap resultMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListDeploymentList()
                        .replace("{namespace}", namespace) + requestSelector, HttpMethod.GET, null, Map.class);

        LOGGER.info("########## resultMap.toString() :: {}", resultMap.toString());

        return (DeploymentsList) commonService.setResultModel(commonService.setResultObject(resultMap, DeploymentsList.class), Constants.RESULT_STATUS_SUCCESS);
    }
}
