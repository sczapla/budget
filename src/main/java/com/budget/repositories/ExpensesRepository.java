package com.budget.repositories;

import org.springframework.data.repository.CrudRepository;

import com.budget.model.Expenses;

public interface ExpensesRepository extends CrudRepository<Expenses, Integer> {

}
