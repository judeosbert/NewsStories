package com.klepto.labs.newsstories.network.models

import com.klepto.labs.newsstories.db.models.ItemType

open class BaseMenuItem(
    val type:ItemType
)
class DiscoverMenuItem(val name:String,val icon:String):BaseMenuItem(ItemType.CONTENT)

class DiscoverAdItem():BaseMenuItem(ItemType.AD)