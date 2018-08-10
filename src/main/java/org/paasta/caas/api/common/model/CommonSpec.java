package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Replicaset Model 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class CommonSpec {

    @SerializedName("minReadySeconds")
    private int minReadySeconds;

    @SerializedName("replicas")
    private int replicas;

    @SerializedName("selector")
    private CommonLabelSelector selector;

    @SerializedName("template")
    private CommonTemplateSpec template;

    @SerializedName("activeDeadlineSeconds")
    private long activeDeadlineSeconds;

    @SerializedName("automountServiceAccountToken")
    private boolean automountServiceAccountToken;

    @SerializedName("dnsPolicy")
    private String dnsPolicy;

    @SerializedName("hostIPC")
    private boolean hostIPC;

    @SerializedName("hostNetwork")
    private boolean hostNetwork;

    @SerializedName("hostPID")
    private boolean hostPID;

    @SerializedName("hostname")
    private String hostname;

    @SerializedName("nodeName")
    private String nodeName;

    @SerializedName("nodeSelector")
    private Map<String, String> nodeSelector;

    @SerializedName("priority")
    private int priority;

    @SerializedName("priorityClassName")
    private String priorityClassName;

    @SerializedName("restartPolicy")
    private String restartPolicy;

    @SerializedName("schedulerName")
    private String schedulerName;

    @SerializedName("serviceAccount")
    private String serviceAccount;

    @SerializedName("serviceAccountName")
    private String serviceAccountName;

    @SerializedName("shareProcessNamespace")
    private boolean shareProcessNamespace;

    @SerializedName("subdomain")
    private String subdomain;

    @SerializedName("terminationGracePeriodSeconds")
    private long terminationGracePeriodSeconds;

    @SerializedName("type")
    private String type;

    @SerializedName("clusterIP")
    private String clusterIP;

    @SerializedName("ports")
    private List ports;



//    @SerializedName("tolerations")
//    private List<Toleration> tolerations;
//
//    @SerializedName("volumes")
//    private List<Volume> volumes;
//
//    @SerializedName("affinity")
//    private V1Affinity affinity;
//
//    @SerializedName("securityContext")
//    private PodSecurityContext securityContext;
//
//    @SerializedName("imagePullSecrets")
//    private List<V1LocalObjectReference> imagePullSecrets;
//
//    @SerializedName("initContainers")
//    private List<V1Container> initContainers;
//
//    @SerializedName("containers")
//    private List<Container> containers = new ArrayList<Container>();
//
//    @SerializedName("dnsConfig")
//    private PodDNSConfig dnsConfig;
//
//    @SerializedName("hostAliases")
//    private List<HostAlias> hostAliases;
}