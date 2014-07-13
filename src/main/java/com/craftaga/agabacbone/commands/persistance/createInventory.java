package com.craftaga.agabacbone.commands.persistance;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.persistence.entities.IInventoryPersistence;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class CreateInventory extends Command implements IValueHolderCommand<Integer> {

    private IInventoryPersistence inventoryPersistence;
    private Integer value;

    public CreateInventory(CommandQueue commandQueue, IInventoryPersistence inventoryPersistence) {
        super(commandQueue);
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public void setValue(Integer integer) {
        this.value = integer;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void execute() {
        try {
            setValue(inventoryPersistence.add());
        } catch (SQLException e) {
            getDefaultCommandQueue().getPlugin().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        }
    }
}
