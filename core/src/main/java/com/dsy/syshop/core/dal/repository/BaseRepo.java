package com.dsy.syshop.core.dal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 14:00
 */
@NoRepositoryBean
public interface BaseRepo<T, ID> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {
}
