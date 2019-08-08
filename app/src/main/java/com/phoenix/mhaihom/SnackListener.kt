package com.phoenix.mhaihom

interface SnackListener {
    fun onItemClick(position: Int,model:Snack)
    fun onBtnClick(model:Snack)
}