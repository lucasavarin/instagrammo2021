package com.example.instagrammo.service

import com.example.instagrammo.beans.business.notification.NotificationArguments

interface NotificationData {

    fun getArgs() : NotificationArguments
}