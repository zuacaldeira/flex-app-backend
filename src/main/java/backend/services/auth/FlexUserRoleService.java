/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.services.auth;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.neo4j.ogm.cypher.query.SortOrder;
import backend.services.AbstractDBService;
import db.auth.UserRole;

/**
 *
 * @author zua
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class FlexUserRoleService extends AbstractDBService<UserRole> {

    @Override
    public Class<UserRole> getClassType() {
        return UserRole.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "roleName");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "roleName");
    }

}
