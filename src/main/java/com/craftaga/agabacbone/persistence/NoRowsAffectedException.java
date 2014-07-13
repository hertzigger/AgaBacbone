package com.craftaga.agabacbone.persistence;

import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class NoRowsAffectedException extends SQLException {
    public NoRowsAffectedException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public NoRowsAffectedException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public NoRowsAffectedException(String reason) {
        super(reason);
    }

    public NoRowsAffectedException() {
    }

    public NoRowsAffectedException(Throwable cause) {
        super(cause);
    }

    public NoRowsAffectedException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public NoRowsAffectedException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public NoRowsAffectedException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}
