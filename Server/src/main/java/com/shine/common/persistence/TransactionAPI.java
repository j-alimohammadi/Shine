package com.shine.common.persistence;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class TransactionAPI {

    private TransactionTemplate txTemplate;


    public TransactionAPI(PlatformTransactionManager transactionManager) {
        this(transactionManager, TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    public TransactionAPI(PlatformTransactionManager transactionManager, int propagationBehavior) {
        txTemplate = new TransactionTemplate(transactionManager);
        txTemplate.setPropagationBehavior(propagationBehavior);
    }

    /**
     * Execute the action specified by the given callback object within a transaction.
     *
     * @param callback the callback object that specifies the transactional action
     * @return a result object returned by the callback, or {@code null} if none
     * @throws TransactionException in case of initialization, rollback, or system errors
     * @throws RuntimeException     if thrown by the TransactionCallback
     */
    public <T> T doInTransaction(TransactionCallback<T> callback) {

        return txTemplate.execute(callback);
    }

}