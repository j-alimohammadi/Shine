package com.shine.common.persistence;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class TransactionAPI {


    public static void executeInTransaction() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);
        txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        txTemplate.execute((TransactionCallback<Object>) status -> {

        });
    }
}
