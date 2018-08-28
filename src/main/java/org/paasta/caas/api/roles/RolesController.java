package org.paasta.caas.api.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Role API 를 호출받는 컨트롤러이다.
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-08-14
 */
@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService roleService;

    @Autowired
    public RolesController(RolesService roleService) {
        this.roleService = roleService;
    }

    /**
     * Role 객체의 리스트를 조회한다. (모든 네임스페이스에서 조회)
     *
     * @return
     */
    @GetMapping
    public RolesList getRoleListByAllNamespace(){
        return roleService.getRoleListByAllNamespace();
    }

    /**
     * Role 객체의 리스트를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace 조회 대상 네임스페이스
     * @return RoleList
     */
    @GetMapping(value = "/namespaces/{namespace}/roles")
    public RolesList getRoleList(@PathVariable("namespace") String namespace){
        return roleService.getRoleList(namespace);
    }

    /**
     * Role 객체를 조회한다. (특정 네임스페이스에서 조회)
     *
     * @param namespace 조회 대상 네임스페이스
     * @param roleName 조회 대상 role 이름
     * @return Role
     */
    @GetMapping(value = "/namespaces/{namespace}/roles/{roleName}")
    public Roles getRole(@PathVariable("namespace") String namespace, @PathVariable("roleName") String roleName){
        return roleService.getRole(namespace, roleName);
    }
}