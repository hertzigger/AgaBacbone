package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;
import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class ChatPersistence extends MysqlPersistence<ChatPersistence> implements IChatPersistence {

    @Override
    protected ChatPersistence getThis() {
        return this;
    }
}
