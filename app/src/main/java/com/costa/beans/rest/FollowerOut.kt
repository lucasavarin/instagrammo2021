package com.costa.beans.rest

import com.costa.`interface`.RestModel

class FollowerOut(
    var id: Int,
    var name: String,
    var description: String,
    var picture: String
): RestModel