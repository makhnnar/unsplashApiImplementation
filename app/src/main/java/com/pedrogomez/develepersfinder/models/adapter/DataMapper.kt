package com.pedrogomez.develepersfinder.models.adapter

interface DataMapper<R,L> {

    fun fromRemoteToLocal(remote:R):L

}