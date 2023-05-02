package com.example.core.usecase

import com.example.core.entities.MenuItem
import com.example.core.repository.MenuRepo

class GetMenuList(private val repo: MenuRepo) {
    suspend operator fun invoke(): List<MenuItem> = repo.fetchApi()
}