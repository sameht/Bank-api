package com.kobita.banking.transaction;

import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecifications {
    public static Specification<Transaction> hasFromAccountNumber(String fromAccountNumber){
        return (root, query, criteriaBuilder) -> {
            if( fromAccountNumber == null || fromAccountNumber.isEmpty()) return null;

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("fromAccount").get("accountNumber")), "%" + fromAccountNumber.toLowerCase()+ "%");
        };
    }

    public static Specification<Transaction> hasToAccountNumber(String toAccountNumber){
        return (root, query, criteriaBuilder) -> {
            if( toAccountNumber == null || toAccountNumber.isEmpty()) return null;

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("toAccount").get("accountNumber")), "%" + toAccountNumber.toLowerCase()+ "%");
        };
    }

    public static Specification<Transaction> hasType(String type){
        return (root, query, criteriaBuilder) -> {
            if( type == null || type.isEmpty()) return null;

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), "%" + type.toLowerCase()+ "%");
        };
    }
    
    public static Specification<Transaction> minAmount(Double minAmount){
        return (root, query, criteriaBuilder) -> {
            if( minAmount == null ) return null;

            return criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), minAmount);
        };
    }
    public static Specification<Transaction> maxAmount(Double maxAmount){
        return (root, query, criteriaBuilder) -> {
            if( maxAmount == null ) return null;

            return criteriaBuilder.lessThanOrEqualTo(root.get("amount"), maxAmount);
        };
    }


}
