package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class InstructionPersistence extends MysqlPersistence<InstructionPersistence> implements IInstructionPersistence {

    @Override
    protected InstructionPersistence getThis() {
        return this;
    }
}
