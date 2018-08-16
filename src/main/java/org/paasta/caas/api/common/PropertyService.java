package org.paasta.caas.api.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Property Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.07
 */
@Service
@Data
public class PropertyService {

    @Value("${caasMaster.api.url}")
    private String caasMasterApiUrl;

    @Value("${caasMaster.api.token}")
    private String caasMasterApiToken;

    @Value("${commonApi.url}")
    private String commonApiUrl;

    @Value("${caasMaster.api.list.services.list}")
    private String caasMasterApiListServicesListUrl;

    @Value("${caasMaster.api.list.services.get}")
    private String caasMasterApiListServicesGetUrl;

    @Value("${caasMaster.api.list.endpoints.list}")
    private String caasMasterApiListEndpointsListUrl;

    @Value("${caasMaster.api.list.endpoints.get}")
    private String caasMasterApiListEndpointsGetUrl;

    @Value("${caasMaster.api.list.pods.list}")
    private String caasMasterApiListPodsListUrl;

    @Value("${caasMaster.api.list.pods.get}")
    private String caasMasterApiListPodsGetUrl;

    @Value("${caasMaster.api.list.nodes.list}")
    private String caasMasterApiListNodesListUrl;

    @Value("${caasMaster.api.list.nodes.get}")
    private String caasMasterApiListNodesGetUrl;

    // ReplicaSet List
    @Value("${caasMaster.api.list.replicaset.list}")
    private String caasMasterApiListReplicasetListUrl;

    // ReplicaSet
    @Value("${caasMaster.api.list.replicaset.get}")
    private String caasMasterApiListReplicasetGetUrl;

    // Role List By All Namespaces
    @Value("${caasMaster.api.list.role.allList}")
    private String caasMasterApiListRoleAllListUrl;

    // Role List By Specified Namespace
    @Value("${caasMaster.api.list.role.list}")
    private String caasMasterApiListRoleListUrl;

    // Role By Specified Namespace
    @Value("${caasMaster.api.list.role.get}")
    private String caasMasterApiListRoleGetUrl;
}
